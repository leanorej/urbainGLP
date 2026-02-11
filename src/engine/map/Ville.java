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
		//1
		Batiment maison1= new Batiment(new Block(0,0), "maison1", 1);
		this.add(maison1);
		Batiment maison2= new Batiment(new Block(0,1), "maison2",1);
		this.add(maison2);
		Batiment maison3= new Batiment(new Block(0,2), "maison3", 1);
		this.add(maison3);
		//2
		Batiment imm1= new Batiment(new Block(6,4), "imm1", 2);
		this.add(imm1);
		Batiment imm2= new Batiment(new Block(6,5), "imm2",2);
		this.add(imm2);
		//3
		Batiment boulangerie = new Batiment(new Block(1,0), "boulangerie", 3);
		this.add(boulangerie);
		//4
		Batiment usine = new Batiment(new Block(2,8), "usine", 4);
		this.add(usine);
		//5
		Batiment piscine = new Batiment(new Block(2,5), "piscine", 5);
		this.add(piscine);
		//6
		Batiment salle = new Batiment(new Block(4,5), "salle", 4);
		this.add(salle);
		//7
		Batiment ecole = new Batiment(new Block(0,5), "ecole", 6);
		this.add(ecole);
		//8
		Batiment lycee = new Batiment(new Block(0,6), "lycee",7);
		this.add(lycee);
		//9
		Batiment parc = new Batiment(new Block(2,3), "parc",8);
		this.add(parc);
		//10
		Batiment mairie = new Batiment(new Block(4,3), "mairie",9);
		this.add(mairie);
		//11
		Batiment mall= new Batiment(new Block(6,0), "mall",10);
		this.add(mall);
		//12
		Batiment cinema = new Batiment(new Block(6,2), "cinema", 11);
		this.add(cinema);
		//13
		Batiment hopital = new Batiment(new Block(5,8), "hopital",12);
		this.add(hopital);

		//parking
		Batiment park1 = new Batiment(new Block(6,1), "park1",13);
		this.add(park1);
		Batiment park2 = new Batiment(new Block(6,3), "park2",13);
		this.add(park2);
		Batiment park3 = new Batiment(new Block(0,3), "park3",13);
		this.add(park3);
		

		Batiment route1 = new Batiment(new Block(2,0), "route1", 14);
		this.add(route1);
		Batiment route2 = new Batiment(new Block(3,0), "route2", 14);
		this.add(route2);
		Batiment route3 = new Batiment(new Block(4,0), "route3", 14);
		this.add(route3);
		Batiment route4 = new Batiment(new Block(5,0), "route4", 14);
		this.add(route4);

		Batiment route5 = new Batiment(new Block(5,5), "route5", 14);
		this.add(route5);
		Batiment route6 = new Batiment(new Block(5,6), "route6", 14);
		this.add(route6);
		Batiment route7 = new Batiment(new Block(4,6), "route7", 14);
		this.add(route7);
		Batiment route8 = new Batiment(new Block(3,6), "route8", 14);
		this.add(route8);
		Batiment route9 = new Batiment(new Block(2,6), "route9", 14);
		this.add(route9);
		Batiment route10 = new Batiment(new Block(1,6), "route10", 14);
		this.add(route10);
		Batiment r11 = new Batiment(new Block(5,1), "r11", 14);
		this.add(r11);
		Batiment r12 = new Batiment(new Block(5,2), "r12", 14);
		this.add(r12);
		Batiment r13= new Batiment(new Block(5,3), "r13", 14);
		this.add(r13);
		Batiment r14 = new Batiment(new Block(5,4), "r14", 14);
		this.add(r14);
		Batiment r15 = new Batiment(new Block(5,7), "r15", 14);
		this.add(r15);
		
		
	}
	
	public ArrayList<Batiment>  getBatiments() {
		return batiments;
	}

}
