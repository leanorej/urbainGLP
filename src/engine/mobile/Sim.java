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
	//nom? age? genre?
	private Block positionCourante;
	//La routin associée au sim
	private HashMap<Integer,Batiment> routine;
	
	private boolean vivant= true;
	
	public Sim(Block position, HashMap<Integer,Batiment> routine){
		this.positionCourante = position;
		this.routine=routine;
	}
	
	public Block getPositionCourante() {
		return positionCourante;
	}
	
	public void setPositionCourante(Block position){
		this.positionCourante = position;
	}

	public HashMap<Integer,Batiment> getRoutine() {
		return routine;
	}

	public void setRoutine(HashMap<Integer,Batiment> routine) {
		this.routine = routine;
	}

	public boolean isVivant() {
		return vivant;
	}

	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}
}
