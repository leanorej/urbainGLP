package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import chrono.Chronometer;
import config.GameConfiguration;
import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.donnee.RelationAmicale;
import engine.process.EtatSimManager;
import engine.process.NiveauManager;
import engine.process.RelationsRepository;

public class SidePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Chronometer chronometer;
    private JLabel timeLabel;
    private JLabel dayLabel;
    private JLabel niveauLabel;
    private JLabel globalPannelLabel;
    private JLabel globalVillePannelLabel;
    private JLabel nomRoutineLabel;
    private EtatSimManager EtatManager;
    private NiveauManager NiveauManager;
    



    // menu de selction Sim qui contient uniquement les nome des sims et pas les istances des sims

    private JProgressBar globalBar = new JProgressBar(0, 100);
    private JProgressBar globalBarVille = new JProgressBar(0, 100);
    private JLabel globalVal = new JLabel("0%");
    private JLabel globalValVille = new JLabel("0%");
    private Sims sims;
    private String selectedSimName;
    
 
    private ArrayList<String> derniersEvenementsList;
    private JTextArea detailEvenements = new JTextArea(10,10);
    
    private JTextArea relationsArea = new JTextArea(4, 10);
    private JTextArea routineArea = new JTextArea(4, 10);



    public SidePanel(Chronometer chronometer, String[] array, Sims sims, ArrayList<String> derniersEvenements, EtatSimManager EtatManager, NiveauManager NiveauManager) {
        this.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH, GameConfiguration.WINDOW_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.chronometer = chronometer;
        this.sims = sims;
        this.derniersEvenementsList = derniersEvenements;
        this.EtatManager = EtatManager;
        this.NiveauManager = NiveauManager ;

        // Nom ville
        JLabel nom = new JLabel(MenuGUI.nomVille, SwingConstants.LEFT);
        nom.setFont(new Font("Monospaced", Font.BOLD, 40));
        nom.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(nom);

        // Jour
        dayLabel = new JLabel("JOUR " + chronometer.getDay(), SwingConstants.LEFT);
        dayLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        dayLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(dayLabel);


        niveauLabel = new JLabel("Niveau " + NiveauManager.getNiveau(), SwingConstants.LEFT);
        niveauLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        niveauLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(niveauLabel);


        // Heure
        timeLabel = new JLabel("00:00:00", SwingConstants.LEFT);
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(timeLabel);

        // Etat global ville
        globalVillePannelLabel = new JLabel("État global de la Ville");
        globalVillePannelLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        globalVillePannelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(globalVillePannelLabel);

        JPanel globalPanelVille = new JPanel(new BorderLayout(5, 0));
        globalBarVille.setStringPainted(false);
        globalPanelVille.add(globalBarVille, BorderLayout.CENTER);
        globalPanelVille.add(globalValVille, BorderLayout.EAST);
        globalPanelVille.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        globalPanelVille.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(globalPanelVille);

        // Etat global sim
        if (array != null && array.length > 0) selectedSimName = array[0];
        globalPannelLabel = new JLabel("État global de " + selectedSimName);
        globalPannelLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        globalPannelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(globalPannelLabel);

        JPanel globalPanel = new JPanel(new BorderLayout(5, 0));
        globalBar.setStringPainted(false);
        globalPanel.add(globalBar, BorderLayout.CENTER);
        globalPanel.add(globalVal, BorderLayout.EAST);
        globalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        globalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        globalPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { openDetailDialog(); }
        });
        add(globalPanel);

        // Routine
        nomRoutineLabel = new JLabel("Routine de "+selectedSimName+":");
        nomRoutineLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
        nomRoutineLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(nomRoutineLabel);

        routineArea.setEditable(false);
        routineArea.setLineWrap(true);
        routineArea.setWrapStyleWord(true);
        routineArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane routineScroll = new JScrollPane(routineArea);
        routineScroll.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH - 20, 100));
        routineScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        routineScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(routineScroll);

        // Bouton alertes
        JButton eventButton = new JButton("Alertes Sims en cours");
        eventButton.addActionListener(e -> openEventDialog());
        eventButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        eventButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(eventButton);

        // Relations
        JLabel nomRe = new JLabel("Relations :");
        nomRe.setFont(new Font("Monospaced", Font.BOLD, 13));
        nomRe.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(nomRe);

        relationsArea.setEditable(false);
        relationsArea.setLineWrap(true);
        relationsArea.setWrapStyleWord(true);
        relationsArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane relationsScroll = new JScrollPane(relationsArea);
        relationsScroll.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH - 20, 100));
        relationsScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        relationsScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(relationsScroll);

        
        
        // Evenements
        JLabel nomE = new JLabel("Les derniers evenements :");
        nomE.setFont(new Font("Monospaced", Font.BOLD, 13));
        nomE.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(nomE);
        
        detailEvenements.setEditable(false);
        detailEvenements.setLineWrap(true);
        detailEvenements.setWrapStyleWord(true);
        detailEvenements.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(detailEvenements);
        scrollPane.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH - 20, 200));
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(scrollPane);
        
        //button retour au menu
        JButton menuButton = new JButton("Recommencer le jeux");
        menuButton.addActionListener(e -> returnToMenu());
        menuButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        menuButton.setBackground(Color.RED);
        menuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(menuButton, BorderLayout.SOUTH);

        setBackground(new Color(165, 230, 215));
        nextRound();
    }

    private void returnToMenu() {
    		SwingUtilities.getWindowAncestor(this).dispose();
	        MenuGUI menu = new MenuGUI();
	        Thread menuThread = new Thread(menu);
	        menuThread.start();
	}

	private void openDetailDialog() {
        Sim s = sims.getSim(getSelectedSim());
        if (s == null) return;

        JProgressBar faimB = new JProgressBar(0, 100);
        JProgressBar physB = new JProgressBar(0, 100);
        JProgressBar mentB = new JProgressBar(0, 100);
        JLabel faimV = new JLabel(), physV = new JLabel(), mentV = new JLabel();

        Object[] message = {
            createLine("Faim", faimB, faimV),
            createLine("Physique", physB, physV),
            createLine("Mental", mentB, mentV)
        };

        // mise à jour en temps réel
        Timer timer = new Timer(100, ae -> {
            faimB.setValue(s.getFaim().getValue()); faimV.setText(s.getFaim().getValue() + "%");
            physB.setValue(s.getPhysique().getValue()); physV.setText(s.getPhysique().getValue() + "%");
            mentB.setValue(s.getMental().getValue()); mentV.setText(s.getMental().getValue() + "%");
        });
        timer.start();

        JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE);
        JDialog dialog = pane.createDialog(this, "Détails : " + s.getNom());
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) { timer.stop(); }
        });
        dialog.setVisible(true);
    }
    
    private JPanel createLine(String name, JProgressBar bar, JLabel val) {
        JPanel p = new JPanel(new BorderLayout(5, 0));
        p.add(new JLabel(name), BorderLayout.WEST);
        p.add(bar, BorderLayout.CENTER);
        p.add(val, BorderLayout.EAST);
        return p;
    }
    /*
    private void openRoutineDialog() {
        Sim s = sims.getSim(getSelectedSim());
        if (s == null) return;
        String textRoutine= "Détails routine : " + s.getNom();
        if (chronometer.isWeekend()) {
        	textRoutine = "ROUTINE WEEKEND\n" + s.getRoutineWeekEnd().toString();
        } else {
        	textRoutine = "ROUTINE SEMAINE\n" + s.getRoutine().toString();
        }
        JTextArea textArea = new JTextArea(textRoutine);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        
        JOptionPane pane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE);
        JDialog dialog = pane.createDialog(this, "Détails : " + s.getNom());
        
        dialog.setVisible(true);
        
    }
*/
    private void openEventDialog() {


        String buffer = "" ;

        for (Sim sim : sims.getSims().values()){
            if(!sim.getEtats().estVide())
                buffer+= sim.getNom() + " : " + sim.getEtats().toString() + "\n" ;

            if (sim.getEtats().getEtats().contains("faim") ||
                sim.getEtats().getEtats().contains("fatigue") ||
                sim.getEtats().getEtats().contains("triste")) {
                buffer += " ATTENTION! " + sim.getJourRestant().getValue() + " jour(s) restant(s)\n";
            }
        }
        buffer += "";
        JTextArea textArea ;
        if (buffer.equals("")){
            textArea = new JTextArea("Aucune alerte Sim en cours");
        }
        else {
            textArea = new JTextArea(buffer);
        }

        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        
        JOptionPane pane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE);
        JDialog dialog = pane.createDialog(this, "Alertes Sims en cours: ");
        
        dialog.setVisible(true);
        
    }

    
    public void nextRound() {
        if (chronometer != null) {
            timeLabel.setText(chronometer.toString());
            dayLabel.setText("JOUR " + chronometer.getDay());
        }

        globalVillePannelLabel.setText("État global de la Ville");
        int scoreGlobal = EtatManager.updateEtatGeneral() ;
        globalBarVille.setValue(scoreGlobal);
        globalValVille.setText(scoreGlobal + "%");
        majCoulBarre(globalBarVille);

        
        globalPannelLabel.setText("État global de "+ selectedSimName);
        nomRoutineLabel.setText("Routine de "+selectedSimName+":");
        
        Sim s = sims.getSim(getSelectedSim());
        if (s != null) {
            int score = s.getScoreGlobal();
            globalBar.setValue(score);
            globalVal.setText(score + "%");
            majCoulBarre(globalBar);

            
            
            
            routineArea.setText(s.getRoutine().toString());
            
            detailEvenements.setText(printEventList());
            
            relationsArea.setText(buildRelationsText(s.getNom()));
        }
    }

    private String buildRelationsText(String nomSim) {
        RelationsRepository relRep = RelationsRepository.getInstance();
        ArrayList<RelationAmicale> relations = relRep.getRelationsDe(nomSim);

        StringBuilder sb = new StringBuilder();

        for (RelationAmicale r : relations) {
            int heures = r.getHeuresPasseesEnsemble();
            // on n'affiche rien si jamais croise
            if (heures == 0) continue;

            String nomAutre = r.getNomSim1().equals(nomSim) ? r.getNomSim2() : r.getNomSim1();

            if (r.sontAmis()) {
                sb.append("Amis avec ").append(nomAutre)
                  .append(" depuis ").append(heures).append("h\n");
            } else {
                // relation en cours mais pas encore amis
                sb.append(nomAutre).append(" : ")
                  .append(heures).append("h / 20h\n");
            }
        }

        return sb.toString();
    }

    private void majCoulBarre(JProgressBar barreGenerale){
        if (barreGenerale.getValue()>=50){
            barreGenerale.setForeground(Color.GREEN);
        }
        else if (barreGenerale.getValue()>=25){
            barreGenerale.setForeground(Color.ORANGE);
        }
        else{
            barreGenerale.setForeground(Color.RED);
        }
    }
    
    private String printEventList() {
    	StringBuilder buffer= new StringBuilder();
    	int dim=derniersEvenementsList.size()-1;
    	for(int i=dim; i>=0; i-- ) {
    		buffer.append(derniersEvenementsList.get(i)+"\n");
    	}
		return buffer.toString();
    	
    }
    
    public void setSelectedSim(String name) {
        this.selectedSimName = name;
    }

    public String getSelectedSim() {
        return selectedSimName;
    }


}