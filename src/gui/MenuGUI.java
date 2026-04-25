package gui;

import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final int ETAT_MENU    = 0;
    private static final int ETAT_REGLES  = 1;
    private static final int ETAT_JOUER   = 2;
    private static final int ETAT_NOM   = 3;


    private int etatCourant = ETAT_MENU;
    private MenuDisplay panneauMenu;
    private JPanel panneauRegles;
    private JPanel panneauNom;
    private CardLayout cardLayout;
    private JPanel conteneur;
    public static String nomVille = "Ma Ville";
    
    public MenuGUI() {
        super("URBAIN");
        init();
    }

    
    private JButton creerBouton(String texte) {
        JButton btn = new JButton(texte);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(500, 50));
        return btn;
    }
    
    
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 495);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        conteneur = new JPanel(cardLayout);

        panneauMenu   = construirePanneauMenu();
        panneauRegles = construirePanneauRegles();
        panneauNom = construirePanneauNom();


        conteneur.add(panneauMenu,   "menu");
        conteneur.add(panneauRegles, "regles");
        conteneur.add(panneauNom, "nom");


        setContentPane(conteneur);
        cardLayout.show(conteneur, "menu");
        setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            switch (etatCourant) {

                case ETAT_MENU:
                    cardLayout.show(conteneur, "menu");
                    break;

                case ETAT_REGLES:
                    cardLayout.show(conteneur, "regles");
                    break;

                case ETAT_JOUER:
                    // C'EST ICI QU4ON LANCE LE JEU
                    dispose();
                    MainGUI mainGUI = new MainGUI(nomVille);
                    Thread gameThread = new Thread(mainGUI);
                    gameThread.start();
                    return;

                case ETAT_NOM:
                    cardLayout.show(conteneur, "nom");
                    break;
            }

            panneauMenu.repaint();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private MenuDisplay construirePanneauMenu() {
        MenuDisplay panel = new MenuDisplay();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        panel.add(Box.createVerticalGlue()); 
        
        JLabel titre = new JLabel("");
        titre.setFont(new Font("Arial", Font.PLAIN, 60));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titre);
        panel.add(Box.createVerticalStrut(40));

        Dimension dim = new Dimension(300, 50);

        JButton btnJouer = creerBouton("Jouer");

        btnJouer.setPreferredSize(dim);
        btnJouer.setMaximumSize(dim);
        btnJouer.setMinimumSize(dim);

        btnJouer.addActionListener(e -> etatCourant = ETAT_JOUER);
        panel.add(btnJouer);
        panel.add(Box.createVerticalStrut(20));


        JButton btnRegles = creerBouton("Règles du jeu");
        
        btnRegles.setPreferredSize(dim);
        btnRegles.setMaximumSize(dim);
        btnRegles.setMinimumSize(dim);

        btnRegles.addActionListener(e -> etatCourant = ETAT_REGLES);
        panel.add(btnRegles);

        JButton btnNom = creerBouton("Modifier le nom");
        
        btnNom.setPreferredSize(dim);
        btnNom.setMaximumSize(dim);
        btnNom.setMinimumSize(dim);

        btnNom.addActionListener(e -> etatCourant = ETAT_NOM);
        panel.add(btnNom);


        panel.add(Box.createVerticalGlue()); 


        return panel;
    }

    private JPanel construirePanneauRegles() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titre = new JLabel("Règles du jeu");
        titre.setFont(new Font("Arial", Font.BOLD, 27));
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titre, BorderLayout.NORTH);

        JTextArea texte = new JTextArea();
        texte.setEditable(false);
        texte.setFont(new Font("Arial", Font.PLAIN, 13));
        texte.setLineWrap(true);
        texte.setWrapStyleWord(true);
        texte.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        texte.setText(
            "But du jeu : maintenir tous vos Sims en bonne santé physique et mentale.\n\n" +
            "Chaque Sim possède 3 barres : Faim, Physique, Mental (0 à 100%).\n\n" +
            "-0-25%   : maladie physique ou psychologique.\n\n" +
            "Si les deux barres physique et mental sont sous 25% pendant 3 jours : mort.\n" +
            "Si 3 Sims meurent : fin de partie.\n\n" +
            "Vous pouvez déplacer les Sims en cliquant d'abord sur le Sim,\n" +
            "puis sur la case de destination.\n\n" +
            "Les relations amicales se forment après 20h passées ensemble\n" +
            "dans le même bâtiment. Un ami booste le mental de +5% par heure.\n"+
            "Le niveau augmente tous les 3 jours si les 6  Sims sont en vie.\n"+
            "À chaque niveau, le nombre d'événements par jour augmente de 1"+
            " rendant la partie progressivement plus difficile à gérer."
        );
        panel.add(new JScrollPane(texte), BorderLayout.CENTER);

        JButton btnRetour = creerBouton("Retour");
        btnRetour.addActionListener(e -> etatCourant = ETAT_MENU);
        panel.setBackground(new Color(165, 230, 215));
        panel.add(btnRetour, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel construirePanneauNom() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titre = new JLabel("Modifier le nom de la Ville");
        titre.setFont(new Font("Arial", Font.BOLD, 27));
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titre, BorderLayout.NORTH);

        JTextField nomChoisi = new JTextField(nomVille);
        nomChoisi.setFont(new Font("Arial", Font.PLAIN, 22));
        panel.add(nomChoisi, BorderLayout.CENTER);
        

        JButton btnRetour = creerBouton("Valider et retour au menu");
        btnRetour.addActionListener(e -> {nomVille = nomChoisi.getText() ;etatCourant = ETAT_MENU;});
        panel.setBackground(new Color(165, 230, 215));
        panel.add(btnRetour, BorderLayout.SOUTH);

        return panel;
    }

}