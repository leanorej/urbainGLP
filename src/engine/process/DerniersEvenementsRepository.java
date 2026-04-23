package engine.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stocke les message correspondant aux deriers événements, donc des String
 * generé dans les classes {@link engine.process.EtatSimManager} et {@link engine.process.EvenementManager} .
 * Utilise le design pattern Singleton pour garantir une instance unique.
 *
 * @author LOUKILI
 */
public class DerniersEvenementsRepository {

    /**
     * ArrayList contenant tous les événements passés de la simulation.
     */
    private ArrayList<String> listEvenementPasses = new ArrayList<>();

    /**
     * Instance unique de la classe, initialisée au chargement.
     */
    private static DerniersEvenementsRepository instance = new DerniersEvenementsRepository();

    /**
     * Constructeur privé — empêche l'instanciation externe.
     */
    private DerniersEvenementsRepository() {
    }

    /**
     * Retourne l'instance unique de {@link derniersEvenements}.
     *
     * @return l'instance singleton de la classe.
     */
    public static DerniersEvenementsRepository getInstance() {
        return instance;
    }

    /**
     * Ajoute une nouvelle entrée au journal des événements.
     *
     * @param nuoveauEvent la description de l'événement à enregistrer.
     */
    public void add(String nuoveauEvent) {
    	listEvenementPasses.add(nuoveauEvent);
    }

    /**
     * Retourne la liste de tous les événements passés enregistrés.
     *
     * @return une ArrayList qui contient les événements passés.
     */
    public ArrayList<String> getListEvenementPasse() {
        return listEvenementPasses;
    }
}