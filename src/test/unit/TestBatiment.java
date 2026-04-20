package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import engine.map.Batiment;
import engine.map.Block;

public class TestBatiment {

    private Batiment usine;

    @Before
    public void preparer() {
        //on test usine masi on pourrait faire tous les autres
        usine = new Batiment(new Block(0, 22), "usine", 4, 8);
    }

    @Test
	public void testContientCoinSupGauche() {
        assertTrue(usine.contient(new Block(0, 22)));
    }

    @Test
    public void testContientInterieur() {
        assertTrue(usine.contient(new Block(2, 25)));
    }

    @Test
    public void testContientHorsZone() {
        assertFalse(usine.contient(new Block(10, 10)));
    }

    @Test
    public void testBordExclu() {
        //ligne 4 = ligneMin(0) + largeur(4) → hors zone
        assertFalse(usine.contient(new Block(4, 22)));
    }

    @Test
    public void testBordDroitExclu() {
        //col 30 = colonneMin(22) + longueur(8) → hors zone
        assertFalse(usine.contient(new Block(0, 30)));
    }
}