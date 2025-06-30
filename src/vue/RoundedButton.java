package vue;

import java.awt.*;
import javax.swing.*;

/**
 * Bouton personnalisé avec coins arrondis.
 */
public class RoundedButton extends JButton {
    private final int arcWidth, arcHeight;  // Dimensions des coins arrondis

    /**
     * Constructeur pour initialiser le bouton avec texte et coins arrondis.
     */
    public RoundedButton(String text, int arcWidth, int arcHeight) {
        super(text);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;

        // Désactive les styles par défaut
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    /**
     * Dessine le bouton avec coins arrondis.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Active l'anticrénelage pour des bords plus lisses
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Remplissage du bouton avec la couleur de fond
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        super.paintComponent(g);
        g2.dispose();
    }

    /**
     * Dessine la bordure arrondie du bouton.
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Active l'anticrénelage pour une bordure plus nette
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dessine la bordure avec la couleur du texte
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcHeight);

        g2.dispose();
    }
}
