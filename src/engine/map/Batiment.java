package engine.map;

import java.util.ArrayList;

/**
 * Le bâtiment est caractérise par sa positon et son nom
 * 
 * @author Loukili
 *
 */
public class Batiment {

	private Block position;
	private String nomBatiment;
	private int largeur;
	private int longueur;
	
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