package engine.mobile;
import java.util.HashMap;

import engine.donnee.Routine;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Ville;
/*
 * @author Loukili
 * README A SUPRIMER:est ce que ca c'est du traiment?? est ce que on peu considere les classe ville et Sims comme des 
 * Builder(un exmple de builder on l'a dans train pour line builder) 
 * vu que on build un obgjet complexe? moi je dis non, vu que il y pas un algorithme derriere et c'est codé en dure
 * */
public class Sims {

	private HashMap<String,Sim> sims = new HashMap<String,Sim>();
	
	public void add(Sim sim) {
		sims.put(sim.getNom(),sim);
	} 
	

	public void creationSims(Ville ville) {
		//Francis 37 ans ouvrier
	    Routine routine1 = new Routine();
	    routine1.ajouterActivite(8,  ville.get("usine"));     
	    routine1.ajouterActivite(12, ville.get("maison1"));//pause dej  
	    routine1.ajouterActivite(14, ville.get("usine"));    
	    routine1.ajouterActivite(18, ville.get("salle"));     
	    routine1.ajouterActivite(20, ville.get("maison1"));   
	    Sim Francis = new Sim("Francis", ville.get("maison1").getPosition(), routine1);
	    add(Francis);
	
	    //Genevieve 74 ans retraitée
	    Routine routine2 = new Routine();
	    routine2.ajouterActivite(9,  ville.get("parc"));      
	    routine2.ajouterActivite(11, ville.get("maison2"));   
	    routine2.ajouterActivite(16, ville.get("piscine"));      
	    routine2.ajouterActivite(17, ville.get("maison2")); 
	    Sim Genevieve = new Sim("Genevieve", ville.get("maison2").getPosition(), routine2);
	    add(Genevieve);
	
	    //Benjamin 17 ans lycéen
	    Routine routine3 = new Routine();
	    routine3.ajouterActivite(8,  ville.get("lycee"));    
	    routine3.ajouterActivite(17, ville.get("parc"));  
	    routine3.ajouterActivite(18, ville.get("imm"));
	    Sim Benjamin = new Sim("Benjamin", ville.get("imm").getPosition(), routine3);
	    add(Benjamin);
	}
	
	public HashMap<String,Sim>  getSims() {
		return sims;
	}
	
	public Sim getSim(String Nom) {
		return sims.get(Nom);
	}

}
