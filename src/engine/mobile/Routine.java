package engine.mobile;

import java.util.HashMap;

import engine.map.Batiment;

/**
 * 
 * @author Loukili
 */
public class Routine {
	

	/**
	 * Cette {@link HashMap} contient les routines d'un Sim. La clé
	 * est un @Integer représentant l'heure de début, la valeur est le
	 * bâtiment où il va se trouver le Sim.
	 */
	private HashMap<Integer,Batiment> routine;
	public Routine() {
		this.routine=new HashMap<Integer,Batiment>();
	}
	public HashMap<Integer,Batiment> getRoutine() {
		return routine;
	}

	public void setRoutine(HashMap<Integer,Batiment> routine) {
		this.routine = routine;
	}
	
	public void put(int HeureDebut,Batiment batiment) {
		routine.put(HeureDebut, batiment);
	} 
	
}
