package engine.process;

import config.GameConfiguration;
import engine.donnee.Evenement;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sims;
import chrono.Chronometer;
import engine.process.EvenementsRepository;


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
		return sims;
	}
	
	//build du manager 
	public static MobileSimManager buildInitMobile(Sims sims) {
		return new MobileSimManager(sims);
	}

	public static EtatSimManager buildInitEtat(Sims sims, Chronometer chrono, Ville ville) {
		return new EtatSimManager(sims, chrono, ville);
	}


	public static void buildEvent() {
		EvenementsRepository eventRep = EvenementsRepository.getInstance();

		eventRep.register(new Evenement("malade", "physique", 5));

		//ici on va ajouter tout le reste des evenements possibles, il faudra gérer les evenements qui immpactent deux
	}

}