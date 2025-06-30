import vue.FenetreAccueil;

import javax.swing.*;

/**
 * Point d'entrée principal du jeu du Pendu.
 */
public class Main {
    public static void main(String[] args) {
        // Lance l'interface graphique de manière sécurisée
        SwingUtilities.invokeLater(() -> {
            FenetreAccueil accueil = new FenetreAccueil();
            accueil.setVisible(true);
        });
    }
}