package engine.map;
/**
 * regroupe tout les batiments
 * 
 * @author Loukili
 *
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Ville {
	private Map map;
	private HashMap<String,Batiment> batiments = new HashMap<String,Batiment>();
	
	
	public Ville(Map map) {
		this.map = map;
	}
	
	public void put(String nomBatiment, Batiment batiment) {
		batiments.put(nomBatiment,batiment);
	} 
	
	public void creationBatiments() {
		//1
		Batiment maison1= new Batiment(new Block(5,0), "maison1", 4, 4);
		this.put(maison1.getNomBatiment(),maison1);

		Batiment maison2= new Batiment(new Block(10,0), "maison2", 4, 4);
		this.put(maison2.getNomBatiment(),maison2);

		Batiment maison3= new Batiment(new Block(15,0), "maison3", 4, 4);
		this.put(maison3.getNomBatiment(),maison3);

		//2
		Batiment imm= new Batiment(new Block(7,11), "imm", 5, 6);
		this.put(imm.getNomBatiment(),imm);

		//3
		Batiment boulangerie = new Batiment(new Block(8,6), "boulangerie", 3, 3);
		this.put(boulangerie.getNomBatiment(),boulangerie);

		//4
		Batiment usine = new Batiment(new Block(0,22), "usine", 4, 8);
		this.put(usine.getNomBatiment(),usine);

		//5
		Batiment piscine = new Batiment(new Block(0,15), "piscine", 4, 5);
		this.put(piscine.getNomBatiment(),piscine);

		//6
		Batiment salle = new Batiment(new Block(15,14), "salle", 4, 4);
		this.put(salle.getNomBatiment(),salle);

		//7
		Batiment ecole = new Batiment(new Block(0,8), "ecole", 4, 6);
		this.put(ecole.getNomBatiment(),ecole);

		//8
		Batiment lycee = new Batiment(new Block(0,0), "lycee", 4, 7);
		this.put(lycee.getNomBatiment(),lycee);

		//9
		Batiment parc = new Batiment(new Block(13,6), "parc", 6, 7);
		this.put(parc.getNomBatiment(),parc);

		//10
		Batiment mairie = new Batiment(new Block(8,19), "mairie", 4, 4);
		this.put(mairie.getNomBatiment(),mairie);

		//11
		Batiment mall= new Batiment(new Block(6,24), "mall", 6, 5);
		this.put(mall.getNomBatiment(),mall);
		
		//12
		Batiment cinema = new Batiment(new Block(13,25), "cinema", 6, 4);
		this.put(cinema.getNomBatiment(),cinema);

		//13
		Batiment hopital = new Batiment(new Block(14,19), "hopital", 5, 5);
		this.put(hopital.getNomBatiment(),hopital);
	}
	
	//Retourne une ArrayList de Batiment avec comme clé String->Nom Batiment
	public HashMap<String, Batiment>  getBatimentsHashMap() {
		return batiments;
	}
	//Retourne une ArrayList de Batiment
	public ArrayList<Batiment>  getBatimentsList() {
		return new ArrayList<Batiment>(batiments.values());
	}
	public Batiment get(String nomBatiment){
		if (batiments.containsKey(nomBatiment)) {
			return batiments.get(nomBatiment);
		} else {
			throw new NoSuchElementException("Le batiment " + nomBatiment + " n'existe pas.");
		}
	}
}