package gui;

import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final int ETAT_MENU    = 0;
    private static final int ETAT_REGLES  = 1;
    private static final int ETAT_JOUER   = 2;

    private int etatCourant = ETAT_MENU;
    private JPanel panneauMenu;
    private JPanel panneauRegles;
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
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        conteneur = new JPanel(cardLayout);

        panneauMenu   = construirePanneauMenu();
        panneauRegles = construirePanneauRegles();

        conteneur.add(panneauMenu,   "menu");
        conteneur.add(panneauRegles, "regles");

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
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private JPanel construirePanneauMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        JLabel titre = new JLabel("URBAIN");
        titre.setFont(new Font("Arial", Font.PLAIN, 60));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titre);
        panel.add(Box.createVerticalStrut(40));

        JButton btnJouer = creerBouton("Jouer");
        btnJouer.addActionListener(e -> etatCourant = ETAT_JOUER);
        panel.add(btnJouer);
        panel.add(Box.createVerticalStrut(20));


        JButton btnRegles = creerBouton("Règles du jeu");
        btnRegles.addActionListener(e -> etatCourant = ETAT_REGLES);
        panel.add(btnRegles);

        return panel;
    }

    private JPanel construirePanneauRegles() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titre = new JLabel("Règles du jeu");
        titre.setFont(new Font("Arial", Font.PLAIN, 22));
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
            "75-100% : le Sim est efficace.\n" +
            "50-75%  : le Sim est normal.\n" +
            "25-50%  : le Sim est fatigué et lent.\n" +
            "0-25%   : maladie physique ou psychologique.\n\n" +
            "Si les deux barres physique et mental sont sous 25% pendant 3 jours : mort.\n" +
            "Si 3 Sims meurent : fin de partie.\n\n" +
            "Vous pouvez déplacer les Sims en cliquant d'abord sur le Sim,\n" +
            "puis sur la case de destination.\n\n" +
            "Les relations amicales se forment après 20h passées ensemble\n" +
            "dans le même bâtiment. Un ami booste le mental de +5% par heure."
        );
        panel.add(new JScrollPane(texte), BorderLayout.CENTER);

        JButton btnRetour = creerBouton("Retour");
        btnRetour.addActionListener(e -> etatCourant = ETAT_MENU);
        panel.add(btnRetour, BorderLayout.SOUTH);

        return panel;
    }

}