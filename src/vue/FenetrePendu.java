package vue;

import javax.swing.*;

/**
 * Fenêtre contenant le HangmanPanel.
 */
public class FenetrePendu extends JFrame {


    public FenetrePendu(String mot, int essais) {
        setTitle("Jeu du Pendu");
        setSize(1200,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new HangmanPanel(mot, essais));
    }
}