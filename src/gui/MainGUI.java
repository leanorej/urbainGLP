package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import config.GameConfiguration;
import engine.map.Block;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sim;
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
	private MobileSimManager SimMenager;
	private GameDisplay dashboard;
	private SidePanel sidePanel;
	private Chronometer chronometer;
	int dernierHeure = -1;

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
		sims = GameBuilder.initializeSims(ville);
		
		SimMenager = GameBuilder.buildInitMobile(sims, chronometer, ville);
		
		String[] simsKey = sims.getSims().keySet().toArray(new String[0]);
		sidePanel = new SidePanel(chronometer, simsKey, sims);

		dashboard = new GameDisplay(map, ville, SimMenager, sidePanel);
		MouseControls mouseControls = new MouseControls();
		dashboard.addMouseListener(mouseControls);
	
		dashboard.setPreferredSize(new Dimension(GameConfiguration.NUMERO_BLOC_LARGEUR * GameConfiguration.BLOCK_SIZE, GameConfiguration.WINDOW_HEIGHT));
		sidePanel.setPreferredSize(new Dimension(GameConfiguration.SIDE_PANEL_WIDTH, GameConfiguration.WINDOW_HEIGHT));

		contentPane.add(dashboard, BorderLayout.WEST);
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

	        for(int i = 0 ; i < 50 ; i++){
	            chronometer.increment();
	            SimMenager.updateBesoins(); 
	        }

	        if (chronometer.getHour().getValue() == 0 && chronometer.getMinute().getValue() == 0 && chronometer.getSecond().getValue() == 0) {
	            sidePanel.incrementDay();
	        }

	        int heureCourante = chronometer.getHour().getValue();
	        if (heureCourante != dernierHeure) {
	            SimMenager.nextMove(heureCourante);
	            dernierHeure = heureCourante;
	        }

	        sidePanel.nextRound();
	        dashboard.repaint();
	    }
	}

	private class MouseControls implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			Block nvlPosition = dashboard.getSimPosition(x , y);
			SimMenager.simMove(nvlPosition);
			dashboard.repaint();
		}
		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
}