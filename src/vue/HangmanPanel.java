package vue;

import audio.LectureSon;
import java.awt.*;
import javax.swing.*;
import modele.JeuPendu;

/**
 * Panneau de jeu : dessin, mot, saisie et boutons.
 */
public class HangmanPanel extends JPanel {

    private final JeuPendu jeu;
    private int erreurs = 6;

    private JPanel wordPanel;
    private JTextField input;
    private RoundedButton tryBtn, helpBtn;

    public HangmanPanel(String mot, int essaisMax) {
        this.jeu = new JeuPendu(mot, essaisMax);
        erreurs -= essaisMax;
        setLayout(new BorderLayout());
        setBackground(new Color(230,235,245));

        initDrawing();
        initWord();
        initControls();

        updateWord();
    }

    private void initDrawing() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawHangman((Graphics2D)g);
            }
        };
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(350,0));
        add(panel, BorderLayout.WEST);
    }

    private void drawHangman(Graphics2D g2){
        g2.setStroke(new BasicStroke(4));
        int x=50,y=50;
        g2.drawLine(x+50,y+450,x+150,y+450);
        g2.drawLine(x+100,y+450,x+100,y+50);
        g2.drawLine(x+100,y+50,x+250,y+50);
        g2.drawLine(x+250,y+50,x+250,y+100);

        switch(erreurs){
            case 6: g2.drawLine(x+250,y+250,x+280,y+300);
            case 5: g2.drawLine(x+250,y+250,x+220,y+300);
            case 4: g2.drawLine(x+250,y+180,x+280,y+230);
            case 3: g2.drawLine(x+250,y+180,x+220,y+230);
            case 2: g2.drawLine(x+250,y+140,x+250,y+250);
            case 1: g2.drawOval(x+230,y+100,50,40); break;
        }
    }

    private void initWord() {
        wordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
        wordPanel.setOpaque(false);
        add(new JPanel(){{
            setOpaque(false);
            add(wordPanel);
        }}, BorderLayout.CENTER);
    }

    private void initControls() {
        JPanel right = new JPanel(new GridBagLayout());
        right.setOpaque(false);
        right.setPreferredSize(new Dimension(250,0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        input = new JTextField(15);
        gbc.gridy=0;
        right.add(input,gbc);

        tryBtn=new RoundedButton("Valider",60,60);
        styleButton(tryBtn);
        tryBtn.addActionListener(e->process());
        gbc.gridy=1;
        right.add(tryBtn,gbc);

        helpBtn=new RoundedButton("Indice",60,60);
        styleButton(helpBtn);
        helpBtn.addActionListener(e->JOptionPane.showMessageDialog(this,jeu.donnerIndice()));
        gbc.gridy=2;
        right.add(helpBtn,gbc);

        add(right, BorderLayout.EAST);
    }

    private void styleButton(RoundedButton b) {
        b.setFont(new Font("SansSerif", Font.PLAIN,18));
        b.setBackground(new Color(20,50,80));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setPreferredSize(new Dimension(120,60));
        b.setMargin(new Insets(10,25,10,25));
    }

    private void updateWord(){
        wordPanel.removeAll();
        for(char c : jeu.getMotDevinePourAffichage().toCharArray()){
            JLabel lbl=new JLabel(""+c);
            lbl.setFont(new Font(Font.MONOSPACED,Font.BOLD,36));
            wordPanel.add(lbl);
        }
        wordPanel.revalidate();
        wordPanel.repaint();
    }

    private void process(){
        String s = input.getText().toUpperCase();
        if(s.length()!=1 || !Character.isLetter(s.charAt(0))) return;
        char c = s.charAt(0);


        if(!jeu.proposerLettre(c)){
            LectureSon.jouerSon("erreur.wav");
            erreurs++;
        }else{
            LectureSon.jouerSon("bon.wav");
        }
        
        updateWord(); repaint();

        if(jeu.estGagne()|| jeu.estPerdu()){
            if(jeu.estGagne()){
                LectureSon.jouerSon("victoire.wav");
            }else{
                LectureSon.jouerSon("defaite.wav");
            }
            new FenetreFin(jeu.estGagne(), jeu.getMotSecret()).setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose();
        }
        if(jeu.estGagne()|| jeu.estPerdu()){
            new FenetreFin(jeu.estGagne(), jeu.getMotSecret()).setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose();
        }
        input.setText("");
    }
}