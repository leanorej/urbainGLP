package engine.process;

import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.donnee.Evenement;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author CHOUAREF
 */
public class EvenementManager {
    private Sims sims;
    private Sim simChoisi = null;
    private EvenementsRepository eventRep = EvenementsRepository.getInstance();;

    public EvenementManager(Sims sims) {
        this.sims = sims;
    }

    public Sims getSims() {
        return sims;
    }

    public Sim getSimChoisi() {
        return simChoisi;
    }

    
    
    
    public void updateBesoinsEvenement(Sim sim, Evenement event) {

        switch(event.getBarreImpactee()){
            case "faim":
                for(int i=0 ; i<event.getImpact();i++){
                    sim.getFaim().decrement();
                }
                break;

            case "physique":
                for(int i=0 ; i<event.getImpact();i++){
                    sim.getPhysique().decrement();
                }
                break;

            case "mental":
                for(int i=0 ; i<event.getImpact();i++){
                    sim.getMental().decrement();
                }
                break;
            }
        //j'enregistre les evenement dans un list qui est dans un classe de traitement faut changer ca
        DerniersEvenementsRepository.getInstance().add(sim.getNom()+"-> "+event.toString());
    }
    

    
    public void generatorRandomEvent() { 
    	Sim sim = getRandomSim(); 
    	Evenement event = getRandomEvent(); 
    	updateBesoinsEvenement(sim, event); 
    }

    
	private Sim getRandomSim() {
		Random r = new Random() ;
		Sim[] liste = sims.getSims().values().toArray(new Sim[0]) ; 
		return liste[r.nextInt(liste.length)] ;
	}

	private Evenement getRandomEvent() {
		Random r = new Random() ;
		Evenement[] liste = eventRep.getValues().values().toArray(new Evenement[0]) ;
		return liste[r.nextInt(liste.length)] ;
	}

}