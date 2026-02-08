package engine.map;
/**
 * regroupe tout les batiments
 * 
 * @author Loukili
 *
 */
import java.util.ArrayList;

public class Ville {
	//il manque l'attribut nom
	private Map map;
	private ArrayList<Batiment> batiments = new ArrayList<Batiment>();
	
	
	public Ville(Map map) {
		this.map = map;
	}
	
	public void add(Batiment batiment) {
		batiments.add(batiment);
	} 
	
	public void creationBatiments() {
		Batiment maison1= new Batiment(new Block(0,0), "maison1");
		this.add(maison1);
		Batiment maison2= new Batiment(new Block(0,1), "maison2");
		this.add(maison2);
	}
	
	public ArrayList<Batiment>  getBatiments() {
		return batiments;
	}

}
