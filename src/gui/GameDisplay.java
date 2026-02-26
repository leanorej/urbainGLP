package gui;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Sim;
import engine.process.MobileInterface;

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
	private MobileInterface manager;

	public GameDisplay(Map map, Ville ville, MobileInterface manager) {
		this.map = map;
		this.ville=ville;
		this.manager = manager;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintStrategy.paint(map, g);


		
		for(Batiment batiment : ville.getBatiments()) {
            paintStrategy.paint(batiment, g);
        }
		
		
        
        for(Sim sim : manager.getSims()){
        	paintStrategy.paint(sim, g);
        }
	}

}
