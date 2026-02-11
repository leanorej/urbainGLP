package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Sim;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author Loukili JEAN
 *
 */

public class PaintStrategy {
	//c'est la map c'est a changer
	public void paint(Map map, Graphics graphics) {
		int blockSize = GameConfiguration.BLOCK_SIZE;
		Block[][] blocks = map.getBlocks();

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

					graphics.setColor(Color.GREEN);
					graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				
			}
		}
	}
	
	//maison c'est juste un carre rouge
	public void paint(Batiment batiment, Graphics graphics) {
		Block position = batiment.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		int col = batiment.getCouleur();

		switch (col){
			case 1 : 
				graphics.setColor(Color.RED);
				break;
			case 2 :
				graphics.setColor(Color.RED.darker());
				break;
			case 3 : 
				graphics.setColor(Color.ORANGE);
				break;
			case 4 :
				graphics.setColor(Color.BLACK);
				break;
			case 5 : 
				graphics.setColor(Color.CYAN);
				break;
			case 6 :
				graphics.setColor(Color.YELLOW.brighter());
				break;
			case 7 : 
				graphics.setColor(Color.YELLOW.darker());
				break;
			case 8 :
				graphics.setColor(Color.GREEN.darker());
				break;
			case 9 : 
				graphics.setColor(Color.BLUE);
				break;
			case 10 :
				graphics.setColor(Color.MAGENTA.darker());
				break;
			case 11 :
				graphics.setColor(Color.PINK);
				break;
			case 12 :
				graphics.setColor(Color.WHITE);
				break;
			case 13 :
				graphics.setColor(Color.GRAY);
				break;
			case 14 :
				graphics.setColor(Color.DARK_GRAY);
				break;
		}
		graphics.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
	}

	public void paint(Sim sim, Graphics graphics){
		Block position = sim.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;
		
		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.white);
		graphics.fillOval(x * blockSize + blockSize/4, y * blockSize + blockSize/4, blockSize/2, blockSize/2);
	}
}