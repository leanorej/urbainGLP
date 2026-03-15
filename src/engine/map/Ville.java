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
		Batiment maison1= new Batiment(new Block(6,3), "maison1");
		this.put(maison1.getNomBatiment(),maison1);

		Batiment maison2= new Batiment(new Block(11,3), "maison2");
		this.put(maison2.getNomBatiment(),maison2);

		Batiment maison3= new Batiment(new Block(16,3), "maison3");
		this.put(maison3.getNomBatiment(),maison3);

		//2
		Batiment imm= new Batiment(new Block(11,14), "imm");
		this.put(imm.getNomBatiment(),imm);

		//3
		Batiment boulangerie = new Batiment(new Block(10,7), "boulangerie");
		this.put(boulangerie.getNomBatiment(),boulangerie);

		//4
		Batiment usine = new Batiment(new Block(3, 26), "usine");
		this.put(usine.getNomBatiment(),usine);

		//5
		Batiment piscine = new Batiment(new Block(3,16), "piscine");
		this.put(piscine.getNomBatiment(),piscine);

		//6
		Batiment salle = new Batiment(new Block(15,15), "salle");
		this.put(salle.getNomBatiment(),salle);

		//7
		Batiment ecole = new Batiment(new Block(3,11), "ecole");
		this.put(ecole.getNomBatiment(),ecole);

		//8
		Batiment lycee = new Batiment(new Block(3,5), "lycee");
		this.put(lycee.getNomBatiment(),lycee);

		//9
		Batiment parc = new Batiment(new Block(13,9), "parc");
		this.put(parc.getNomBatiment(),parc);

		//10
		Batiment mairie = new Batiment(new Block(8,20), "mairie");
		this.put(mairie.getNomBatiment(),mairie);

		//11
		Batiment mall= new Batiment(new Block(8,24), "mall");
		this.put(mall.getNomBatiment(),mall);
		
		//12
		Batiment cinema = new Batiment(new Block(14,25), "cinema");
		this.put(cinema.getNomBatiment(),cinema);

		//13
		Batiment hopital = new Batiment(new Block(14,22), "hopital");
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
