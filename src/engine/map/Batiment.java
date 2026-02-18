package engine.map;
/**
 * Le bâtiment est caractérise par sa positon et son nom
 * 
 * @author Loukili
 *
 */
public class Batiment {

	private Block position;
	private String nomBatiment;
	private int couleur ;//  a enlever 
	
	public Batiment(Block position, String nom, int coul) {
		this.position = position;
		this.nomBatiment=nom;
		this.couleur=coul ;
	}

	public Block getPosition() {
		return position;
	}

	public String getNomBatiment() {
		return nomBatiment;
	}

	public int getCouleur(){
		return couleur ;
	}
}
