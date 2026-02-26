package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import config.GameConfiguration;
import engine.map.Map;
import engine.map.Ville;
import engine.process.GameBuilder;
import engine.process.MobileInterface;
import chrono.Chronometer;

/**
 * Copyright SEDAMOP - Software Engineering
 * * @author tianxiao.liu@cyu.fr Loukili JEAN
 *
 */
public class MainGUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

	private Ville ville;
	
	private MobileInterface manager;

	private GameDisplay dashboard;
	
	private SidePanel sidePanel;
	private Chronometer chronometer;

	public MainGUI(String title) {
		super(title);
		init();
	}

	private void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		chronometer = new Chronometer();

		map = GameBuilder.buildMap();
		ville = GameBuilder.buildVille(map);
		
		manager = GameBuilder.buildInitMobile(map, ville);
		
		dashboard = new GameDisplay(map, ville, manager);
		sidePanel = new SidePanel(chronometer);

		dashboard.setPreferredSize(new Dimension(GameConfiguration.NUMERO_BLOC_LARGEUR * GameConfiguration.BLOCK_SIZE, GameConfiguration.WINDOW_HEIGHT));
		sidePanel.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH, GameConfiguration.WINDOW_HEIGHT));
		
		contentPane.add(dashboard, BorderLayout.CENTER);
		contentPane.add(sidePanel, BorderLayout.EAST);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
			chronometer.increment();
			
			if (chronometer.getHour().getValue() == 0 && chronometer.getMinute().getValue() == 0 && chronometer.getSecond().getValue() == 0) {
				sidePanel.incrementDay();
			}
			
			manager.nextRound();
			sidePanel.updateUI();
			dashboard.repaint();
		}
	}
}