package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import engine.process.NiveauManager;
import engine.process.GameBuilder;

// NiveauManager gère la progression : niveau monte tous les 3 jours si les 6 sims sont vivants
public class TestNiveauManager {

    private NiveauManager niveauManager;

    @Before
    public void preparer() {
        niveauManager = GameBuilder.buildInitNiv();
    }

    @Test
    public void testNiveauInitialEst1() {
        assertEquals("Le niveau initial doit être 1", 1, niveauManager.getNiveau());
    }

    @Test
    public void testNbEvenementsInitialEst1() {
        assertEquals("Le nombre d'événements par jour initial doit être 1",1, niveauManager.getNbEventJour());
    }

    @Test
    public void testCompteurJourInitialEst0() {
        assertEquals("Le compteur de jours initial doit être 0",0, niveauManager.getCptJour());
    }

    @Test
    public void testNiveauNeMontePasAvant3Jours() {
        niveauManager.majNiveau(6);
        niveauManager.majNiveau(6);
        assertEquals("Le niveau ne doit pas monter avant 3 jours", 1, niveauManager.getNiveau());
    }

    @Test
    public void testNiveauMontePasAu3eJourSiMoinsde6Sims() {
        niveauManager.majNiveau(5);
        niveauManager.majNiveau(5);
        niveauManager.majNiveau(5);
        assertEquals("Le niveau ne doit pas monter s'il y a moins de 6 sims",1, niveauManager.getNiveau());
    }

    @Test
    public void testNiveauMonteAu3eJourAvec6Sims() {
        niveauManager.majNiveau(6);
        niveauManager.majNiveau(6);
        niveauManager.majNiveau(6);
        assertEquals("Le niveau doit passer à 2 après 3 jours avec 6 sims",2, niveauManager.getNiveau());
    }

    @Test
    public void testNbEvenementsMonteAvecLeNiveau() {
        niveauManager.majNiveau(6);
        niveauManager.majNiveau(6);
        niveauManager.majNiveau(6);
        assertEquals("Le nombre d'événements par jour doit aussi monter",2, niveauManager.getNbEventJour());
    }

    @Test
    public void testDeuxMonteesDeNiveau() {
        for (int i = 0; i < 6; i++) {
            niveauManager.majNiveau(6);
        }
        assertEquals("Après 6 jours avec 6 sims, le niveau doit être 3",3, niveauManager.getNiveau());
    }
    
    @Test
    public void testCompteurJourIncrementeAChaqueMajNiveau() {
        niveauManager.majNiveau(6);
        assertEquals("Le compteur de jours doit s'incrémenter à chaque appel",1, niveauManager.getCptJour());
    }

    @Test
    public void testCompteurJourApres5Jours() {
        for (int i = 0; i < 5; i++) {
            niveauManager.majNiveau(6);
        }
        assertEquals("Le compteur doit valoir 5 après 5 appels",5, niveauManager.getCptJour());
    }

    @Test
    public void testMajNiveauAvec0Sims() {
        for (int i = 0; i < 10; i++) {
            niveauManager.majNiveau(0);
        }
        assertEquals("Le niveau ne doit pas monter s'il n'y a pas de sims",1, niveauManager.getNiveau());
    }

    @Test
    public void testAppelsMassifsSansException() {
        for (int i = 0; i < 1000; i++) {
            niveauManager.majNiveau(6);
        }
        assertTrue("1000 appels ne doivent pas générer d'exception", true);
    }
}
