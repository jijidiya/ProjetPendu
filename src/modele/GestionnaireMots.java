package modele;

import java.io.*;
import java.util.*;

    public class GestionnaireMots {
        private List<String> mots = new ArrayList<>();

        // Constructeur qui lit les mots depuis un fichier texte
        public GestionnaireMots(String nomFichier) {
            try (BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier))) {
                String ligne;
                while ((ligne = lecteur.readLine()) != null) {
                    String[] motsLigne = ligne.split("\\s+");
                    mots.addAll(Arrays.asList(motsLigne));
                }
            } catch (IOException e) {
                System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            }
        }

        // Méthode pour obtenir un mot aléatoire
        public String obtenirMotAleatoire() {
            if (mots.isEmpty()) return null;
            Random random = new Random();
            return mots.get(random.nextInt(mots.size()));
        }
    }

