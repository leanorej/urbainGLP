package engine.process;

import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.map.Block;
import org.apache.log4j.Logger;
import log.LoggerUtility;

/**
 * Gestion de tout les deplacement automatique donc par les deux routines et manuel donc par l'utilisateur
 * @author CHOUAREF
 * @author JEAN
 * @author LOUKILI
 */
public class MobileSimManager {
    private Sims sims;
    private Sim simChoisi = null;
	private static Logger logger = LoggerUtility.getLogger(MobileSimManager.class, "html");


    public MobileSimManager(Sims sims) {
        this.sims = sims;
    }

    public Sims getSims() {
        return sims;
    }

    public Sim getSimChoisi() {
        return simChoisi;
    }

    /**
     * Applique les déplacements automatiques selon la routine normale.
     *
     * @param heureCourante heure courante
     */
    public void nextMove(int heureCourante) {
        for (Sim sim : sims.getSims().values()) {
            if (sim.getRoutine().containsKey(heureCourante)) {
                sim.setPositionCourante(sim.getRoutine().get(heureCourante).getPosition());
            }
        }
    }


    /**
     * Applique les déplacements automatiques selon la routine normale.
     *
     * @param heureCourante heure courante
     */
    public void nextMoveWeekEnd(int heureCourante) {
        for (Sim sim : sims.getSims().values()) {
            if (sim.getRoutineWeekEnd().containsKey(heureCourante)) {
                sim.setPositionCourante(sim.getRoutineWeekEnd().get(heureCourante).getPosition());
            }
        }
    }
    

    /**
     * Gère un déplacement manuel par clic utilisateur.
     *
     * Fonctionnement :si aucun Sim n'est sélectionné, 
     * l'apelle de cette methode sélectionne celui situé sur la case indiquée ;
     * sinon si  un Sim est déjà sélectionné, le déplace vers la case choisie
     * puis annule la sélection.
     * @param position du Block choisi par l'utilisateur
     */
    public void simMove(Block position) {
        if (simChoisi == null) {//cas ou aucun sims est selectionné
            for (Sim sim : sims.getSims().values()) {
                if (sim.getPositionCourante().getLine() == position.getLine() &&
                        sim.getPositionCourante().getColumn() == position.getColumn()) {
                    simChoisi = sim;
                    logger.info("Sim "+ simChoisi.getNom() +" selectionne en position : "+ simChoisi.getPositionCourante()) ;
                }
            }
        } else {//cas où on a dejà selectionné un sim e on lui donne une nouvelle position
            simChoisi.setPositionCourante(position);
            logger.info("Sim "+ simChoisi.getNom() +" deplace en position : "+ simChoisi.getPositionCourante()) ;
            simChoisi = null;
        }
    }


}
