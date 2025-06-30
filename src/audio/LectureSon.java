package audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * Classe utilitaire pour jouer des fichiers audio (.wav) depuis /ressources/sons.
 */
public class LectureSon {

    /**
     * Joue un son depuis le dossier ressources/sons.
     * @param nomFichier Nom du fichier son (ex : victoire.wav)
     */
    public static void jouerSon(String nomFichier) {
        try {
            //chemin pour le fichier sonore
            File cheminFichier = new File("ressources/sons/" + nomFichier);
            if (!cheminFichier.exists()) {
                System.err.println("Fichier introuvable : " + cheminFichier.getPath());
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(cheminFichier);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Il y a des problemes avec le son, Danella!!!!.");
        } 
    }
}