package engine.process;

import config.GameConfiguration;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sims;
import chrono.Chronometer;

/**
 * Copyright SEDAMOP - Software Engineering
 * * @author tianxiao.liu@cyu.fr Loukili JEAN
 *
 */
public class GameBuilder {

	public static Map buildMap() {
		return new Map(GameConfiguration.NUMERO_BLOC_HAUTEUR , GameConfiguration.NUMERO_BLOC_LARGEUR);
	}

	public static Ville buildVille(Map map) {
		Ville ville= new Ville(map);
		ville.creationBatiments();
		return ville;
	}

	public static Sims initializeSims(Ville ville) {
		Sims sims=new Sims();
		sims.creationSims(ville);
		for(String nomSim : sims.getSims().keySet()) {
			System.out.println("Nom: "+nomSim+"\nRoutine:"+sims.getSim(nomSim).getRoutine().toString());
		}
		return sims;
	}
	
	//build du manager 
	public static MobileSimManager buildInitMobile(Sims sims, Chronometer chrono, Ville ville) {
		return new MobileSimManager(sims, chrono, ville);
	}
}