package engine.process;

import engine.donnee.RelationAmicale;
import java.util.ArrayList;
public class RelationsRepository {

	private static RelationsRepository instance = new RelationsRepository();
    private ArrayList<RelationAmicale> relations = new ArrayList<>();
    private RelationsRepository() {}

    public static RelationsRepository getInstance() {
        return instance;
    }

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

    public ArrayList<RelationAmicale> getRelationsDe(String nomSim) {
        ArrayList<RelationAmicale> resultat = new ArrayList<>();
        for (RelationAmicale r : relations) {
            if (r.implique(nomSim)) {
                resultat.add(r);
            }
        }
        return resultat;
    }

    public ArrayList<RelationAmicale> getRelations() {
        return relations;
    }
}
