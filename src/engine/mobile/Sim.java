package engine.mobile;

import java.util.HashMap;

import engine.map.Batiment;
import engine.map.Block;
/**
 * Le sim est caractérisé par son nom et son age 
 * 
 * @author Jean
 *
 */
public class Sim {
	//ajouterun classe identité
	private Block positionCourante;
	//La routin associée au sim
	private Routine routine;
	
	public Sim(Block position, Routine routine){
		this.positionCourante = position;
		this.routine=routine;
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
}
