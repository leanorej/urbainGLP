package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import engine.donnee.RelationAmicale;

public class TestRelationAmicale {

    private RelationAmicale relation;

    @Before
    public void preparer() {
        relation = new RelationAmicale("Francis", "Marie");
    }

    @Test
    public void testPasAmisAuDebut() {
        assertFalse(relation.sontAmis());
    }

    @Test
    public void testHeuresZeroAuDebut() {
        assertEquals(0, relation.getHeuresPasseesEnsemble());
    }

    @Test
    public void testDevenirAmiApres20h() {
        for (int i = 0; i < 20; i++) {
            relation.incrementerTemps();
        }
        assertTrue(relation.sontAmis());
    }

    @Test
    public void testCompteurRedemarre() {
        //après 20h (le seuil) amis=true et conpteur repart à 1
        for (int i = 0; i < 20; i++) {
            relation.incrementerTemps();
        }
        assertEquals(1, relation.getHeuresPasseesEnsemble());
    }

    @Test
    public void testCompteurApresAmitie() {
        for (int i = 0; i < 20; i++) {
            relation.incrementerTemps();
        }
        //une heure de + après amitié donc cpt à 2
        relation.incrementerTemps();
        assertEquals(2, relation.getHeuresPasseesEnsemble());
    }

    @Test
    public void testImplique() {
        assertTrue(relation.implique("Francis"));
        assertTrue(relation.implique("Marie"));
        assertFalse(relation.implique("Benjamin"));
    }

    @Test
    public void testImpliqueLesDeuxSimsSymetrie() {
        assertTrue(relation.impliqueLesDeuxSims("Francis", "Marie"));
        assertTrue(relation.impliqueLesDeuxSims("Marie", "Francis"));
        assertFalse(relation.impliqueLesDeuxSims("Francis", "Benjamin"));
    }
}