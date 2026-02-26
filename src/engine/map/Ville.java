package engine.map;
/**
 * regroupe tout les batiments
 * 
 * @author Loukili
 *
 */
import java.util.ArrayList;

public class Ville {
	private Map map;
	private ArrayList<Batiment> batiments = new ArrayList<Batiment>();
	
	
	public Ville(Map map) {
		this.map = map;
	}
	
	public void add(Batiment batiment) {
		batiments.add(batiment);
	} 
	

	public void creationBatiments() {
		//1
		Batiment maison1= new Batiment(new Block(6,3), "maison1");
		this.add(maison1);

		Batiment maison2= new Batiment(new Block(11,3), "maison2");
		this.add(maison2);

		Batiment maison3= new Batiment(new Block(16,3), "maison3");
		this.add(maison3);

		//2
		Batiment imm= new Batiment(new Block(11,14), "imm");
		this.add(imm);

		//3
		Batiment boulangerie = new Batiment(new Block(10,7), "boulangerie");
		this.add(boulangerie);

		//4
		Batiment usine = new Batiment(new Block(3, 26), "usine");
		this.add(usine);

		//5
		Batiment piscine = new Batiment(new Block(3,16), "piscine");
		this.add(piscine);

		//6
		Batiment salle = new Batiment(new Block(15,15), "salle");
		this.add(salle);

		//7
		Batiment ecole = new Batiment(new Block(3,11), "ecole");
		this.add(ecole);

		//8
		Batiment lycee = new Batiment(new Block(3,5), "lycee");
		this.add(lycee);

		//9
		Batiment parc = new Batiment(new Block(13,9), "parc");
		this.add(parc);

		//10
		Batiment mairie = new Batiment(new Block(9,20), "mairie");
		this.add(mairie);

		//11
		Batiment mall= new Batiment(new Block(8,24), "mall");
		this.add(mall);
		
		//12
		Batiment cinema = new Batiment(new Block(14,25), "cinema");
		this.add(cinema);

		//13
		Batiment hopital = new Batiment(new Block(15,22), "hopital");
		this.add(hopital);
	}
	
	public ArrayList<Batiment>  getBatiments() {
		return batiments;
	}

}
