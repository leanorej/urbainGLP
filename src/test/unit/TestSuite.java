package test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Regroupe tous les tests automatisés 
@RunWith(Suite.class)
@Suite.SuiteClasses({
    //Classes de données
    TestBoundedCounter.class,
    TestBatiment.class,
    TestEvenement.class,
    TestRelationAmicale.class,
    TestRelationsRepository.class,

    //classes de traitement
    TestEtatSimManager.class,
    TestEvenementManager.class,
    TestNiveauManager.class,
    TestRelationSimManager.class
})
public class TestSuite {
	
}