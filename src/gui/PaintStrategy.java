package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random ;

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

	//La base de la map est toute verte 

	public void paint(Map map, Graphics graphics) {
		int blockSize = GameConfiguration.BLOCK_SIZE;
		Block[][] blocks = map.getBlocks();

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				graphics.setColor(Color.GREEN);
				graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				
				if ((lineIndex + columnIndex) % 2 == 0) {
					graphics.setColor(Color.green.darker());
					graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				}
			}
		}
	}
		
	public void paint(Batiment batiment, Graphics graphics) {
		Block position = batiment.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		Random num = new Random() ;

			float r = num.nextFloat();
			float g = num.nextFloat();
			float b = num.nextFloat() ;
		
			graphics.setColor(new Color(r,g,b));
			graphics.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
        

		
	}

	public void paintRoute(Map map, Graphics graphics){

	}
	
	

	public void paint(Sim sim, Graphics graphics){
		Block position = sim.getPositionCourante();
		int blockSize = GameConfiguration.BLOCK_SIZE;
		
		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.white);
		graphics.fillOval(x * blockSize + blockSize/4, y * blockSize + blockSize/4, blockSize/2, blockSize/2);
	}
}