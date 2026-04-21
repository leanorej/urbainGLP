package engine.mobile;
import java.util.HashMap;

import engine.donnee.Routine;
import engine.map.Ville;

import log.LoggerUtility;
import org.apache.log4j.Logger;


public class Sims {

	private HashMap<String,Sim> sims = new HashMap<String,Sim>();
	private static final Logger logger = Logger.getLogger(Ville.class);
	
	public void add(Sim sim) {
		sims.put(sim.getNom(),sim);
	} 

	public void creationSims(Ville ville) {
		// Francis 37 ans ouvrier
		Routine routine1 = new Routine();
		routine1.ajouterActivite(8,  ville.get("usine"));     
		routine1.ajouterActivite(12, ville.get("maison1"));  
		routine1.ajouterActivite(14, ville.get("usine"));    
		routine1.ajouterActivite(18, ville.get("salle"));     
		routine1.ajouterActivite(20, ville.get("maison1")); 
		
		// Francis 37 ans ouvrier
		Routine routine1WE = new Routine();
	    routine1WE.ajouterActivite(10, ville.get("cinema"));  
	    routine1WE.ajouterActivite(13, ville.get("maison1"));
	    routine1WE.ajouterActivite(15, ville.get("mall"));
	    routine1WE.ajouterActivite(18, ville.get("maison1"));
		
		Sim Francis = new Sim("Francis", ville.get("maison1").getPosition(), routine1,routine1WE);
		add(Francis);
		logger.info("Sim Francis créé.");
		
		// Genevieve 74 ans retraitee
		Routine routine2 = new Routine();
		routine2.ajouterActivite(9,  ville.get("parc"));      
		routine2.ajouterActivite(11, ville.get("boulangerie"));   
		routine2.ajouterActivite(13, ville.get("maison2"));
		routine2.ajouterActivite(16, ville.get("piscine"));       
		routine2.ajouterActivite(17, ville.get("maison2")); 
		
		Routine routine2WE = new Routine();
		routine2WE.ajouterActivite(9,  ville.get("piscine"));  
		routine2WE.ajouterActivite(11, ville.get("maison2"));
		routine2WE.ajouterActivite(16, ville.get("parc"));    
		routine2WE.ajouterActivite(17, ville.get("maison2"));
		Sim Genevieve = new Sim("Genevieve", ville.get("maison2").getPosition(), routine2, routine2WE);
		add(Genevieve);
		logger.info("Sim Genevieve créée.");

		
		// Benjamin 14 ans lycee
	    Routine routine3 = new Routine();
	    routine3.ajouterActivite(8,  ville.get("lycee"));
	    routine3.ajouterActivite(17, ville.get("parc"));
	    routine3.ajouterActivite(18, ville.get("immeuble"));

	    Routine routine3WE = new Routine();//il aime pas trop sortir le week end
	    routine3WE.ajouterActivite(11, ville.get("parc"));   
	    routine3WE.ajouterActivite(13, ville.get("immeuble"));
	
	    Sim Benjamin = new Sim("Benjamin", ville.get("immeuble").getPosition(), routine3, routine3WE);
	    add(Benjamin);
		logger.info("Sim Benjamin créé.");


	    // Julie 8 ans ecoliere
	    Routine routine4 = new Routine();
	    routine4.ajouterActivite(8,  ville.get("ecole"));
	    routine4.ajouterActivite(16, ville.get("immeuble"));
	    routine4.ajouterActivite(15, ville.get("parc"));
	    routine4.ajouterActivite(18, ville.get("immeuble"));

	    Routine routine4WE = new Routine();
	    routine4WE.ajouterActivite(10, ville.get("cinema"));  
	    routine4WE.ajouterActivite(12, ville.get("immeuble"));
	    routine4WE.ajouterActivite(14, ville.get("parc"));
	    routine4WE.ajouterActivite(18, ville.get("immeuble"));

	    Sim Julie = new Sim("Julie", ville.get("immeuble").getPosition(), routine4, routine4WE);
	    add(Julie);
		logger.info("Sim Julie créée.");

		
	    // Marie 34 ans boulangere
	    Routine routine5 = new Routine();
	    routine5.ajouterActivite(6,  ville.get("boulangerie"));
	    routine5.ajouterActivite(14, ville.get("immeuble"));
	    routine5.ajouterActivite(17, ville.get("salle"));
	    routine5.ajouterActivite(19, ville.get("immeuble"));

	    Routine routine5WE = new Routine();      
	    routine5WE.ajouterActivite(10, ville.get("cinema"));  
	    routine5WE.ajouterActivite(12, ville.get("immeuble"));
	    routine5WE.ajouterActivite(15, ville.get("salle"));  
	    routine5WE.ajouterActivite(17, ville.get("immeuble"));

	    Sim Marie = new Sim("Marie", ville.get("immeuble").getPosition(), routine5, routine5WE);
	    add(Marie);
		logger.info("Sim Marie créée.");


	    // Emmanuel 50 ans maire
	    Routine routine6 = new Routine();
	    routine6.ajouterActivite(11, ville.get("mairie"));
	    routine6.ajouterActivite(17, ville.get("hopital"));
	    routine6.ajouterActivite(18, ville.get("maison3"));

	    Routine routine6WE = new Routine();      
	    routine6WE.ajouterActivite(11, ville.get("mairie"));   
	    routine6WE.ajouterActivite(16, ville.get("parc"));    
	    routine6WE.ajouterActivite(17, ville.get("mall"));    
	    routine6WE.ajouterActivite(18, ville.get("maison3"));
		
		Sim Emmanuel = new Sim("Emmanuel", ville.get("maison3").getPosition(), routine6, routine6WE);
		add(Emmanuel);
		logger.info("Sim Emmanuel créé.");

	}
	
	public HashMap<String,Sim> getSims() {
		return sims;
	}
	
	public Sim getSim(String Nom) {
		return sims.get(Nom);
	}

	
	public void remove(Sim sim) {
	    Sim removed = sims.remove(sim.getNom());
	    //System.out.println("Tentative de suppression : " + sim.getNom() + "  résultat : " + (removed != null ? "OK" : "ÉCHEC - non trouvé"));

	}
}