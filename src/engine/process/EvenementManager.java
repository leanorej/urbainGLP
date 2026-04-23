package engine.process;

import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.donnee.Evenement;

import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;
import log.LoggerUtility;


/**
 * Gere les événements, en mettant à jour la barre d’états suite à l’événement
 * et généré aléatoirement un événement qui implique un sim
 * @author CHOUAREF
 */
public class EvenementManager {
    private Sims sims;
    private Sim simChoisi = null;
    private EvenementsRepository eventRep = EvenementsRepository.getInstance();;
     
    private ArrayList<String> derniersEvenements = new ArrayList<>();
	private static Logger logger = LoggerUtility.getLogger(EvenementManager.class, "html");

    public EvenementManager(Sims sims) {
        this.sims = sims;
    }

    public Sims getSims() {
        return sims;
    }

    public Sim getSimChoisi() {
        return simChoisi;
    }
    
    /**
     * Applique l'impact d'un événement sur les besoins d'un Sim.
     * Selon la barre concernée, la valeur correspondante est diminuée
     * d'un nombre de points égal à l'impact de l'événement.
     *
     * @param sim Sim concerné
     * @param event événement appliqué
     */
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
    	derniersEvenements.add(sim.getNom()+"-> "+event.toString());
    }
    

     /**
     * Génère et applique un événement aléatoire.
     * Un Sim est choisi au hasard, puis un événement aléatoire est appliqué.
     */
    public void generatorRandomEvent() { 
    	Sim sim = getRandomSim(); 
    	Evenement event = getRandomEvent(); 
    	updateBesoinsEvenement(sim, event); 
    }

    /**
     * Sélectionne un Sim aléatoire parmi les Sims.
     *
     * @return Sim choisi aléatoirement
     */
	private Sim getRandomSim() {
		Random r = new Random() ;
		Sim[] liste = sims.getSims().values().toArray(new Sim[0]) ; 
		return liste[r.nextInt(liste.length)] ;
	}

    /**
     * Sélectionne un événement aléatoire dans le EvenementsRepository.
     *
     * @return événement choisi aléatoirement
     */
	private Evenement getRandomEvent() {
		Random r = new Random() ;
		Evenement[] liste = eventRep.getValues().values().toArray(new Evenement[0]) ;
		return liste[r.nextInt(liste.length)] ;
	}

}