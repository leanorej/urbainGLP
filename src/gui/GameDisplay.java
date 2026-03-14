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

		
		for(Batiment batiment : ville.getBatimentsList()) {
            paintStrategy.paint(batiment, g);
        }
        
		Graphics2D g2 = (Graphics2D) g;
		for(Sim sim : SimManager.getSims().getSims().values()){
        	paintStrategy.paint2(sim, g2);
        }
        }
	}

