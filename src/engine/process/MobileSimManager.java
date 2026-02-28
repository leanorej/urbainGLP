package engine.process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.map.Block;
/**
 * 
 * @author JEAN
 *
 */
public class MobileSimManager {
	private Map map;
	private Ville ville;
	private Sims sims;
	int i=0;

	public Sims getSims() {
		return sims;
	}
	public MobileSimManager(Map map, Ville ville, Sims sims) {
		this.map = map;
		this.ville = ville;
		this.sims=sims;
	}
	public void nextMove(int heureCourante) {
		for(Sim sim: sims.getSims()) {
			//Verification si c'est l'heure de changer le batiment
			if(sim.getRoutine().containsKey(heureCourante)) {
				//ATTENTION EST CE QUE SA VA CAUSER PROBLEME QUAND ON VA IMPLEMETER LE DEPLACEMENT FORCE PAR  L'USER OU LES EVENENEMT??
				//peut etre faut prevoir un variable qui indique oui il deja commencer cette tache ducoup pas besoin de y revenir comme ca si l'user force le sim a all
				//aller dans un autre batiment cette fonction elle va pas l'obliger à revenir à la routine 
				sim.setPositionCourante(sim.getRoutine().get(heureCourante).getPosition());
			}
		}	
		
	}
}

