package engine.process;

import config.GameConfiguration;
import engine.donnee.Evenement;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sims;
import chrono.Chronometer;
import org.apache.log4j.Logger;
import log.LoggerUtility;

/**
 * Responsable de construction et de l'initialisation de tous les element de donnee et traitement du jeu.
 * Cette classe fournit de méthodes statiques pour construire la carte, la ville, les personnages (Sims) ainsi
 * que les différents managers.
 * @author CHOUAREF
 * @author JEAN
 */
public class GameBuilder {

	private static final Logger logger = LoggerUtility.getLogger(GameBuilder.class, "html");

	/**
     * Construit et retourne une nouvelle instance de la map.
     *
     * @return la map dont les dimensions proviennent de GameConfiguration
     */
	public static Map buildMap() {
		logger.info("Construction de la map");
		return new Map(GameConfiguration.NUMERO_BLOC_HAUTEUR , GameConfiguration.NUMERO_BLOC_LARGEUR);
	}

	 /**
     * Construit et retourne une nouvelle Ville.
     *
     * @param map sur laquelle la ville sera construite 
     * @return la ville entièrement initialisé avec ses bâtiments créés
     */
	public static Ville buildVille(Map map) {
		logger.info("Construction de la ville");
		Ville ville= new Ville(map);
		ville.creationBatiments();
		return ville;
	}

	/**
     * Construit et retourne une instance de tout les Sims et il le place dans la ville
     *
     * @param ville dans laquelle les Sims seront créés 
     * @return un instance de la classe Sims
     */
	public static Sims initializeSims(Ville ville) {
		logger.info("Initialisation des sims");
		Sims sims=new Sims();
		sims.creationSims(ville);
		return sims;
	}
	
	/**
     * Construit et retourne le mobileSimMenager chargé de gérer
     * les déplacements des Sims au sein de la simulation.
     *
     * @param sims isnstance de sims ou tout les sims sont stocke
     * @return instance de MobileSimMenager responsaple du déplacements des sim
     */
	public static MobileSimManager buildInitMobile(Sims sims) {
		return new MobileSimManager(sims);
	}
	
	/**
     * Construit et retourne un {@link EtatSimManager} chargé de gérer
     * l'état (santé, faim, humeur, etc.) des Sims au fil du temps.
     *
     * @param sims             l'instance {@link Sims} dont les états seront gérés ; ne doit pas être {@code null}
     * @param chrono           le {@link Chronometer} utilisé pour suivre le temps en jeu ; ne doit pas être {@code null}
     * @param ville            la {@link Ville} fournissant le contexte des changements d'état ; ne doit pas être {@code null}
     * @param evenementManager le {@link EvenementManager} utilisé pour déclencher les événements
     *                         affectant les états des Sims ; ne doit pas être {@code null}
     * @return un nouveau {@link EtatSimManager} initialisé avec les composants fournis
     */
	public static EtatSimManager buildInitEtat(Sims sims, Chronometer chrono, Ville ville, EvenementManager evenementManager ) {
		return new EtatSimManager(sims, chrono, ville, evenementManager);
	}

	/**
     * Construit et retourne RelationSimManager chargé de gérer
     * les relations sociales entre les Sims.
     *
     * @param sims  
     * @param chrono
     * @param ville  
     *               
     * @return istance RelationSimManager initialisé avec les composants fournis
     */
	public static RelationSimManager buildInitRelation(Sims sims, Chronometer chrono, Ville ville) {
		return new RelationSimManager(sims, chrono, ville);
	}
	
	/**
     * Construit et retourne un {@link EvenementManager} chargé de gérer
     * les événements en jeu affectant les Sims.
     *
     * @param sims l'instance {@link Sims} qui sera affectée par les événements gérés ;
     *             ne doit pas être {@code null}
     * @return un nouveau {@link EvenementManager} initialisé avec les {@link Sims} fournis
     */
	public static EvenementManager buildInitEventMan(Sims sims) {
		return new EvenementManager(sims);
	}
	
	/**
     * Construit et retourne un {@link NiveauManager} chargé de gérer
     * les niveaux de difficulté ou les étapes de progression de la simulation.
     *
     * @return une nouvelle instance de {@link NiveauManager}
     */
	public static NiveauManager buildInitNiv() {
		return new NiveauManager();
	}

	/**
    * Enregistre tous les événements prédéfinis du jeu dans le singleton {@link EvenementsRepository}.
    *
    * <p>Les événements suivants sont enregistrés avec leur catégorie de statistique
    * associée et leur valeur d'impact :</p>
    * <ul>
    *   <li>{@code sautRepas}            — affecte {@code faim} (faim) de 20</li>
    *   <li>{@code malade}               — affecte {@code physique} (santé physique) de 15</li>
    *   <li>{@code urgenceMedicale}      — affecte {@code physique} (santé physique) de 50</li>
    *   <li>{@code dispute}              — affecte {@code mental} (santé mentale) de 20</li>
    *   <li>{@code Equipe du coeur perd} — affecte {@code mental} (santé mentale) de 7</li>
    * </ul>
    */
	public static void buildEvent() {
		EvenementsRepository eventRep = EvenementsRepository.getInstance();

		eventRep.register(new Evenement("sautRepas", "faim", 20));  
		eventRep.register(new Evenement("malade", "physique", 15));
		eventRep.register(new Evenement("urgenceMedicale", "physique", 50)); 
		eventRep.register(new Evenement("dispute", "mental", 20));
		eventRep.register(new Evenement("Equipe du coeur perd", "mental", 7));
		
}

}