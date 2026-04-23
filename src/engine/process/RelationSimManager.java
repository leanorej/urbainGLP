package engine.process;

import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.map.Batiment;
import engine.map.Ville;
 
import engine.donnee.RelationAmicale;

import java.util.ArrayList;
 
import chrono.Chronometer;
import org.apache.log4j.Logger;
import log.LoggerUtility;



/**
 * Gère relations amicales entre Sims.
 * Cette classe analyse la position des Sims présents
 * dans la ville afin de déterminer lesquels passent du temps dans le meme batiment.
 * Lorsqu'au moins deux Sims se trouvent dans un même bâtiment, leur
 * relation amicale est mise à jour.
 * Si deux Sims déjà amis restent ensemble, leur moral augmente de 5.
 *
 * @author JEAN
 */
public class RelationSimManager {
    private Sims sims;
    private Sim simChoisi = null;
    private Chronometer chrono;
    private Ville ville;
    private RelationsRepository relationsRep = RelationsRepository.getInstance();

    private int derniereHeureTraiteeRelations = -1;
    
	private static Logger logger = LoggerUtility.getLogger(RelationSimManager.class, "html");

    public RelationSimManager(Sims sims, Chronometer chrono, Ville ville) {
        this.sims = sims;
        this.chrono = chrono;
        this.ville = ville;
    }

    public Sims getSims() {
        return sims;
    }

    public Sim getSimChoisi() {
        return simChoisi;
    }
    
    

    /**
     * Met à jour les relations sociales entre tous les Sims.
     *
     * Realisé une fois par heure,
     * recherche des paires de Sims présentes dans un même bâtiment,
     * augmentation du temps passé ensemble,
     * si les deux Sims sont amis, augmentation du moral pour chaque heure.
     */
    public void updateRelations() {
        if (chrono == null) return;

        int heureCourante = chrono.getHour().getValue();

        if (heureCourante == derniereHeureTraiteeRelations) return;
        derniereHeureTraiteeRelations = heureCourante;

        Sim[] listeSims = sims.getSims().values().toArray(new Sim[0]);

        for (int i = 0; i < listeSims.length; i++) {
            for (int j = i + 1; j < listeSims.length; j++) {
                Sim simA = listeSims[i];
                Sim simB = listeSims[j];
                //on verifie si ils sont dns le meme batiment (sims par sim)
                boolean memeEndroit = false;
                for (Batiment b : ville.getBatimentsList()) {
                    if (b.contient(simA.getPositionCourante())
                     && b.contient(simB.getPositionCourante())) {
                        memeEndroit = true;
                        break;
                    }
                }

                if (memeEndroit) {
                    logger.debug(simA.getNom() +" et "+ simB.getNom() + " passent du temps ensemble.") ;
                    RelationAmicale relation = relationsRep.getOuCreer(
                        simA.getNom(), simB.getNom()
                    );
                    relation.incrementerTemps();
                    //on augment de 5 pourcnts si ils sont amis et dans le memee batiment
                    if (relation.sontAmis()) {
                        simA.getMental().increment();
                        simA.getMental().increment();
                        simA.getMental().increment();
                        simA.getMental().increment();
                        simA.getMental().increment();
                        simB.getMental().increment();
                        simB.getMental().increment();
                        simB.getMental().increment();
                        simB.getMental().increment();
                        simB.getMental().increment();
                    }
                }
            }
        }
    }

   

}