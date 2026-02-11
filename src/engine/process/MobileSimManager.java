package engine.process;

import java.util.ArrayList;
import java.util.List;

import engine.map.Batiment;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sim;
import engine.map.Block;
/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr JEAN
 *
 */
public class MobileSimManager implements MobileInterface {
	private Map map;

	private Ville ville;
	private List<Sim> sims = new ArrayList<Sim>();

	public MobileSimManager(Map map, Ville ville) {
		this.map = map;
		this.ville = ville;
	}

	@Override
	public void add(Sim sim) {
		sims.add(sim);
	}

	@Override
	public void nextRound() {
		moveSims();
	}

	private void moveSims() {

		for (Sim sim : sims) {
			Block position = sim.getPosition();
			
			List<Batiment> batiments = ville.getBatiments();
			/** il faudra adapter la boucle for en fonction des batiments de la routine **/
			for(int i = 0; i<batiments.size(); i++){
				Block batimentPosition = batiments.get(i).getPosition();
				if(position.getLine() == batimentPosition.getLine() && position.getColumn() == batimentPosition.getColumn()){
					int suivanti = (i+1) % batiments.size();
					Block newPosition = batiments.get(suivanti).getPosition();
					sim.setPosition(newPosition);
					break;
				}
			}
		}
	}
	@Override
	public List<Sim> getSims() {
		return sims;
	}
}
