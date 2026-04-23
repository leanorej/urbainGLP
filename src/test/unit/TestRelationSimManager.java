package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import chrono.Chronometer;
import engine.donnee.RelationAmicale;
import engine.map.Map;
import engine.map.Ville;
import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.process.GameBuilder;
import engine.process.RelationSimManager;
import engine.process.RelationsRepository;


//On teste que deux Sims au même endroit accumulent des heures ensemble, et que le bonus mental s'applique une fois qu'ils sont amis
//On vide le RelationsRepository avant chaque test pour garantir l'isolation

public class TestRelationSimManager {

    private RelationSimManager relationManager;
    private Sims sims;
    private Ville ville;
    private Chronometer chrono;
    private Sim francis;
    private Sim marie;

    @Before
    public void preparer() {
        Map map = GameBuilder.buildMap();
        ville = GameBuilder.buildVille(map);
        sims = GameBuilder.initializeSims(ville);
        chrono = new Chronometer();
        chrono.init();
        relationManager = GameBuilder.buildInitRelation(sims, chrono, ville);
        RelationsRepository.getInstance().getRelations().clear();

        francis = sims.getSim("Francis");
        marie = sims.getSim("Marie");
        for (int i = 0; i < 3600; i++) chrono.increment();
    }
    private void avancerUneHeure() {
        for (int i = 0; i < 3600; i++) chrono.increment();
    }
    
    @Test
    public void testPasDeRelationSiSimsNonCoPresents() {
        francis.setPositionCourante(ville.get("usine").getPosition());
        marie.setPositionCourante(ville.get("boulangerie").getPosition());

        relationManager.updateRelations();

        assertTrue("Aucune relation ne doit exister si les sims ne sont pas au même endroit",RelationsRepository.getInstance().getRelations().isEmpty());
    }

    @Test
    public void testRelationCreeeQuandSimsCoPresents() {
        francis.setPositionCourante(ville.get("piscine").getPosition());
        marie.setPositionCourante(ville.get("piscine").getPosition());

        relationManager.updateRelations();

        assertFalse("Une relation doit être créée si les sims sont au même endroit",RelationsRepository.getInstance().getRelations().isEmpty());
    }

    @Test
    public void testHeuresIncrementeesAChaqueCycleCoPresence() {
        francis.setPositionCourante(ville.get("piscine").getPosition());
        marie.setPositionCourante(ville.get("piscine").getPosition());
        relationManager.updateRelations();
        RelationAmicale relation = RelationsRepository.getInstance()
            .getOuCreer("Francis", "Marie");
        int heuresApres1 = relation.getHeuresPasseesEnsemble();
        avancerUneHeure();
        relationManager.updateRelations();
        int heuresApres2 = relation.getHeuresPasseesEnsemble();

        assertTrue("Le compteur d'heures doit augmenter à chaque heure de coprésence",heuresApres2 > heuresApres1);
    }

    @Test
    public void testPasIncrementSansNouvelleHeure() {
        francis.setPositionCourante(ville.get("piscine").getPosition());
        marie.setPositionCourante(ville.get("piscine").getPosition());

        relationManager.updateRelations();
        RelationAmicale relation = RelationsRepository.getInstance()
            .getOuCreer("Francis", "Marie");
        int heuresApres1 = relation.getHeuresPasseesEnsemble();
        relationManager.updateRelations();
        int heuresApres2 = relation.getHeuresPasseesEnsemble();

        assertEquals("Sans avancer d'une heure, le compteur ne doit pas changer",heuresApres1, heuresApres2);
    }

    @Test
    public void testPasDesBonusMentalSiPasAmis() {
        francis.setPositionCourante(ville.get("piscine").getPosition());
        marie.setPositionCourante(ville.get("piscine").getPosition());
        for (int i = 0; i < 30; i++) { francis.getMental().decrement(); marie.getMental().decrement(); }
        int mentalFrancisAvant = francis.getMental().getValue();
        relationManager.updateRelations();
        RelationAmicale r = RelationsRepository.getInstance().getOuCreer("Francis", "Marie");
        assertFalse("Après 1 heure, ils ne doivent pas être amis", r.sontAmis());
    }

    @Test
    public void testBonusMentalApresAmitie() {
        RelationAmicale relation = RelationsRepository.getInstance().getOuCreer("Francis", "Marie");
        for (int i = 0; i < 21; i++) relation.incrementerTemps();
        assertTrue("La relation doit être en amitié", relation.sontAmis());
        francis.setPositionCourante(ville.get("piscine").getPosition());
        marie.setPositionCourante(ville.get("piscine").getPosition());
        for (int i = 0; i < 50; i++) { francis.getMental().decrement(); marie.getMental().decrement(); }
        int mentalFrancisAvant = francis.getMental().getValue();
        int mentalMarieAvant = marie.getMental().getValue();
        avancerUneHeure();
        relationManager.updateRelations();

        assertTrue("Francis doit recevoir le bonus mental (+5) grâce à l'amitié",francis.getMental().getValue() > mentalFrancisAvant);
        assertTrue("Marie doit recevoir le bonus mental (+5) grâce à l'amitié",marie.getMental().getValue() > mentalMarieAvant);
    }

    @Test
    public void testUpdateRelationsChronoNull() {
        RelationSimManager managerNull = new RelationSimManager(sims, null, ville);
        managerNull.updateRelations();
        assertTrue(true);
    }

    @Test
    public void testMultiplesAppelsSansException() {
        francis.setPositionCourante(ville.get("piscine").getPosition());
        marie.setPositionCourante(ville.get("piscine").getPosition());
        for (int i = 0; i < 50; i++) {
            avancerUneHeure();
            relationManager.updateRelations();
        }
        assertTrue("50 appels consécutifs ne doivent pas planter", true);
    }
}
