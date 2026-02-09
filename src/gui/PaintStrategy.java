package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Map;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author Loukili 
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

					graphics.setColor(Color.green.darker());
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

		if(col==1)
			graphics.setColor(Color.red);
		else if (col ==2)
			graphics.setColor(Color.blue);
		else if (col == 3)
			graphics.setColor(Color.black);
		else if (col==4)
			graphics.setColor(Color.yellow);
		else if(col==5)
			graphics.setColor(Color.pink);
		else if (col==6)
			graphics.setColor(Color.cyan);
		else if(col==7)
			graphics.setColor(Color.gray);
		graphics.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
	}
}
