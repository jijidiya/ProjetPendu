package vue;

import javax.swing.*;
import java.awt.*;

/**
 * Écran d'accueil du jeu.
 */
public class FenetreAccueil extends JFrame {

    public FenetreAccueil() {
        setTitle("Jeu du Pendu");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel fond = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(245,240,255),
                                                     0, getHeight(), new Color(160,200,255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        fond.setLayout(new BoxLayout(fond, BoxLayout.Y_AXIS));
        fond.setOpaque(false);

        JLabel titre = new JLabel("Le Pendu");
        titre.setFont(new Font("Serif", Font.BOLD, 40));
        titre.setForeground(new Color(20,40,80));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton jouer = new RoundedButton("JOUER", 60, 60);
        styleButton(jouer);

        jouer.addActionListener(e ->
            new FenetreNiveau().setVisible(true)
        );

        fond.add(Box.createVerticalStrut(100));
        fond.add(titre);
        fond.add(Box.createVerticalStrut(80));
        fond.add(jouer);

        setContentPane(fond);
    }

    private void styleButton(RoundedButton btn) {
        btn.setFont(new Font("SansSerif", Font.PLAIN, 20));
        btn.setBackground(new Color(20,50,80));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(120,60));
        btn.setMargin(new Insets(10,30,10,30));
    }
}