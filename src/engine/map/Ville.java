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
		Batiment maison1= new Batiment(new Block(0,0), "maison1", 1);
		this.add(maison1);
		Batiment maison2= new Batiment(new Block(0,1), "maison2",1);
		this.add(maison2);
		Batiment maison3= new Batiment(new Block(0,2), "maison3", 1);
		this.add(maison3);

		Batiment mall= new Batiment(new Block(4,0), "mall",2);
		this.add(mall);

		Batiment parc = new Batiment(new Block(2,3), "parc",3);
		this.add(parc);

		Batiment hopital = new Batiment(new Block(3,5), "hopital",4);
		this.add(hopital);

		Batiment cinema = new Batiment(new Block(4,1), "cinema",5);
		this.add(cinema);

		Batiment lycee = new Batiment(new Block(2,5), "lycee",6);
		this.add(lycee);
		
		Batiment route1 = new Batiment(new Block(1,0), "route1", 7);
		this.add(route1);
		Batiment route2 = new Batiment(new Block(2,0), "route2", 7);
		this.add(route2);
		Batiment route3 = new Batiment(new Block(3,0), "route3", 7);
		this.add(route3);

		Batiment route4 = new Batiment(new Block(0,3), "route4", 7);
		this.add(route4);
		Batiment route5 = new Batiment(new Block(0,4), "route5", 7);
		this.add(route5);
		Batiment route6 = new Batiment(new Block(0,5), "route6", 7);
		this.add(route6);
		Batiment route7 = new Batiment(new Block(1,5), "route7", 7);
		this.add(route7);
		
	}
	
	public ArrayList<Batiment>  getBatiments() {
		return batiments;
	}

}
