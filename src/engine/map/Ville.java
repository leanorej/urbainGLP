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

import log.LoggerUtility;
import org.apache.log4j.Logger;


import engine.mobile.Sims;

public class Ville {
	private Map map;
	private HashMap<String,Batiment> batiments = new HashMap<String,Batiment>();
	private static final Logger logger = Logger.getLogger(Ville.class);

	
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
		logger.info("creation maison 1");

		Batiment maison2= new Batiment(new Block(10,0), "maison2", 4, 4);
		this.put(maison2.getNomBatiment(),maison2);
		logger.info("creation maison 2");

		Batiment maison3= new Batiment(new Block(15,0), "maison3", 4, 4);
		this.put(maison3.getNomBatiment(),maison3);
		logger.info("creation maison 3");

		//2
		Batiment immeuble= new Batiment(new Block(7,11), "immeuble", 6, 5);
		this.put(immeuble.getNomBatiment(),immeuble);
		logger.info("creation immeuble");

		//3
		Batiment boulangerie = new Batiment(new Block(8,6), "boulangerie", 3, 3);
		this.put(boulangerie.getNomBatiment(),boulangerie);
		logger.info("creation boulangerie");

		//4
		Batiment usine = new Batiment(new Block(0,22), "usine", 8, 4);
		this.put(usine.getNomBatiment(),usine);
		logger.info("creation usine");

		//5
		Batiment piscine = new Batiment(new Block(0,15), "piscine", 5, 4);
		this.put(piscine.getNomBatiment(),piscine);
		logger.info("creation piscine");

		//6
		Batiment salle = new Batiment(new Block(15,14), "salle", 4, 4);
		this.put(salle.getNomBatiment(),salle);
		logger.info("creation salle de sport");

		//7
		Batiment ecole = new Batiment(new Block(0,8), "ecole", 6, 4);
		this.put(ecole.getNomBatiment(),ecole);
		logger.info("creation ecole");

		//8
		Batiment lycee = new Batiment(new Block(0,0), "lycee", 7, 4);
		this.put(lycee.getNomBatiment(),lycee);
		logger.info("creation lycee");

		//9
		Batiment parc = new Batiment(new Block(13,6), "parc", 7, 6);
		this.put(parc.getNomBatiment(),parc);
		logger.info("creation parc");

		//10
		Batiment mairie = new Batiment(new Block(8,19), "mairie", 4, 4);
		this.put(mairie.getNomBatiment(),mairie);
		logger.info("creation mairie");

		//11
		Batiment mall= new Batiment(new Block(6,24), "mall", 5, 6);
		this.put(mall.getNomBatiment(),mall);
		logger.info("creation centre commercial");

		//12
		Batiment cinema = new Batiment(new Block(13,25), "cinema", 4, 6);
		this.put(cinema.getNomBatiment(),cinema);
		logger.info("creation cinema");

		//13
		Batiment hopital = new Batiment(new Block(14,19), "hopital", 5, 5);
		this.put(hopital.getNomBatiment(),hopital);		
		logger.info("creation hopital");

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
			logger.error("Le batiment "+nomBatiment+" n'existe pas dans la ville.");
			throw new NoSuchElementException("Le batiment " + nomBatiment + " n'existe pas.");
		}
	}
}