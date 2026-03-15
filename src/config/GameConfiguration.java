package config;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class GameConfiguration {
	
	public static final int BLOCK_SIZE = 35;
	public static final int SIDE_PANEL_WIDTH = 300;
	//numèro di ligne
	public static final int NUMERO_BLOC_HAUTEUR=20;
	//numèro de colonne
	public static final int NUMERO_BLOC_LARGEUR=30;
	public static final int WINDOW_WIDTH = (NUMERO_BLOC_LARGEUR * BLOCK_SIZE) + SIDE_PANEL_WIDTH;
	public static final int WINDOW_HEIGHT = NUMERO_BLOC_HAUTEUR*BLOCK_SIZE;
	
	public static final int GAME_SPEED = 50;
}
