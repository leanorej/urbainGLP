package config;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class GameConfiguration {
	
	public static final int BLOCK_SIZE = 100;
	
	public static final int WINDOW_WIDTH = 9*BLOCK_SIZE;
	public static final int WINDOW_HEIGHT = 7*BLOCK_SIZE;
	
	public static final int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;
	public static final int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;
	
	public static final int GAME_SPEED = 1000;
}
