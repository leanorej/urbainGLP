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

	public GameDisplay(Map map, Ville ville, MobileSimManager SimManager) {
		this.map = map;
		this.ville=ville;
		this.SimManager = SimManager;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		paintStrategy.paint(map, g);
		
		
		for(Batiment batiment : ville.getBatimentsList()) {
            paintStrategy.paint(map, batiment, g);
        }

		
		
        
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		
		//A REGROUPER DANS UNE METHODE A METTRE DANS PAINT STRATEGY
		int sizeBlock=GameConfiguration.BLOCK_SIZE;
		g2.drawImage(GameUtility.readImage("src/images/maison1.png"),0,  5* sizeBlock, 4*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/maison1.png"),0,  10* sizeBlock, 4*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/maison1.png"),0,  15* sizeBlock, 4*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/imm.png"),11*sizeBlock,  7* sizeBlock, 6*sizeBlock,5*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/piscine.png"),15*sizeBlock,0, 5*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/boulangerie.png"),6*sizeBlock,  8* sizeBlock, 3*sizeBlock,3*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/lycee.png"),0*sizeBlock,   0*sizeBlock, 7*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/parc.png"),6*sizeBlock,  13* sizeBlock, 7*sizeBlock,6*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/usine.png"),22*sizeBlock,  0* sizeBlock, 8*sizeBlock,4*sizeBlock, null);
		
		//dessin des portes
		for(Batiment batiment : ville.getBatimentsList()) {
            paintStrategy.paint(batiment, g);
        }
		
		for(Sim sim : SimManager.getSims().getSims().values()){
        	paintStrategy.paint2(sim, g2);
        }
        }

		public Block getSimPosition(int x, int y) {
			int line = y / GameConfiguration.BLOCK_SIZE;
			int column = x / GameConfiguration.BLOCK_SIZE;
			return map.getBlock(line, column);
		}

}

