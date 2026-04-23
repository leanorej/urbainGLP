package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import engine.donnee.Evenement;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.process.EvenementManager;
import engine.process.GameBuilder;

//vérifie l'impact d'un événement sur une barre de vie précise

public class TestEvenementManager {

    private EvenementManager eventManager;
    private Sims sims;
    private Sim francis;
    private Sim marie;

    @Before
    public void preparer() {
        Map map = GameBuilder.buildMap();
        Ville ville = GameBuilder.buildVille(map);
        sims = GameBuilder.initializeSims(ville);
        eventManager = GameBuilder.buildInitEventMan(sims);
        GameBuilder.buildEvent();

        francis = sims.getSim("Francis");
        marie = sims.getSim("Marie");
    }
    
    @Test
    public void testEvenementSautRepasDecreimenteFaim() {
        Evenement sautRepas = new Evenement("sautRepas", "faim", 20);
        int faimAvant = francis.getFaim().getValue();

        eventManager.updateBesoinsEvenement(francis, sautRepas);

        assertEquals("sautRepas doit retirer 20 de faim",faimAvant - 20, francis.getFaim().getValue());
    }

    @Test
    public void testEvenementFaimNePasDescendreSousMin() {
        while (francis.getFaim().getValue() > 10) francis.getFaim().decrement();
        Evenement sautRepas = new Evenement("sautRepas", "faim", 20);

        eventManager.updateBesoinsEvenement(francis, sautRepas);

        assertEquals("La faim ne peut pas descendre sous 0", 0, francis.getFaim().getValue());
    }

    @Test
    public void testEvenementMaladeDécrementePhysique() {
        Evenement malade = new Evenement("malade", "physique", 15);
        int physAvant = francis.getPhysique().getValue();

        eventManager.updateBesoinsEvenement(francis, malade);

        assertEquals("malade doit retirer 15 de physique",physAvant - 15, francis.getPhysique().getValue());
    }

    @Test
    public void testEvenementUrgenceMedicaleGrandImpact() {
        Evenement urgence = new Evenement("urgenceMedicale", "physique", 50);
        int physAvant = francis.getPhysique().getValue();

        eventManager.updateBesoinsEvenement(francis, urgence);

        assertEquals("urgenceMedicale doit retirer 50 de physique",physAvant - 50, francis.getPhysique().getValue());
    }

    @Test
    public void testEvenementPhysiqueNePasDescendreSousMin() {
        while (francis.getPhysique().getValue() > 10) francis.getPhysique().decrement();
        Evenement urgence = new Evenement("urgenceMedicale", "physique", 50);

        eventManager.updateBesoinsEvenement(francis, urgence);

        assertEquals("Le physique ne peut pas descendre sous 0", 0, francis.getPhysique().getValue());
    }

    @Test
    public void testEvenementDisputeDecreimenteMental() {
        Evenement dispute = new Evenement("dispute", "mental", 20);
        int mentalAvant = marie.getMental().getValue();

        eventManager.updateBesoinsEvenement(marie, dispute);

        assertEquals("dispute doit retirer 20 de mental",mentalAvant - 20, marie.getMental().getValue());
    }

    @Test
    public void testEvenementMentalNePasDescendreSousMin() {
        while (marie.getMental().getValue() > 10) marie.getMental().decrement();
        Evenement dispute = new Evenement("dispute", "mental", 20);

        eventManager.updateBesoinsEvenement(marie, dispute);

        assertEquals("Le mental ne peut pas descendre sous 0", 0, marie.getMental().getValue());
    }

    @Test
    public void testEvenementImpactZeroNeChlangePasLaBarre() {
        Evenement neutre = new Evenement("neutre", "faim", 0);
        int faimAvant = francis.getFaim().getValue();

        eventManager.updateBesoinsEvenement(francis, neutre);

        assertEquals("Un impact de 0 ne doit rien changer", faimAvant, francis.getFaim().getValue());
    }

    @Test
    public void testEvenementNAffectePasUnAutreSim() {
        Evenement sautRepas = new Evenement("sautRepas", "faim", 20);
        int faimMarie = marie.getFaim().getValue();

        eventManager.updateBesoinsEvenement(francis, sautRepas);

        assertEquals("L'événement appliqué à Francis ne doit pas affecter Marie",faimMarie, marie.getFaim().getValue());
    }
}
