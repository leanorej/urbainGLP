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
import engine.mobile.Sim;

public class Sims {

	private ArrayList<Sim> sims = new ArrayList<Sim>();
	
	public void add(Sim sim) {
		sims.add(sim);
	} 
	

	public void creationSims(Ville ville) {
		//Fracis
		HashMap<Integer,Batiment> routine1=new HashMap<Integer,Batiment>();
		routine1.put(1,ville.getBatiments().get(7));//ca serait mieux de mettre un hashMap pour batimens dans la classe ville ou visitor si on veut se casser la tete
		routine1.put(4,ville.getBatiments().get(5));
		routine1.put(7,ville.getBatiments().get(8));
		routine1.put(16,ville.getBatiments().get(4));
		routine1.put(18,ville.getBatiments().get(9));
		Sim Francis = new Sim(new Block(0,0),routine1);
		add(Francis);
		//Genevieve
		HashMap<Integer,Batiment> routine2=new HashMap<Integer,Batiment>();
		routine2.put(1,ville.getBatiments().get(6)); 
		routine2.put(3,ville.getBatiments().get(2));
		routine2.put(6,ville.getBatiments().get(4));
		routine2.put(16,ville.getBatiments().get(9));
		routine2.put(18,ville.getBatiments().get(8));
		Sim Genevieve = new Sim(new Block(0,0),routine2);
		add(Genevieve);
		
	}
	
	public ArrayList<Sim>  getSims() {
		return sims;
	}

}
