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

		graphics.setColor(Color.white);
		graphics.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
		
	}

	public void paint(Map map, Batiment batiment, Graphics graphics){
		int blockSize = GameConfiguration.BLOCK_SIZE;
		Block[][] blocks = map.getBlocks();

		int y ;
		int x ;
		int largeur ;
		int longueur ;

		String nom = batiment.getNomBatiment() ;
		switch(nom){
			case "maison1" :
				y = 5 ; x = 0 ; largeur = 4 ; longueur = 4 ;
				break ;

			case "maison2" :
				y = 10 ; x = 0 ; largeur = 4 ; longueur = 4 ;
				break ;
			
			case "maison3" :
				y = 15 ; x = 0 ; largeur = 4 ; longueur = 4 ;
				break ;

			case "imm" :
				y = 7 ; x = 11 ; largeur = 5 ; longueur = 6 ;
				break ;
			
			case "boulangerie" :
				y = 8 ; x = 6 ; largeur = 3 ; longueur = 3 ;
				break ;

			case "usine" :
				y = 0 ; x = 22 ; largeur = 4 ; longueur = 8 ;
				break ;

			case "piscine" :
				y = 0 ; x = 15 ; largeur = 4 ; longueur = 3 ;
				break ;
			
			case "salle" :
				y = 15 ; x = 14 ; largeur = 4 ; longueur = 4 ;
				break ;
			
			case "ecole" :
				y = 0 ; x = 8 ; largeur = 4 ; longueur = 6 ;
				break ;

			case "lycee" :
				y = 0 ; x = 0 ; largeur = 4 ; longueur = 7 ;
				break ;
			
			case "parc" :
				y = 13 ; x = 6 ; largeur = 6 ; longueur = 7 ;
				break ;
			
			case "mairie" :
				y = 8 ; x = 19 ; largeur = 4 ; longueur = 4 ;
				break ;
			
			case "mall" :
				y = 6 ; x = 24 ; largeur = 6 ; longueur = 5 ;
				break ;
			
			case "cinema" :
				y = 13 ; x = 25 ; largeur = 6 ; longueur = 4 ;
				break ;
			
			case "hopital" :
				y = 15 ; x = 19 ; largeur = 5 ; longueur = 5 ;
				break ;

			default :
				y = 0 ; x = 0 ; largeur = 0 ; longueur = 0 ;
				break ;

		}

		for (int lineIndex = y ; lineIndex < y+largeur ; lineIndex++) {
			for (int columnIndex = x ; columnIndex < x+longueur ; columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];
				graphics.setColor(Color.PINK);
				graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
			}
		}

	}


	public void paint(Sim sim, Graphics graphics){
		Block position = sim.getPositionCourante();
		int blockSize = GameConfiguration.BLOCK_SIZE;
		
		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.blue);
		graphics.fillOval(x * blockSize + blockSize/4, y * blockSize + blockSize/4, blockSize/2, blockSize/2);
	}
}