package engine.process;

import java.util.HashMap;


import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sim;
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
	
	public static MobileSimManager buildInitMobile(Map map, Ville ville, Sims sims) {
		MobileSimManager manager = new MobileSimManager(map, ville, sims);
		return manager;
	}
}
