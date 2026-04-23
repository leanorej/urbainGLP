package engine.process;
import engine.donnee.Evenement;



import java.util.HashMap;
/**
 * Cette classe gère est stocke les {@link engine.donnee.Evenement}s du jeux.
 * Elle utilise le Design pattern Singleton, pour permettre
 * de avoir une seule instance pendant l'execution du jeux.
 * @author CHOUAREF
 */
public class EvenementsRepository {
	
    
	/**
     * Cette {@link HashMap} contient tous les événements enregistrés.
     * La clé est le nom de l'événement associée l'objet événement.
     */
	private HashMap<String, Evenement> evenements = new HashMap<String, Evenement>();

	/**
	 * Instance unique de la classe initialisé à l’appelle de la classe.
	 */
	private static EvenementsRepository instance = new EvenementsRepository();

	/**
     * Constructeur privé appelé directement pas la classe.
     */
	private EvenementsRepository() {

	}

    /**
     * Méthode statique permettant aux utilisateurs d'obtenir l'instance unique de la classe.
     *
     * @return l'instance de la classe.
	 */ 
	public static EvenementsRepository getInstance() {
		return instance;
	}

    /**
	 * Permet de ajouter un événement.
	 * @param event l'événement à ajouter .
	 */
	public void register(Evenement event) {
		evenements.put(event.getNom(), event);
	}

	/**
     * Retourne la HashMap des événements enregistrés dans le Repository.
     *
     * @return une {@link HashMap} contenant tous les événements, qui a comme clé le nom de l'événement.
     */
	public HashMap<String, Evenement> getValues () {
		return evenements ;
	}    

	 /**
     * Recherche et retourne l'événement par son nom.
     *
     * @param nom le nom de l'événement recherché.
     * @return l'événement associé au nom, ou null s'il est existe pas.
     */
	public Evenement getEvenement(String nom) {
		if (evenements.containsKey(nom)) {
			return evenements.get(nom);
		}
        else 
            return null ;
	}

}

