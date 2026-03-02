package engine.process;

import config.GameConfiguration;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sims;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr Loukili JEAN
 *
 */
public class GameBuilder {

	public static Map buildMap() {
		return new Map(GameConfiguration.NUMERO_BLOC_HAUTEUR , GameConfiguration.NUMERO_BLOC_LARGEUR);
	}
	//construcion de la ville a l'aide de creationBatiments
	public static Ville buildVille(Map map) {
		Ville ville= new Ville(map);
		ville.creationBatiments();
		return ville;
	}
	public static Sims initializeSims(Ville ville) {
		Sims sims=new Sims();
		sims.creationSims(ville);
		return sims;
	}
	
	public static MobileSimManager buildInitMobile(Sims sims) {
		MobileSimManager manager = new MobileSimManager(sims);
		return manager;
	}
}
