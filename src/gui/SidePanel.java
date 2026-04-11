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
import engine.process.RelationsRepository;

public class SidePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Chronometer chronometer;
    private JLabel timeLabel;
    private JLabel dayLabel;
    private JLabel globalPannelLabel;

    // menu de selction Sim qui contient uniquement les nome des sims et pas les istances des sims

    private JProgressBar globalBar = new JProgressBar(0, 100);
    private JLabel globalVal = new JLabel("0%");
    private Sims sims;
    private String selectedSimName;
    
    private JTextArea detailRoutine = new JTextArea();
 
    private ArrayList<String> derniersEvenementsList;
    private JTextArea detailEvenements = new JTextArea(10,10);
    
    private JTextArea relationsArea = new JTextArea(4, 10);

    public SidePanel(Chronometer chronometer, String[] array, Sims sims, ArrayList<String> derniersEvenements) {
        this.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH, GameConfiguration.WINDOW_HEIGHT));
        this.setLayout(new GridLayout(20, 2));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    	
    	this.chronometer = chronometer;
        this.sims = sims;
        this.derniersEvenementsList=derniersEvenements;
        

        

        dayLabel = new JLabel("JOUR " + chronometer.getDay(), SwingConstants.CENTER);
        dayLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        

                                    
        add(new JLabel("Le nom de la ville"));
        add(dayLabel);
        add(timeLabel);
        
        
        //Le nom du sim selectioné
        if (array != null && array.length > 0) {
            selectedSimName = array[0];
        }
        globalPannelLabel=new JLabel("État global de "+ selectedSimName);
        add(globalPannelLabel);
        
        JPanel globalPanel = new JPanel(new BorderLayout(5, 0));
        globalBar.setStringPainted(false);
        globalPanel.add(globalBar, BorderLayout.CENTER);
        globalPanel.add(globalVal, BorderLayout.EAST);
        add(globalPanel);
        
        globalPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openDetailDialog();
            }
        });
        
        JButton routineBtn = new JButton("Voir la routine");
        routineBtn.addActionListener(e -> openRoutineDialog());
        add(routineBtn);

        JButton eventButton = new JButton("Alertes Sims en cours");
        eventButton.addActionListener(e -> openEventDialog());
        add(eventButton);
        
        //pour les relations amicales
        add(new JLabel("Relations :"));
        relationsArea.setEditable(false);
        relationsArea.setLineWrap(true);
        relationsArea.setWrapStyleWord(true);
        relationsArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane relationsScroll = new JScrollPane(relationsArea);
        add(relationsScroll);
        
        //add(detailRoutine);
        detailEvenements.setEditable(false);
        detailEvenements.setLineWrap(true);
        detailEvenements.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(detailEvenements);
        scrollPane.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH - 30, 150));
        add(scrollPane);

        nextRound();
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

    private void openEventDialog() {


        String buffer = "" ;

        for (Sim sim : sims.getSims().values()){
            if(!sim.getEtats().estVide())
                buffer+= sim.getNom() + " : " + sim.getEtats().toString() + "\n" ;
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
        
        globalPannelLabel.setText("État global de "+ selectedSimName);
        
        Sim s = sims.getSim(getSelectedSim());
        if (s != null) {
            int score = s.getScoreGlobal();
            globalBar.setValue(score);
            globalVal.setText(score + "%");
            
            
            detailRoutine.setText(s.getRoutine().toString());
            
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
    
    private String printEventList() {
    	StringBuilder buffer= new StringBuilder("Les dernier evenements: \n");
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