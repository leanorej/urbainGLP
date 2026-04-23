package config;

/**
 * Cette classe permet de modifier les valeurs des constantes du jeu et
 * de les centraliser en une seule classe.
 *
 * @author JEAN
 *
 * @version 1.0
 */
public class GameConfiguration {
	
	/**Dimension bloc de la map.*/
	public static final int BLOCK_SIZE = 35;
	/**Larguer du side pannel.*/
	public static final int SIDE_PANEL_WIDTH = 400;
	/**Numéro de ligne (Hauteur)*/
	public static final int NUMERO_BLOC_HAUTEUR=20;
	/**Numéro de colonne (Larguer).*/
	public static final int NUMERO_BLOC_LARGEUR=30;
	/**Larguer fenêtre.*/
	public static final int WINDOW_WIDTH = (NUMERO_BLOC_LARGEUR * BLOCK_SIZE) + SIDE_PANEL_WIDTH;
	/**Longueur fenêtre.*/
	public static final int WINDOW_HEIGHT = NUMERO_BLOC_HAUTEUR*BLOCK_SIZE;
	/**Vitesse de rafraîchissement du jeu (en millisecondes).*/
	public static final int GAME_SPEED = 10;
}
