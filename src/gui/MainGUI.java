package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
	int dernierHeure = -1;//j'en ai besoin pour faire moin de verification que de heure en heure

	public MainGUI(String title) {
		super(title);
		init();
	}
	
	/*Action listener pour l'instant quand on 
	*selectionne un sim il bouge vers un block fixé en coord 6,7
	*donc il faut programmer le actionListener (voir dans AirCraft->MainGui->MouseControls)
	*
	*Aussi je dois corriger un bug dans mobile menager ou pas car si on deplace le sim
	*celui la il va rester jusqua la prochaine heure pui il va reprendre sa routine
	*ducoup ce action listener est a enlever
    */
	private ActionListener actListener = e -> {
        String selected = sidePanel.getSelectedSim();//en prends le nom du sim selctionné
        if (selected != null) {
        	//on le place en position 67
        	SimMenager.getSims().getSim(selected).setPositionCourante(new Block(6,7));;
        }
    };
    
	private void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		chronometer = new Chronometer();

		map = GameBuilder.buildMap();
		ville = GameBuilder.buildVille(map);
		//je initialise les sims avec leur routine corrispectives
		sims = GameBuilder.initializeSims(ville);
		SimMenager = GameBuilder.buildInitMobile(sims);
		
		dashboard = new GameDisplay(map, ville, SimMenager);
		
		String[] simsKey = sims.getSims().keySet().toArray(new String[0]);//je prends la list des nom des sims
		sidePanel = new SidePanel(chronometer, simsKey, actListener);
		
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
			
			chronometer.increment();
			
			if (chronometer.getHour().getValue() == 0 && chronometer.getMinute().getValue() == 0 && chronometer.getSecond().getValue() == 0) {
				sidePanel.incrementDay();
			}
			//ON MET à JOUR LA POSITION DE UN SI UNE FOIS PAR HEUR
			int heureCourante = chronometer.getHour().getValue();
		    // on vérifie la routine SEULEMENT si l'heure a changé
		    if (heureCourante != dernierHeure) {
		    	SimMenager.nextMove(heureCourante);
		        dernierHeure = heureCourante;
		    }
		    
			sidePanel.nextRound();
			dashboard.repaint();
		}
	}
	

}