package test.performance;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import chrono.Chronometer;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sims;
import engine.process.EtatSimManager;
import engine.process.EvenementManager;
import engine.process.GameBuilder;
import engine.process.NiveauManager;
import engine.process.RelationSimManager;


//Tests de performance, rep à ces questions : Est-ce que la machine ralentit si on lance le jeu ?Combien de temps prend updateBesoins() pour les 6 sims ? Le jeu tient-il sur une semaine complète ?

public class TestPerformance {

    private EtatSimManager etatManager;
    private EvenementManager eventManager;
    private RelationSimManager relationManager;
    private NiveauManager niveauManager;
    private Sims sims;
    private Ville ville;
    private Chronometer chrono;

    @Before
    public void preparer() {
    	Logger.getRootLogger().setLevel(Level.OFF);
        Map map = GameBuilder.buildMap();
        ville = GameBuilder.buildVille(map);
        sims = GameBuilder.initializeSims(ville);
        chrono = new Chronometer();
        chrono.init();
        eventManager = GameBuilder.buildInitEventMan(sims);
        etatManager = GameBuilder.buildInitEtat(sims, chrono, ville, eventManager);
        relationManager = GameBuilder.buildInitRelation(sims, chrono, ville);
        niveauManager = GameBuilder.buildInitNiv();
        GameBuilder.buildEvent();
        for (int i = 0; i < 3600; i++) chrono.increment();
    }

    @Test
    public void testTempsUpdateBesoins1000Appels() {
        long debut = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            etatManager.updateBesoins();
        }

        long duree = System.currentTimeMillis() - debut;
        System.out.println("[PERF] 1000 x updateBesoins() : " + duree + "ms");

        assertTrue("1000 appels à updateBesoins() doivent prendre moins de 1000ms, durée réelle : " + duree + "ms",duree < 1000);
    }

    @Test
    public void testTempsUpdateEtats1000Appels() {
        long debut = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            etatManager.updateEtats();
        }

        long duree = System.currentTimeMillis() - debut;
        System.out.println("[PERF] 1000 x updateEtats() : " + duree + "ms");

        assertTrue("1000 appels à updateEtats() doivent prendre moins de 1000ms, durée réelle : " + duree + "ms",duree < 1000);
    }

    @Test
    public void testTempsUpdateRelations100Heures() {
        long debut = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            for (int s = 0; s < 3600; s++) chrono.increment();
            relationManager.updateRelations();
        }

        long duree = System.currentTimeMillis() - debut;
        System.out.println("[PERF] 100 heures de relations simulées : " + duree + "ms");

        assertTrue("100 heures de relations doivent prendre moins de 2000ms, durée réelle : " + duree + "ms",duree < 2000);
    }

    @Test
    public void testTempsUneJourneeComplete() {
        long debut = System.currentTimeMillis();

        for (int heure = 0; heure < 24; heure++) {
            for (int s = 0; s < 3600; s++) chrono.increment();
            etatManager.updateBesoins();
            etatManager.updateEtats();
            relationManager.updateRelations();
        }

        long duree = System.currentTimeMillis() - debut;
        System.out.println("[PERF] Simulation 1 journée complète (24h) : " + duree + "ms");

        assertTrue("Une journée simulée doit prendre moins de 2000ms, durée réelle : " + duree + "ms",duree < 2000);
    }

    @Test
    public void testTempsUneSemaineComplete() {
        long debut = System.currentTimeMillis();

        for (int jour = 0; jour < 7; jour++) {
            for (int heure = 0; heure < 24; heure++) {
                for (int s = 0; s < 3600; s++) chrono.increment();
                etatManager.updateBesoins();
                etatManager.updateEtats();
                relationManager.updateRelations();
            }
            niveauManager.majNiveau(sims.getSims().size());
        }

        long duree = System.currentTimeMillis() - debut;
        System.out.println("[PERF] Simulation 1 semaine complète (7j × 24h) : " + duree + "ms");

        assertTrue("Une semaine simulée doit prendre moins de 5000ms, durée réelle : " + duree + "ms",duree < 5000);
    }

    @Test
    public void testTempsUpdateEtatGeneral10000Appels() {
        long debut = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            etatManager.updateEtatGeneral();
        }

        long duree = System.currentTimeMillis() - debut;
        System.out.println("[PERF] 10 000 x updateEtatGeneral() : " + duree + "ms");

        assertTrue("10 000 appels à updateEtatGeneral() doivent prendre moins de 500ms, durée réelle : " + duree + "ms",duree < 500);
    }
}
