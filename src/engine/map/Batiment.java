package engine.map;
/**
 * Le bâtiment est caractérise par ca positon et son nom
 * 
 * @author Loukili
 *
 */
public class Batiment {

	private Block position;
	private String nom;
	
	public Batiment(Block position, String nom) {
		this.position = position;
		this.nom=nom;
	}

	public Block getPosition() {
		return position;
	}

	public String getNom() {
		return nom;
	}
}
