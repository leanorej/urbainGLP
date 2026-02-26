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
	
	public Batiment(Block position, String nom) {
		this.position = position;
		this.nomBatiment=nom;
	}

	public Block getPosition() {
		return position;
	}

	public String getNomBatiment() {
		return nomBatiment;
	}

}
