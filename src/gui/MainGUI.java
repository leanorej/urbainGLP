package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import config.GameConfiguration;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sims;
import engine.process.GameBuilder;

import engine.process.MobileSimManager;
import chrono.Chronometer;

/**
 * @author Loukili JEAN
 *
 */
public class MainGUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

	private Ville ville;
	
	private Sims sims;
	
	private MobileSimManager manager;

	private GameDisplay dashboard;
	
	private SidePanel sidePanel;
	private Chronometer chronometer;
	int dernierHeure = -1;//j'en ai besoin pour faire moin de verification que de heure en heure

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
		//je initialise les sims avec leur routine corrispectives
		sims = GameBuilder.initializeSims(ville);
		manager = GameBuilder.buildInitMobile(map, ville,sims);
		
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
			
			int heureCourante = chronometer.getHour().getValue();
		    // on vérifie la routine SEULEMENT si l'heure a changé
		    if (heureCourante != dernierHeure) {
		        manager.nextMove(heureCourante);
		        dernierHeure = heureCourante;
		    }
		    
			sidePanel.updateUI();
			dashboard.repaint();
		}
	}
}