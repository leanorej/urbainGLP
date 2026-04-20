package engine.mobile;

import java.util.ArrayList;
import java.util.HashMap;

import chrono.BoundedCounter;
import chrono.Counter;
import engine.donnee.Routine;
import engine.map.Batiment;
import engine.map.Block;

/**
 * Le sim est caractérisé par son nom et son age 
 * * @author Jean
 *
 */
public class Sim {
	
	private String nom;
	
	private Block positionCourante;
	//La routine associée au sim
	private Routine routine;
	private Routine routineWeekEnd;
	
	private Etats etats = new Etats() ;
	
	private boolean vivant= true;
	
	private Counter jourRestant = new Counter(1);
	private int dernierJourCritique= -1;
	
	

	private BoundedCounter faim = new BoundedCounter(100, 100, 0);
	private BoundedCounter physique = new BoundedCounter(25, 100, 0);
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