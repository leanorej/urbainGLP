package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import engine.donnee.Evenement;

public class TestEvenement {

    private Evenement evenement;

    @Before
    public void preparer() {
        evenement = new Evenement("sautRepas", "faim", 20);
    }

    @Test
    public void testGetNom() {
        assertEquals("sautRepas", evenement.getNom());
    }

    @Test
    public void testGetBarreImpactee() {
        assertEquals("faim", evenement.getBarreImpactee());
    }

    @Test
    public void testGetImpact() {
        assertEquals(20, evenement.getImpact());
    }

    @Test
    public void testToString() {
        String resultat = evenement.toString();
        assertTrue(resultat.contains("sautRepas"));
        assertTrue(resultat.contains("faim"));
        assertTrue(resultat.contains("20"));
    }
}