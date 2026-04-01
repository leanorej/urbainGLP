package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random ;

import javax.imageio.ImageIO;

import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Sim;
import engine.process.GameUtility;

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
				y = 0 ; x = 15 ; largeur = 4 ; longueur = 5;
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
				y = 14 ; x = 19 ; largeur = 5 ; longueur = 5 ;
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
	public void paint(Graphics2D g2) {
		int sizeBlock=GameConfiguration.BLOCK_SIZE;
		g2.drawImage(GameUtility.readImage("src/images/maison1.png"),0,  5* sizeBlock, 4*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/maison1.png"),0,  10* sizeBlock, 4*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/maison1.png"),0,  15* sizeBlock, 4*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/imm.png"),11*sizeBlock,  7* sizeBlock, 6*sizeBlock,5*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/piscine.png"),15*sizeBlock,0, 5*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/boulangerie.png"),6*sizeBlock,  8* sizeBlock, 3*sizeBlock,3*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/lycee.png"),0*sizeBlock,   0*sizeBlock, 7*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/parc.png"),6*sizeBlock,  13* sizeBlock, 7*sizeBlock,6*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/mairie.png"),19*sizeBlock,  8* sizeBlock, 4*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/cinema.png"),25*sizeBlock,  13* sizeBlock, 4*sizeBlock,6*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/salle.png"),14*sizeBlock,  15* sizeBlock, 4*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/hopital.png"),19*sizeBlock,  14* sizeBlock, 5*sizeBlock,5*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/mall.png"),24*sizeBlock,  6* sizeBlock, 5*sizeBlock,6*sizeBlock, null);
		
		g2.drawImage(GameUtility.readImage("src/images/lycee.png"),8*sizeBlock,   0*sizeBlock, 6*sizeBlock,4*sizeBlock, null);
		g2.drawImage(GameUtility.readImage("src/images/usine.png"),22*sizeBlock,  0* sizeBlock, 8*sizeBlock,4*sizeBlock, null);
	}

		

//SUPRIMEEEEEEER
	public void paint(Sim sim, Graphics graphics){
		Block position = sim.getPositionCourante();
		int blockSize = GameConfiguration.BLOCK_SIZE;
		
		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.blue);
		graphics.fillOval(x * blockSize + blockSize/4, y * blockSize + blockSize/4, blockSize/2, blockSize/2);
	}
	
	
	//Amin
	public void paint2(Sim sim, Graphics2D graphics2){
		graphics2.setColor(Color.BLACK);
		graphics2.setStroke(new BasicStroke(6));


		Block position = sim.getPositionCourante();
		int blockSize = GameConfiguration.BLOCK_SIZE;
		
		int y = position.getLine()*blockSize;
		int x = position.getColumn()*blockSize;

		graphics2.setColor(Color.blue);
		graphics2.fillOval(x + blockSize/4, y  + blockSize/4, blockSize/2, blockSize/2);
		
		// Attention : don't call trainManager.getId(), because it will return the ID of the thread (not the train).
		graphics2 .setFont(new Font("Dialog", Font.PLAIN, 20));
		graphics2.drawString(sim.getNom(), x-blockSize/4, y);
		graphics2.drawImage(GameUtility.readImage("src/images/sim.jpg"),  x+blockSize/4, y, 25, 35, null);
	}


	public void paintC(Sim sim, Graphics graphics){
		Block position = sim.getPositionCourante();
		int blockSize = GameConfiguration.BLOCK_SIZE;
		
		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.red);
		graphics.drawOval(x * blockSize + blockSize/4 - 2, y * blockSize - 2, 29, 39);
	}

	public void paintS(Sim sim, Graphics graphics){
		Block position = sim.getPositionCourante();
		int blockSize = GameConfiguration.BLOCK_SIZE;
		
		int y = position.getLine();
		int x = position.getColumn();

		graphics.setColor(Color.blue);
		graphics.drawOval(x*blockSize + blockSize/4 - 2, y*blockSize - 2, 29, 39);
	}
	
}