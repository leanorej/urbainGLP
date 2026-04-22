package engine.mobile;

import chrono.BoundedCounter;
import chrono.Counter;
import engine.donnee.Routine;
import engine.map.Block;

/**
 * Représente un Sim, personnage principal du jeu.
 * Un Sim est caractérisé par son nom, sa position sur la carte, ses deux routines
 * et trois barres de statistiques : la faim, la forme physique
 * et la santé mentale et aussi un etat mental.
 * 
 * @author Chouaref
 * @author Jean
 * @author Loukii
 * 
 * @version 1.0
 */
public class Sim {
	
	private String nom;
	
	private Block positionCourante;
	//La routine associée au sim
	private Routine routine;
	//La routine du weekend associée au sim
	private Routine routineWeekEnd;
	//États courants du Sim. 
	private Etats etats = new Etats() ;
	
	private boolean vivant= true;
	/**
     * Compteur du nombre de jours restants avant la mort du Sim
     * lorsqu'il est en état critique (initialisé à 4 jours).
     */
	private Counter jourRestant = new Counter(4);
	 /**
     * Numéro du dernier jour où le Sim était en état critique.
     * Initialisé à -1} si aucun jour critique n'a encore été enregistré.
     */
	private int dernierJourCritique= -1;

	private BoundedCounter faim = new BoundedCounter(100, 100, 0);
	private BoundedCounter physique = new BoundedCounter(100, 100, 0);
	private BoundedCounter mental = new BoundedCounter(100, 100, 0);
	
	public Sim(String nom, Block position, Routine routine, Routine routineWeekEnd){
		this.nom=nom;
		this.positionCourante = position;
		this.routine=routine;
		this.routineWeekEnd=routineWeekEnd;
	}
	
	public int getScoreGlobal() {
		return (faim.getValue() + physique.getValue() + mental.getValue()) / 3;
	}
	
	public Block getPositionCourante() {
		return positionCourante;
	}
	
	public void setPositionCourante(Block position){
		this.positionCourante = position;
	}

	public Routine getRoutine() {
		return routine;
	}


	public void setRoutine(Routine routine) {
		this.routine = routine;
	}

	public boolean isVivant() {
		return vivant;
	}

	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}

	public String getNom() {
		return nom;
	}

	public Etats getEtats() {
		return etats;
	}
	
	public BoundedCounter getFaim() { return faim; }
	public BoundedCounter getPhysique() { return physique; }
	public BoundedCounter getMental() { return mental; }

	@Override
	public String toString() {
			return "Sim [nom=" + nom + "]";
	}

	public Routine getRoutineWeekEnd() {
		return routineWeekEnd;
	}

	public void setRoutineWeekEnd(Routine routineWend) {
		this.routineWeekEnd = routineWend;
	}

	public Counter getJourRestant() {
		return jourRestant;
	}

	public int getDernierJourCritique() {
		return dernierJourCritique;
	}

	public void setDernierJourCritique(int dernierJourCritique) {
		this.dernierJourCritique = dernierJourCritique;
	}

}