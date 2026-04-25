package engine.map;

import java.util.ArrayList;

/**
 *Le bâtiment est caractérise par un bloc, son nom,
 *il occuppe un zone rectangulaire avec sa longuer et longueur
 *à partir du bloc qui l'identifie le bâtiment en haut a guache.
 * 
 *@author JEAN
 *@author LOUKILI
 *@version 1.0
 */
public class Batiment {
	/** Le bloc correspondant au coin supérieur gauche du bâtiment. */
	private Block position;
	private String nomBatiment;
	private int largeur;
	private int longueur;
	
	/**
	 * Crée un bâtiment  
	 * 
	 * @param position le bloc qui identifie sa position (en haut a guache)
	 * @param nom le nom du bâtiment
	 * @param largeur la larguer bâtiment
	 * @param longueur la longuer bâtiment
	 */
	public Batiment(Block position, String nom, int largeur, int longueur) {
		this.position = position;
		this.nomBatiment=nom;
		this.largeur = largeur;
		this.longueur = longueur;
	}

	public Block getPosition() {
		return position;
	}

	public String getNomBatiment() {
		return nomBatiment;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	/**
	 * Vérifie si un bloc donné appartient à la zone occupée par ce bâtiment.
	 * 
	 * @param block le bloc dont on veut verifier si il appartient à la zone du batiment
	 * @return true si le block est dans le bâtiment false sinon
	 */
	public boolean contient(Block block) {
		int ligne = block.getLine();
		int colonne = block.getColumn();
		int ligneMin = position.getLine();
		int colonneMin = position.getColumn();
		int ligneMax = ligneMin + largeur;
		int colonneMax = colonneMin + longueur;
		return ligne >= ligneMin && ligne < ligneMax && colonne >= colonneMin && colonne < colonneMax;
	}

}