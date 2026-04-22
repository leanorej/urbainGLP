package engine.process;

import config.GameConfiguration;
import engine.donnee.Evenement;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sims;
import chrono.Chronometer;
import engine.process.EvenementsRepository;

import org.apache.log4j.Logger;
import log.LoggerUtility;

/**
 * Copyright SEDAMOP - Software Engineering
 * * @author tianxiao.liu@cyu.fr 
 *  JEAN
 *
 */
public class GameBuilder {

	private static final Logger logger = LoggerUtility.getLogger(GameBuilder.class, "html");

	public static Map buildMap() {
		logger.info("Construction de la map");
		return new Map(GameConfiguration.NUMERO_BLOC_HAUTEUR , GameConfiguration.NUMERO_BLOC_LARGEUR);
	}

	public static Ville buildVille(Map map) {
		logger.info("Construction de la ville");
		Ville ville= new Ville(map);
		ville.creationBatiments();
		return ville;
	}

	public static Sims initializeSims(Ville ville) {
		logger.info("Initialisation des sims");
		Sims sims=new Sims();
		sims.creationSims(ville);
		return sims;
	}
	
	//build du manager 
	public static MobileSimManager buildInitMobile(Sims sims) {
		return new MobileSimManager(sims);
	}

	public static EtatSimManager buildInitEtat(Sims sims, Chronometer chrono, Ville ville, EvenementManager evenementManager) {
		return new EtatSimManager(sims, chrono, ville, evenementManager);
	}

	public static RelationSimManager buildInitRelation(Sims sims, Chronometer chrono, Ville ville) {
		return new RelationSimManager(sims, chrono, ville);
	}

	public static EvenementManager buildInitEventMan(Sims sims) {
		return new EvenementManager(sims);
	}

	public static void buildEvent() {
		EvenementsRepository eventRep = EvenementsRepository.getInstance();

		eventRep.register(new Evenement("sautRepas", "faim", 20));  
		eventRep.register(new Evenement("malade", "physique", 15));
		eventRep.register(new Evenement("urgenceMedicale", "physique", 50)); 
		eventRep.register(new Evenement("dispute", "mental", 20));
		eventRep.register(new Evenement("Equipe du coeur perd", "mental", 7));
		
}

}