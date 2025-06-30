package vue;

import java.awt.*;
import javax.swing.*;

/**
 * Fenêtre finale arguant victoire ou défaite + options.
 */
public class FenetreFin extends JFrame {

    public FenetreFin(boolean victoire, String motSecret) {
        setTitle("Jeu du Pendu");
        setSize(1000,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel fond = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2=(Graphics2D)g;
                GradientPaint gp=new GradientPaint(0,0,new Color(245,240,255),
                                                   0,getHeight(),new Color(160,200,255));
                g2.setPaint(gp);
                g2.fillRect(0,0,getWidth(),getHeight());
            }
        };
        fond.setLayout(new BoxLayout(fond,BoxLayout.Y_AXIS));
        fond.setOpaque(false);

        JLabel titre=new JLabel(victoire?"Bravo ! Tu as gagné":"Dommage ! Tu as perdu");
        titre.setFont(new Font("Serif",Font.BOLD,36));
        titre.setForeground(new Color(20,40,80));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel motLabel=new JLabel("Le mot était : "+motSecret.toUpperCase());
        motLabel.setFont(new Font("SansSerif",Font.PLAIN,24));
        motLabel.setForeground(Color.DARK_GRAY);
        motLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel boutons = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        boutons.setOpaque(false);

        RoundedButton rej = new RoundedButton("REJOUER",60,60);
        styleButton(rej);
        rej.addActionListener(e->{
            new FenetreNiveau().setVisible(true);
            dispose();
        });

        RoundedButton quit = new RoundedButton("QUITTER",60,60);
        styleButton(quit);
        quit.addActionListener(e->System.exit(0));

        boutons.add(rej); boutons.add(quit);

        fond.add(Box.createVerticalStrut(80));
        fond.add(titre);
        fond.add(Box.createVerticalStrut(30));
        fond.add(motLabel);
        fond.add(Box.createVerticalStrut(60));
        fond.add(boutons);

        setContentPane(fond);
    }

    private void styleButton(RoundedButton b){
        b.setFont(new Font("SansSerif",Font.PLAIN,18));
        b.setBackground(new Color(20,50,80));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setPreferredSize(new Dimension(150,60));
        b.setMargin(new Insets(10,25,10,25));
    }
}