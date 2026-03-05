package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import chrono.Chronometer;
import config.GameConfiguration;
import engine.mobile.Sim;

public class SidePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Chronometer chronometer;
	private JLabel timeLabel;
	private JLabel dayLabel;
	private int dayCount = 1;

	//menu de selction Sim qui contient uniquement les nome des sims et pas les istances des sims
	private JComboBox<String> comboBox;
	
	
	//update j'ai ajouté deux parametre un c'est la list des prenom des Sims e l'autre c'est l'action listener
	public SidePanel(Chronometer chronometer, String[] array, ActionListener actionListener) {
		this.chronometer = chronometer;
		this.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH, GameConfiguration.WINDOW_HEIGHT));
		
		this.setLayout(new GridLayout(20, 1));

		dayLabel = new JLabel("JOUR " + dayCount, SwingConstants.CENTER);
		dayLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
		timeLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
		
		comboBox = new JComboBox<String>(array);
		comboBox.addActionListener(actionListener);
		
		add(dayLabel);
		add(timeLabel);
		//amin
		add(comboBox);
		
		updateUI();
	}

	public void updateUI() {
		if (chronometer != null) {
			timeLabel.setText(chronometer.toString());
		}
	}
	
	public void incrementDay() {
		dayCount++;
		dayLabel.setText("JOUR " + dayCount);
	}
	//methode pour comboBox qui retourne l'element selectioné
	public String getSelectedSim() {
	    return  (String) comboBox.getSelectedItem();
	}
}