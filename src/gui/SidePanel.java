package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import chrono.Chronometer;
import config.GameConfiguration;

public class SidePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Chronometer chronometer;
	private JLabel timeLabel;
	private JLabel dayLabel;
	private int dayCount = 1;

	public SidePanel(Chronometer chronometer) {
		this.chronometer = chronometer;
		this.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH, GameConfiguration.WINDOW_HEIGHT));
		
		this.setLayout(new GridLayout(20, 1));

		dayLabel = new JLabel("JOUR " + dayCount, SwingConstants.CENTER);
		dayLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
		timeLabel.setFont(new Font("Monospaced", Font.BOLD, 25));

		add(dayLabel);
		add(timeLabel);
		
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
}