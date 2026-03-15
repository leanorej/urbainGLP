package engine.process;

import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.map.Block;
import engine.map.Batiment;
import engine.map.Ville;
import chrono.Chronometer;

/**
 * @author JEAN
 */
public class MobileSimManager {
    private Sims sims;
    private Sim simChoisi = null;
    private Chronometer chrono;
    private Ville ville;

    private int derniereMinuteTraitee = -1;
    private int derniereHeureTraiteePhysique = -1;
    private int derniereHeureTraiteeFaim = -1;

    public MobileSimManager(Sims sims, Chronometer chrono, Ville ville) {
        this.sims = sims;
        this.chrono = chrono;
        this.ville = ville;
    }

    public Sims getSims() {
        return sims;
    }

    public void nextMove(int heureCourante) {
        for (Sim sim : sims.getSims().values()) {
            if (sim.getRoutine().possedeUneNouvelleTache(heureCourante)) {
                sim.setPositionCourante(sim.getRoutine().get(heureCourante).getPosition());
            }
        }
    }

    public void simMove(Block position) {
        if (simChoisi == null) {//cas ou aucun sims est selectionné
            for (Sim sim : sims.getSims().values()) {
                if (sim.getPositionCourante().getLine() == position.getLine() &&
                        sim.getPositionCourante().getColumn() == position.getColumn()) {
                    simChoisi = sim;
                }
            }
        } else {//cas où on a dejà selectionné un sim e on lui donne une nouvelle position
            simChoisi.setPositionCourante(position);
            simChoisi = null;
        }
    }

    public void updateBesoins() {
        if (chrono == null) return;
        
        int heureCourante = chrono.getHour().getValue();
        int minuteCourante = chrono.getMinute().getValue();
        //updatedBesoin une fois par minute
        if (minuteCourante == derniereMinuteTraitee) return;

        for (Sim sim : sims.getSims().values()) {
            //décrémentation faim (1 fois par heure)
            if (minuteCourante == 0 && heureCourante != derniereHeureTraiteeFaim) {
                sim.getFaim().decrement();
            }

            //detection du lieu par pos
            String lieuActuel = "rue";
            for (Batiment b : ville.getBatimentsList()) {
                if (b.getPosition().getLine() == sim.getPositionCourante().getLine() &&
                    b.getPosition().getColumn() == sim.getPositionCourante().getColumn()) {
                    lieuActuel = b.getNomBatiment().toLowerCase().trim();
                    break;
                }
            }

            //Physique et Mental (1 fois par heure)
            if (heureCourante != derniereHeureTraiteePhysique) {
                if (lieuActuel.equals("usine")) {
                    sim.getPhysique().decrement();
                } else if (lieuActuel.equals("piscine") || lieuActuel.equals("salle")) {
                    sim.getPhysique().increment();
                    sim.getMental().increment();
                }
            }

            //incrémentation faim (si boulangerie/mall)
            if (minuteCourante == 0 || minuteCourante == 20 || minuteCourante == 40) {
                if (lieuActuel.equals("boulangerie") || lieuActuel.equals("mall")) {
                    sim.getFaim().increment();
                }
            }
        }
        
        derniereMinuteTraitee = minuteCourante;
        if (heureCourante != derniereHeureTraiteePhysique) derniereHeureTraiteePhysique = heureCourante;
        if (minuteCourante == 0) derniereHeureTraiteeFaim = heureCourante;
    }
}