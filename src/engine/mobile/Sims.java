package engine.mobile;
/**
 * regroupe tout les sims
 * 
 * @author Loukili
 *
 */
import java.util.ArrayList;
import java.util.HashMap;

import engine.map.Batiment;
import engine.map.Block;
import engine.map.Ville;
/*
 * @author Loukili
 * README A SUPRIMER:est ce que ca c'est du traiment?? est ce que on peu considere les classe ville et Sims comme des 
 * Builder(un exmple de builder on l'a dans train pour line builder) 
 * vu que on build un obgjet complexe? moi je dis non vous que il y pas un algorithme derriere et c'est codé en dure
 * */
public class Sims {

	private HashMap<String,Sim> sims = new HashMap<String,Sim>();
	
	public void add(Sim sim) {
		sims.put(sim.getNom(),sim);
	} 
	

	public void creationSims(Ville ville) {
		//Fracis
		HashMap<Integer,Batiment> routine1=new HashMap<Integer,Batiment>();
		routine1.put(1,ville.getBatiments().get(7));//ca serait mieux de mettre un hashMap pour batimens dans la classe ville ou visitor si on veut se casser la tete
		routine1.put(4,ville.getBatiments().get(5));
		routine1.put(7,ville.getBatiments().get(8));
		routine1.put(16,ville.getBatiments().get(4));
		routine1.put(18,ville.getBatiments().get(9));
		Sim Francis = new Sim("Francis", new Block(0,0),routine1);
		add(Francis);
		//Genevieve
		HashMap<Integer,Batiment> routine2=new HashMap<Integer,Batiment>();
		routine2.put(1,ville.getBatiments().get(6)); 
		routine2.put(3,ville.getBatiments().get(2));
		routine2.put(6,ville.getBatiments().get(4));
		routine2.put(16,ville.getBatiments().get(9));
		routine2.put(18,ville.getBatiments().get(8));
		Sim Genevieve = new Sim("Genevieve",new Block(0,1),routine2);
		add(Genevieve);
		//Victor
		HashMap<Integer,Batiment> routine3=new HashMap<Integer,Batiment>();
		routine3.put(2,ville.getBatiments().get(2)); 
		routine3.put(5,ville.getBatiments().get(8));
		routine3.put(6,ville.getBatiments().get(4));
		routine3.put(16,ville.getBatiments().get(13));
		routine3.put(18,ville.getBatiments().get(7));
		Sim Victor = new Sim("Victor",new Block(0,5),routine3);
		add(Victor);
		//jeBoujePas
		HashMap<Integer,Batiment> routine4=new HashMap<Integer,Batiment>();
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
