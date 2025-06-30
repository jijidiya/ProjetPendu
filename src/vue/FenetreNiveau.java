package vue;

import java.awt.*;
import javax.swing.*;
import modele.GestionnaireMots;

/**
 * Choix du niveau : Facile (10), Moyen (8), Difficile (6 essais).
 */
public class FenetreNiveau extends JFrame {

    public FenetreNiveau() {
        setTitle("Jeu du Pendu");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel fond = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0,0,new Color(245,240,255),
                                                     0,getHeight(),new Color(160,200,255));
                g2.setPaint(gp);
                g2.fillRect(0,0,getWidth(),getHeight());
            }
        };
        fond.setLayout(new BoxLayout(fond, BoxLayout.Y_AXIS));
        fond.setOpaque(false);

        JLabel titre = new JLabel("Niveau de difficulté");
        titre.setFont(new Font("Serif", Font.BOLD, 40));
        titre.setForeground(new Color(20,40,80));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel boutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        boutons.setOpaque(false);

        String[] niveaux = {"EASY","MID","HARD"};
        for (String niv : niveaux) {
            RoundedButton btn = new RoundedButton(niv, 60,60);
            styleButton(btn);
            btn.addActionListener(e -> {
                int essais = niv.equals("EASY") ? 10 :
                             niv.equals("MID") ? 8 : 6;
                
                GestionnaireMots gestionnaireMots = new GestionnaireMots("ressources/mots.txt");
                String mot = gestionnaireMots.obtenirMotAleatoire(); 
                new FenetrePendu(mot,essais).setVisible(true);
                dispose();
            });
            boutons.add(btn);
        }

        fond.add(Box.createVerticalStrut(100));
        fond.add(titre);
        fond.add(Box.createVerticalStrut(80));
        fond.add(boutons);

        setContentPane(fond);
    }

    private void styleButton(RoundedButton btn) {
        btn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btn.setBackground(new Color(20,50,80));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(120,60));
        btn.setMargin(new Insets(10,25,10,25));
    }
}