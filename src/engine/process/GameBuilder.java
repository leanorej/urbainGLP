package engine.process;

import java.util.HashMap;


import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sim;

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

	public static MobileInterface buildInitMobile(Map map, Ville ville) {
		MobileInterface manager = new MobileSimManager(map, ville);
		
		initializeSim(map, ville, manager);
		
		return manager;
	}

	private static void initializeSim(Map map, Ville ville, MobileInterface manager) {
		Block block = ville.getBatiments().get(0).getPosition();
		Sim sim = new Sim(block);
		manager.add(sim);
	}
}
