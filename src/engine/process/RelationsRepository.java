package engine.process;

import engine.donnee.RelationAmicale;
/**
 * Gere toutes les {@link engine.donnee.RelationAmicale} entre Sims.
 * Elle garantit qu'il n'existe qu'une seule relation entre deux Sims donnés.
 *
 * @author JEAN
 */
import java.util.ArrayList;
public class RelationsRepository {
	

	/**
     * Instance unique (pattern Singleton).
     */
	private static RelationsRepository instance = new RelationsRepository();
    /**
     * Liste interne de toutes les relations amicales enregistrées.
     */
    private ArrayList<RelationAmicale> relations = new ArrayList<>();
    /**
     * Constructeur privé (pattern Singleton).
     */
    private RelationsRepository() {}

    /**
     * Instance unique (pattern Singleton).
     */
    public static RelationsRepository getInstance() {
        return instance;
    }
    /**
     * Retourne l'objet {@link engine.donnee.RelationAmicale} entre deux Sim si elle existe,
     * sinon elle la créer.
     * @param nom1 nom premier sim
     * @param nom2 nom deuxième sim
     * @return la relation amicale entre les deux sim si elle existe sinon il créer
     */
    public RelationAmicale getOuCreer(String nom1, String nom2) {
        for (RelationAmicale r : relations) {
            if (r.impliqueLesDeuxSims(nom1, nom2)) {
                return r;
            }
        }
        RelationAmicale nouvelle = new RelationAmicale(nom1, nom2);
        relations.add(nouvelle);
        return nouvelle;
    }

    /**
     * Retourne toute les relations amicales du sim donnée
     * @param nomSim nom du sim dont on veux chercher toutes les relation amicales
     * @return la {@link ArrayList} de toute les relation du sim
     */
    public ArrayList<RelationAmicale> getRelationsDe(String nomSim) {
        ArrayList<RelationAmicale> resultat = new ArrayList<>();
        for (RelationAmicale r : relations) {
            if (r.implique(nomSim)) {
                resultat.add(r);
            }
        }
        return resultat;
    }

    /**
     * Retourne la liste de toutes les relations amicales enregistrées.
     * @return la {@link ArrayList} de toutes les relations de tout les Sims.
     */
    public ArrayList<RelationAmicale> getRelations() {
        return relations;
    }
}
