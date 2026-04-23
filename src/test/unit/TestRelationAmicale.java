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
    public void testHeuresInitialesNegatif() {
        assertEquals(-1, relation.getHeuresPasseesEnsemble());
    }

    @Test
    public void testPasAmiAvant20h() {
        //car amis à 20h
        for (int i = 0; i < 19; i++) {
            relation.incrementerTemps();
        }
        assertFalse(relation.sontAmis());
    }

    @Test
    public void testDevenirAmiApres20Appels() {
        // 20 appels : amis et il faut 21 apl pour que heures == 20
 
        for (int i = 0; i < 21; i++) {
            relation.incrementerTemps();
        }
        assertTrue(relation.sontAmis());
    }

    @Test
    public void testCompteurRedemarre() {
        for (int i = 0; i < 21; i++) {
            relation.incrementerTemps();
        }
        assertEquals(1, relation.getHeuresPasseesEnsemble());
    }

    @Test
    public void testCompteurContinueApresAmitie() {
        for (int i = 0; i < 21; i++) {
            relation.incrementerTemps();
        }
        relation.incrementerTemps();
        assertEquals(2, relation.getHeuresPasseesEnsemble());
    }

    @Test
    public void testImpliqueSim1() {
        assertTrue(relation.implique("Francis"));
    }

    @Test
    public void testImpliqueSim2() {
        assertTrue(relation.implique("Marie"));
    }

    @Test
    public void testImpliqueTiersExclu() {
        assertFalse(relation.implique("Benjamin"));
    }

    @Test
    public void testImpliqueLesDeuxSimsDansOrdre() {
        assertTrue(relation.impliqueLesDeuxSims("Francis", "Marie"));
    }

    @Test
    public void testImpliqueLesDeuxSimsSymetrie() {
        //relation est symétrique
        assertTrue(relation.impliqueLesDeuxSims("Marie", "Francis"));
    }

    @Test
    public void testImpliqueLesDeuxSimsMauvairesPaire() {
        assertFalse(relation.impliqueLesDeuxSims("Francis", "Benjamin"));
    }
}
