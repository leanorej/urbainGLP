package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import engine.donnee.RelationAmicale;
import engine.process.RelationsRepository;
import java.util.ArrayList;

public class TestRelationsRepository {

    private RelationsRepository repo;

    @Before
    public void preparer() {
        //vide la liste pour isoler les tests
        repo = RelationsRepository.getInstance();
        repo.getRelations().clear();
    }

    @Test
    public void testGetOuCreerNouvelleRelation() {
        RelationAmicale r = repo.getOuCreer("Francis", "Marie");
        assertNotNull(r);
    }

    @Test
    public void testGetOuCreerMemeObjet() {
        RelationAmicale r1 = repo.getOuCreer("Francis", "Marie");
        RelationAmicale r2 = repo.getOuCreer("Francis", "Marie");
        //doit retourner le m^m objet
        assertSame(r1, r2);
    }

    @Test
    public void testGetOuCreerSymetrie() {
        RelationAmicale r1 = repo.getOuCreer("Francis", "Marie");
        RelationAmicale r2 = repo.getOuCreer("Marie", "Francis");
        assertSame(r1, r2);
    }

    @Test
    public void testGetRelationsDe() {
        repo.getOuCreer("Francis", "Marie");
        repo.getOuCreer("Francis", "Benjamin");
        repo.getOuCreer("Marie", "Benjamin");

        ArrayList<RelationAmicale> relFrancis = repo.getRelationsDe("Francis");
        //Francis est dans 2 relations
        assertEquals(2, relFrancis.size());
    }

    @Test
    public void testGetRelationsDeAucune() {
        ArrayList<RelationAmicale> rel = repo.getRelationsDe("Emmanuel");
        assertEquals(0, rel.size());
    }
}