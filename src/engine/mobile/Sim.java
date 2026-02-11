package engine.mobile;

import engine.map.Block;
/**
 * Le sim est caractérisé par son nom et son age
 * 
 * @author Jean
 *
 */
public class Sim {

	private Block position;
	
	public Sim(Block position){
		this.position = position;
	}
	
	public Block getPosition() {
		return position;
	}
	
	public void setPosition(Block position){
		this.position = position;
	}
}
