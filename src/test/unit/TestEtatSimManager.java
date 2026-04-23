package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import chrono.Chronometer;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.process.EtatSimManager;
import engine.process.EvenementManager;
import engine.process.GameBuilder;
import engine.donnee.Evenement;


 //On construit un environnement minimal sans GUI ni thread pour isoler la logique
 //@Before : on utilise GameBuilder (comme dans le jeu réel) pour construire map + ville + sims, puis on force manuellement le chrono à une heure précise pour déclencher les effets horaires

public class TestEtatSimManager {

    private EtatSimManager etatManager;
    private EvenementManager eventManager;
    private Sims sims;
    private Ville ville;
    private Chronometer chrono;

    @Before
    public void preparer() {
        Map map = GameBuilder.buildMap();
        ville = GameBuilder.buildVille(map);
        sims = GameBuilder.initializeSims(ville);
        chrono = new Chronometer();
        chrono.init();
        eventManager = GameBuilder.buildInitEventMan(sims);
        etatManager = GameBuilder.buildInitEtat(sims, chrono, ville, eventManager);
        GameBuilder.buildEvent();

        forcerHeure(1, 0);
    }

    private void forcerHeure(int heure, int minute) {
        int secondesCibles = heure * 3600 + minute * 60;
        for (int i = 0; i < secondesCibles; i++) {
            chrono.increment();
        }
    }

    private void avancerUneHeure() {
        for (int i = 0; i < 3600; i++) {
            chrono.increment();
        }
    }
    
    @Test
    public void testFrancisPerditPhysiqueAUsine() {
        Sim francis = sims.getSim("Francis");
        francis.setPositionCourante(ville.get("usine").getPosition());
        int physAvant = francis.getPhysique().getValue();

        etatManager.updateBesoins();
        avancerUneHeure();
        etatManager.updateBesoins();

        assertTrue("Francis doit perdre du physique à l'usine",francis.getPhysique().getValue() < physAvant);
    }

    @Test
    public void testFrancisPerditMentalAUsine() {
        Sim francis = sims.getSim("Francis");
        francis.setPositionCourante(ville.get("usine").getPosition());
        int mentalAvant = francis.getMental().getValue();

        etatManager.updateBesoins();
        avancerUneHeure();
        etatManager.updateBesoins();

        assertTrue("Francis doit perdre du mental à l'usine",francis.getMental().getValue() < mentalAvant);
    }

    @Test
    public void testPiscineAugmentePhysiqueToutLeMonde() {
        Sim genevieve = sims.getSim("Genevieve");
        genevieve.setPositionCourante(ville.get("piscine").getPosition());
        for (int i = 0; i < 20; i++) genevieve.getPhysique().decrement();
        int physAvant = genevieve.getPhysique().getValue();

        etatManager.updateBesoins();
        avancerUneHeure();
        etatManager.updateBesoins();

        assertTrue("La piscine doit augmenter le physique",genevieve.getPhysique().getValue() > physAvant);
    }

    @Test
    public void testPiscineAugmenteMentalToutLeMonde() {
        Sim francis = sims.getSim("Francis");
        francis.setPositionCourante(ville.get("piscine").getPosition());
        for (int i = 0; i < 20; i++) francis.getMental().decrement();
        int mentalAvant = francis.getMental().getValue();

        etatManager.updateBesoins();
        avancerUneHeure();
        etatManager.updateBesoins();

        assertTrue("La piscine doit augmenter le mental",francis.getMental().getValue() > mentalAvant);
    }

    @Test
    public void testJuliePerdPhysiqueAEcole() {
        Sim julie = sims.getSim("Julie");
        julie.setPositionCourante(ville.get("ecole").getPosition());
        for (int i = 0; i < 10; i++) julie.getPhysique().decrement(); // pas au min
        int physAvant = julie.getPhysique().getValue();

        etatManager.updateBesoins();
        avancerUneHeure();
        etatManager.updateBesoins();

        assertTrue("Julie doit perdre du physique à l'école",julie.getPhysique().getValue() < physAvant);
    }

    @Test
    public void testBenjaminPerdPhysiqueAuLycee() {
        Sim benjamin = sims.getSim("Benjamin");
        benjamin.setPositionCourante(ville.get("lycee").getPosition());
        for (int i = 0; i < 10; i++) benjamin.getPhysique().decrement();
        int physAvant = benjamin.getPhysique().getValue();

        etatManager.updateBesoins();
        avancerUneHeure();
        etatManager.updateBesoins();

        assertTrue("Benjamin doit perdre du physique au lycée",benjamin.getPhysique().getValue() < physAvant);
    }

    @Test
    public void testMallAugmenteMental() {
        Sim marie = sims.getSim("Marie");
        marie.setPositionCourante(ville.get("mall").getPosition());
        for (int i = 0; i < 20; i++) marie.getMental().decrement();
        int mentalAvant = marie.getMental().getValue();

        etatManager.updateBesoins();
        avancerUneHeure();
        etatManager.updateBesoins();

        assertTrue("Le mall doit augmenter le mental",marie.getMental().getValue() > mentalAvant);
    }

    @Test
    public void testFaimDecrementeParHeure() {
        // La faim décrémente une fois par heure pour tout le monde
        Sim francis = sims.getSim("Francis");
        francis.setPositionCourante(ville.get("parc").getPosition());
        int faimAvant = francis.getFaim().getValue();

        etatManager.updateBesoins();
        int faimApres = francis.getFaim().getValue();

        assertTrue("La faim doit décrementer chaque heure",faimApres < faimAvant);
    }

    @Test
    public void testEtatFaimAjouteQuandFaimBasse() {
        Sim francis = sims.getSim("Francis");
        while (francis.getFaim().getValue() >= 25) francis.getFaim().decrement();

        etatManager.updateEtats();

        assertTrue("L'état 'faim' doit être ajouté quand faim < 25",francis.getEtats().getEtats().contains("faim"));
    }

    @Test
    public void testEtatFaimSupprimeQuandFaimNormale() {
        Sim francis = sims.getSim("Francis");
        //faim à 80
        while (francis.getFaim().getValue() < 80) francis.getFaim().increment();

        etatManager.updateEtats();

        assertFalse("L'état 'faim' ne doit pas être présent si faim >= 25",francis.getEtats().getEtats().contains("faim"));
    }

    @Test
    public void testEtatTristeAjouteQuandMentalBas() {
        Sim genevieve = sims.getSim("Genevieve");
        while (genevieve.getMental().getValue() >= 25) genevieve.getMental().decrement();

        etatManager.updateEtats();

        assertTrue("L'état 'triste' doit être ajouté quand mental < 25",genevieve.getEtats().getEtats().contains("triste"));
    }

    @Test
    public void testEtatFatigueAjouteQuandPhysiqueBas() {
        Sim marie = sims.getSim("Marie");
        while (marie.getPhysique().getValue() >= 25) marie.getPhysique().decrement();

        etatManager.updateEtats();

        assertTrue("L'état 'fatigue' doit être ajouté quand physique < 25",marie.getEtats().getEtats().contains("fatigue"));
    }

    @Test
    public void testEtatFatigueSupprimeQuandPhysiqueNormal() {
        Sim francis = sims.getSim("Francis");
        while (francis.getPhysique().getValue() < 80) francis.getPhysique().increment();

        etatManager.updateEtats();

        assertFalse("L'état 'fatigue' ne doit pas être présent si physique >= 25",
            francis.getEtats().getEtats().contains("fatigue"));
    }

    @Test
    public void testEtatGeneralEstMoyenneDesScores() {
        int etatG = etatManager.updateEtatGeneral();
        assertEquals("L'état général doit être la moyenne des scores",
            100, etatG);
    }

    @Test
    public void testEtatGeneralBaisseQuandSimEnMauvaiseEtat() {
        Sim francis = sims.getSim("Francis");
        while (francis.getFaim().getValue() > 0) francis.getFaim().decrement();
        while (francis.getMental().getValue() > 0) francis.getMental().decrement();

        int etatG = etatManager.updateEtatGeneral();

        assertTrue("L'état général doit baisser quand un sim est en mauvaise santé",
            etatG < 100);
    }

    @Test
    public void testUpdateBesoinsAvecChronoNull() {
        EvenementManager evMan = GameBuilder.buildInitEventMan(sims);
        EtatSimManager managerNull = new EtatSimManager(sims, null, ville, evMan);
        managerNull.updateBesoins();
        assertTrue(true);
    }

    @Test
    public void testAppelsRepetesUpdateBesoins() {
        for (int i = 0; i < 100; i++) {
            etatManager.updateBesoins();
        }
        assertTrue(true);
    }
}
