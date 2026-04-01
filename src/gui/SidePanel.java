package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import chrono.Chronometer;
import config.GameConfiguration;
import engine.mobile.Sim;
import engine.mobile.Sims;

public class SidePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Chronometer chronometer;
    private JLabel timeLabel;
    private JLabel dayLabel;
    private int dayCount = 1;

    // menu de selction Sim qui contient uniquement les nome des sims et pas les istances des sims
    private JComboBox<String> comboBox;
    private JProgressBar globalBar = new JProgressBar(0, 100);
    private JLabel globalVal = new JLabel("0%");
    private Sims sims;
    
    private JTextArea detailRoutine = new JTextArea("Routine de: ");

    public SidePanel(Chronometer chronometer, String[] array, Sims sims) {
        this.chronometer = chronometer;
        this.sims = sims;
        this.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH, GameConfiguration.WINDOW_HEIGHT));

        this.setLayout(new GridLayout(20, 1));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        dayLabel = new JLabel("JOUR " + dayCount, SwingConstants.CENTER);
        dayLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        
        comboBox = new JComboBox<String>(array);
                                    
        add(new JLabel("Le nom de la ville"));
        add(dayLabel);
        add(timeLabel);
        add(comboBox);
        
        //nouveaux elts
        add(new JLabel("État global du Sim"));
        
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

        JTextArea textArea = new JTextArea(s.getRoutine().toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        
        JOptionPane pane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE);
        JDialog dialog = pane.createDialog(this, "Détails : " + s.getNom());
        
        dialog.setVisible(true);
        
    }
    
    public void nextRound() {
        if (chronometer != null) {
            timeLabel.setText(chronometer.toString());
        }
        Sim s = sims.getSim(getSelectedSim());
        if (s != null) {
            int score = s.getScoreGlobal();
            globalBar.setValue(score);
            globalVal.setText(score + "%");
            
            detailRoutine.setText(s.getRoutine().toString());
        }
    }
    
    public void incrementDay() {
        dayCount++;
        dayLabel.setText("JOUR " + dayCount);
    }
    
    public String getSelectedSim() {
        return (String) comboBox.getSelectedItem();
    }

}