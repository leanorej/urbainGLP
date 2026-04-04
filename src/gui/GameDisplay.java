package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Sim;
import engine.process.GameUtility;
import engine.process.MobileSimManager;
import engine.map.Ville;


/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr Loukili JEAN
 *
 */
public class GameDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map map;
	private Ville ville;
	private PaintStrategy paintStrategy = new PaintStrategy();
	private MobileSimManager SimManager;
	private SidePanel sidePanel ;

	public GameDisplay(Map map, Ville ville, MobileSimManager SimManager, SidePanel sidePanel) {
		this.map = map;
		this.ville=ville;
		this.SimManager = SimManager;
		this.sidePanel = sidePanel ;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		paintStrategy.paint(map, g);
		
		
		for(Batiment batiment : ville.getBatimentsList()) {
            paintStrategy.paint(map, batiment, g);
        }

		
		
        
		Graphics2D g2 = (Graphics2D) g;
		
		//dessine tout les images de batiment
		paintStrategy.paint(g2);
		
		for(Batiment batiment : ville.getBatimentsList()) {
            paintStrategy.paint(batiment, g);
        }
		
		for(Sim sim : SimManager.getSims().getSims().values()){
        	paintStrategy.paint2(sim, g2);
        }


		String nom = sidePanel.getSelectedSim() ;
		Sim simSelected = SimManager.getSims().getSim(nom) ;
		paintStrategy.paintS(simSelected, g2);

		Sim simChoisi = SimManager.getSimChoisi();

		if (simChoisi != null){
			paintStrategy.paintC(simChoisi, g2);
		}
	}

		public Block getSimPosition(int x, int y) {
			int line = y / GameConfiguration.BLOCK_SIZE;
			int column = x / GameConfiguration.BLOCK_SIZE;
			return map.getBlock(line, column);
		}

}

