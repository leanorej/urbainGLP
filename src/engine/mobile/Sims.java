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
		//Fracis
		Routine routine1=new Routine();
		routine1.ajouterActivite(1,ville.getBatimentsList().get(7));//ca serait mieux de mettre un hashMap pour batimens dans la classe ville ou visitor si on veut se casser la tete
		routine1.ajouterActivite(4,ville.getBatimentsList().get(5));
		routine1.ajouterActivite(7,ville.getBatimentsList().get(8));
		routine1.ajouterActivite(16,ville.getBatimentsList().get(4));
		routine1.ajouterActivite(18,ville.getBatimentsList().get(9));
		Sim Francis = new Sim("Francis", new Block(0,0),routine1);
		add(Francis);
		//Genevieve
		Routine routine2=new Routine();
		routine2.ajouterActivite(1,ville.getBatimentsList().get(6)); 
		routine2.ajouterActivite(3,ville.getBatimentsList().get(2));
		routine2.ajouterActivite(6,ville.getBatimentsList().get(4));
		routine2.ajouterActivite(16,ville.getBatimentsList().get(9));
		routine2.ajouterActivite(18,ville.getBatimentsList().get(8));
		Sim Genevieve = new Sim("Genevieve",new Block(0,1),routine2);
		add(Genevieve);
		//Victor
		Routine routine3=new Routine();
		routine3.ajouterActivite(2,ville.getBatimentsList().get(2)); 
		routine3.ajouterActivite(5,ville.getBatimentsList().get(8));
		routine3.ajouterActivite(6,ville.getBatimentsList().get(4));
		routine3.ajouterActivite(16,ville.getBatimentsList().get(13));
		routine3.ajouterActivite(18,ville.getBatimentsList().get(7));
		Sim Benjamin = new Sim("Benjamin",new Block(0,5),routine3);
		add(Benjamin);
		//jeBoujePas
		Routine routine4=new Routine();
		Sim jeBoujePas = new Sim("jeBoujePas",new Block(0,5),routine4);
		add(jeBoujePas);
	}
	
	public HashMap<String,Sim>  getSims() {
		return sims;
	}
	
	public Sim getSim(String Nom) {
		return sims.get(Nom);
	}

}
