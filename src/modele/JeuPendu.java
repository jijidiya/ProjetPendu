
package modele;

import java.text.Normalizer;
import java.util.ArrayList;

// Gère la logique du jeu du pendu : lettres proposées, mot deviné, erreurs restantes, victoire/défaite.
public class JeuPendu {
	
    private String motSecret;
    private StringBuilder motDevineAffiche; 
    private ArrayList<Character> lettresProposees;
    private int erreursRestantes;

    private boolean indiceDejaAffiche = false;

    // Liste statique des mots déjà utilisés pour éviter les répétitions
    private static final ArrayList<String> motsDejaUtilises = new ArrayList<>();
    
    // Constructeur qui reçoit un mot déjà choisi par une autre classe, supprime les accents et prépare le mot à deviner
    public JeuPendu(String motChoisi, int nbrEssais) {
        if (motChoisi == null || motChoisi.trim().isEmpty()) {
            throw new IllegalArgumentException("Mot invalide ou null");
            
        }
        motSecret = supprimerAccents(motChoisi);
        
        // Vérifie si ce mot a déjà été utilisé
        if (motsDejaUtilises.contains(motSecret)) {
            throw new IllegalArgumentException("Mot déjà utilisé dans une partie précédente.");
        }
        motsDejaUtilises.add(motSecret);

        motDevineAffiche = new StringBuilder(); 
        for (int i = 0; i < motSecret.length(); i++) {        	
            motDevineAffiche.append("_"); 
        }

        lettresProposees = new ArrayList<>();
        erreursRestantes = nbrEssais;
    }

    // Permet de proposer une lettre. Met à jour l'état du jeu.
    public boolean  proposerLettre(char lettre) {
        lettre = Character.toLowerCase(lettre);

        // Ignorer si ce n'est pas une lettre (ex : chiffre, symbole)
        if (!Character.isLetter(lettre)) {
            return false;
        }

        if (lettresProposees.contains(lettre)) {
            return true;
        }

        lettresProposees.add(lettre);

        if (motSecret.indexOf(lettre) != -1) {
            for (int i = 0; i < motSecret.length(); i++) {
                if (motSecret.charAt(i) == lettre) {
                    motDevineAffiche.setCharAt(i, lettre);
                }
            }
            return true;
        } else {
            erreursRestantes--;
            return false;
        }
    }

    // Donne un indice (1 seule fois)
    public String donnerIndice() {
        if (motSecret == null || motSecret.isEmpty()) {
            return "Mot invalide.";
        }
        if (!indiceDejaAffiche) {
            indiceDejaAffiche = true;
            return "Indice : la première lettre est '" + motSecret.charAt(0) + "'";
        } else {
            return "L'indice a déjà été donné.";
        }
    }

    // Réinitialise l'état d’indice (si nouvelle partie)
    public void reinitialiserIndice() {
        indiceDejaAffiche = false;
    }

    // Vérifie si le joueur a gagné
    public boolean estGagne() {
        return motDevineAffiche.toString().equals(motSecret); 
    }

    // Vérifie si le joueur a perdu
    public boolean estPerdu() {
        return erreursRestantes <= 0;
    }

    // Retourne le mot avec les lettres devinées et les tirets
    public String getMotDevinePourAffichage() {
        return motDevineAffiche.toString();
    }
    // Retoure le mot secret 
    public String getMotSecret(){
        return motSecret;
    }

    // Retourne le nombre d’erreurs restantes
    public int getErreursRestantes() { 
        return erreursRestantes;
    }

    // Retourne la liste des lettres déjà proposées
    public ArrayList<Character> getLettresProposees() {
        return lettresProposees;
    }

    // Supprime les accents d’un mot et le met en minuscules
    public static String supprimerAccents(String texte) {
        if (texte == null) return "";
        String sansAccents = Normalizer.normalize(texte, Normalizer.Form.NFD);
        return sansAccents.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }
}
