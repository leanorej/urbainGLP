package engine.process;

import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.donnee.Evenement;
import engine.map.Batiment;
import engine.map.Ville;

import engine.donnee.RelationAmicale;


import java.util.ArrayList;

import chrono.Chronometer;

import org.apache.log4j.Logger;

/**
 * Gère les besoin des sim, précisément elle gère les trois barre de vie du {@link engine.mobile.Sim},
 * gère la mort et la déclenche et aussi si le sim mort est ami avec quelqu'un il declanche le deuil.
 * 
 * @author CHOUAREF
 * @author JEAN
 * @author LOUKILI
 */
public class EtatSimManager {
    private Sims sims;
    private Chronometer chrono;
    private Ville ville;
    private RelationsRepository relationsRep = RelationsRepository.getInstance();
    
    /** Gère l'application des modificateurs de besoins liés aux événements (ici le deuil pert de 30d de mentale) */
    private EvenementManager evenementManager;
    /**
     * Pour éviter un double traitement dans la même minute.
     */
    private int derniereMinuteTraitee = -1;
    /**
     * Pour éviter un double traitement dans la même heure.
     */
    private int derniereHeureTraiteePhysique = -1;
    /**
     * Pour éviter un double traitement dans la même heure.
     */
    private int derniereHeureTraiteeFaim = -1;
  
	private static final Logger logger = Logger.getLogger(EtatSimManager.class);

	
	/**
    * Sims à supprimer à la fin du cycle de mise à jour en cours.
    * Une suppression directe pendant l'itération provoquerait un crash.
    */
    ArrayList<Sim> simsToKill = new ArrayList<>();
    
    /**
     * Constructeur de EtatSimManager
     * 
     * @param sims les sims a gèrer
     * @param chrono le chronometre du jeux
     * @param ville	la ville pour prendre les batiments
     * @param evenementManager le menager utilisé pour appliquer les evenents generer par cette classe dans ce cas le deuil
     */
    public EtatSimManager(Sims sims, Chronometer chrono, Ville ville,EvenementManager evenementManager) {
        this.sims = sims;
        this.chrono = chrono;
        this.ville = ville;
        this.evenementManager = evenementManager;
    }
    /**
     * Retourne la classe {@link engine.mobile.Sims}
     * @return Retourne tout les sims
     */
    public Sims getSims() {
        return sims;
    }
    /**
     * Met à jour les besoins (faim, physique, mental) de chaque Sim actif, une fois par minute de simulation.
     *  Faim: décrémente une fois par heure pour tous les Sims.
     *  Elle s'incrémente toutes les 20 minutes lorsqu'un Sim se trouve à la boulangerie} ou au mall.
     *  Physique et Mental: mis à jour une fois par heure selon des règles propres à chaque lieu
     *       et à chaque Sim (ex. : Francis perd plus vite du physique à l'usine,
     *       la piscine booste tout le monde, l'école diminue les stats de Julie, etc.).
     */
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
                if (b.contient(sim.getPositionCourante())) {
                    lieuActuel = b.getNomBatiment().toLowerCase().trim();
                    break;
                }
            }
            //Physique et Mental (1 fois par heure)
            if (heureCourante != derniereHeureTraiteePhysique) {
            	String nomSim = sim.getNom();
            	switch (lieuActuel) {
            	case "maison1":
            		//Maison de Francis, son mental augmente
            		if(nomSim.equals("Francis")) {
            			sim.getMental().increment();
            		}
            		break;
            	case "maison2":
            		//maison de Geneviève donc ça augmente son physique 
            		if(nomSim.equals("Genevieve")) {
            			sim.getPhysique().increment();
            		}
            		break;
            	case "maison3":
            		//Maison d'Emmanuel qui augmente son physique
            		if(nomSim.equals("Emmanuel")) {
            			sim.getPhysique().increment();
            		}
            		break;
            	case "immeuble":
            		//immeuble avec Francis, Julie et Marie, leur physique augmente
            		if(nomSim.equals("Francis") || nomSim.equals("Julie") || nomSim.equals("Marie")) {
            			sim.getPhysique().increment();
            		}
            		break;
            	case "boulangerie":
            		//lieu de travail de Maris donc physique et mental diminuent
            		if(nomSim.equals("Marie")) {
            			sim.getMental().decrement();
            			sim.getPhysique().decrement();
            		}
            		break;
            	case "usine":
            		//Francis perd plus vite physiquement que mentalement à l'usine
            		if(nomSim.equals("Francis")) {
            			sim.getPhysique().decrement();
            			sim.getPhysique().decrement();
            			sim.getMental().decrement();
            		}
            		break;
            	case "piscine":
            	    //augmente physique et mental de tout le monde
                    sim.getPhysique().increment();
                    sim.getMental().increment();
                    break;
            	case "salle":
            	    //Physique et mental de Francis et Marie augmentent
                    if (nomSim.equals("Francis") || nomSim.equals("Marie")) {
                        sim.getPhysique().increment();
                        sim.getMental().increment();
                    }
                    break;
            	case "ecole":
            	    //Physique et mental baissent pour Julie
                    if (nomSim.equals("Julie")) {
                        sim.getPhysique().decrement();
                        sim.getMental().decrement();
                    }
                    break;
            	case "lycee":
            	    //physique et mental baissent pour Benjamin 
                    if (nomSim.equals("Benjamin")) {
                        sim.getPhysique().decrement();
                        sim.getMental().decrement();
                    }
                    break;
            	case "parc":
            	    //baisse physique et mental de Emmanuel
                    if (nomSim.equals("Emmanuel")) {
                        sim.getPhysique().decrement();
                        sim.getMental().decrement();
                    }
                    break;
            	case "mall":
            		//augmente mental de tout le monde
                    sim.getMental().increment();
                    break;
            	case "cinema":
            	    //augmente mental de tout le monde
                    sim.getMental().increment();
                    break;
            	case "hopital":
                    //"enlève malade" qui est à implémenter quand l'état malade sera défini
                    //pour l'instant on remonte le physique si en dessous de 25
                    if (sim.getPhysique().getValue() < 25) {
                        sim.getPhysique().increment();
                        sim.getPhysique().increment();
                    }
                    break;
                default:
                	//quand on est pas dans un batiment
                	break;
            	}
            	
            
            }

            //incrémentation faim (si boulangerie/mall)
            if (minuteCourante == 0 || minuteCourante == 20 || minuteCourante == 40) {
                if (lieuActuel.equals("boulangerie") || lieuActuel.equals("mall")) {
                    sim.getFaim().increment();
                }
            }
            
        }
    }
    /**
     * Gère les états de tout les sims ejoute un etat faim, triste ou fatigue en fonction si une des trois barre de vie descend en desous de 25%
     */
    public void updateEtats(){
        if (chrono == null) return;
    
        int heureCourante = chrono.getHour().getValue();
        int minuteCourante = chrono.getMinute().getValue();
        //updatedBesoin une fois par minute
        if (minuteCourante == derniereMinuteTraitee) return;

        for (Sim sim : sims.getSims().values()) {

            if(sim.getFaim().getValue() < 25){
                sim.getEtats().ajouterEtat("faim");
            }
            else{
                sim.getEtats().supprimerEtat("faim");
            }

            if(sim.getMental().getValue() < 25){
                sim.getEtats().ajouterEtat("triste");
            }
            else{
                sim.getEtats().supprimerEtat("triste");
            }

            if(sim.getPhysique().getValue() < 25){
                sim.getEtats().ajouterEtat("fatigue");
            }
            else{
                sim.getEtats().supprimerEtat("fatigue");
            }
            
            //Si un de trois etat est activé alors on commance a compter les 4 dernier jour avant la mort
	        if(sim.getEtats().getEtats().contains("faim") || sim.getEtats().getEtats().contains("triste") || sim.getEtats().getEtats().contains("fatigue")){
			     updateEtatCritique(sim);
			 } else {
			     sim.getJourRestant().reset();
			     sim.setDernierJourCritique(-1);
			 }
        }
        
        derniereMinuteTraitee = minuteCourante;
        if (heureCourante != derniereHeureTraiteePhysique) derniereHeureTraiteePhysique = heureCourante;
        if (minuteCourante == 0) derniereHeureTraiteeFaim = heureCourante;
        
        for( Sim sim :simsToKill){
        	if(sim.isVivant()) {
        	sims.remove(sim);
        	sim.setVivant(false);}
        }
    }
    
   /**
    * Retourne la moyenne de l'etata global de chaque Sim
    * @return la moyenne  du ScoreGlobal pour tous les Sims actifs
    */
    public int updateEtatGeneral(){
        int etatG = 0 ;
        int nbSim = 0 ;
        for (Sim sim : sims.getSims().values()) {
            etatG+=sim.getScoreGlobal() ;
            nbSim++ ;
        }   
        return etatG/nbSim ;
    }
    
    /**
     * Déclenche un événement de deuil pour tous les Sims qui étaient amis avec le Sim décédé.
     *
     * @param simMort le Sim qui vient de mourir
     */
    public void declencherDeuil(Sim simMort) {
        for (RelationAmicale r : relationsRep.getRelationsDe(simMort.getNom())) {
            if (r.sontAmis()) {
                String nomAmi = r.getNomSim1().equals(simMort.getNom())
                              ? r.getNomSim2() : r.getNomSim1();
                Sim ami = sims.getSim(nomAmi);
                if (ami != null) {
                    evenementManager.updateBesoinsEvenement(ami, new Evenement("Est en deuil pour " + simMort.getNom(), "mental", 30));
                }
            }
        }
    }
    
    /**
     * Avance le compte à (rebours de mort) pour un Sim se trouvant dans un état critique.
     *
     * @param sim le Sim dont l'état critique doit être évalué
     */
    public void updateEtatCritique(Sim sim) {
        int jourCourant = chrono.getDay();
        if (jourCourant != sim.getDernierJourCritique()) {
            sim.setDernierJourCritique(jourCourant);
            
            //System.out.println("[DEBUG] " + sim.getNom() + " decrement -> " + (sim.getJourRestant().getValue()-1));
            sim.getJourRestant().decrement();

            if (sim.getJourRestant().getValue() == 0) {
                //sims.remove(sim);//on suprime pas directement sinon risque de crash car boucle en cours
                simsToKill.add(sim);
                logger.info("Sim "+ sim.getNom() +" est mort.");
                declencherDeuil(sim);
                DerniersEvenementsRepository.getInstance().add(sim.getNom() + " est mort(e).");  
            }
        }
    }
}