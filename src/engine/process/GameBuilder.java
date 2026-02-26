package engine.process;

import java.util.HashMap;


import config.GameConfiguration;
import engine.map.Batiment;
import engine.map.Block;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Routine;
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
		Routine routine1=new Routine();
		routine1.put(8,ville.getBatiments().get(0));//ca serait mieux de mettre un hashMap pour batimens dans la classe ville ou visitor si on veut se casser la tete
		routine1.put(16,ville.getBatiments().get(5));
		routine1.put(18,ville.getBatiments().get(9));
		Sim sim1 = new Sim(block,routine1);
		manager.add(sim1);
	}
}
