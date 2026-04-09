package engine.process;

import engine.mobile.Sim;
import engine.mobile.Sims;
import engine.donnee.Evenement;
import engine.map.Batiment;
import engine.map.Ville;
import engine.process.EvenementsRepository;
import engine.process.RelationsRepository;
import engine.donnee.RelationAmicale;


import java.util.Random;

import chrono.Chronometer;


public class EtatSimManager {
    private Sims sims;
    private Sim simChoisi = null;
    private Chronometer chrono;
    private Ville ville;
    private EvenementsRepository eventRep = EvenementsRepository.getInstance();;
    private RelationsRepository relationsRep = RelationsRepository.getInstance();


    private int derniereMinuteTraitee = -1;
    private int derniereHeureTraiteePhysique = -1;
    private int derniereHeureTraiteeFaim = -1;

    public EtatSimManager(Sims sims, Chronometer chrono, Ville ville) {
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
            	case "imm":
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

        }
        
        
        derniereMinuteTraitee = minuteCourante;
        if (heureCourante != derniereHeureTraiteePhysique) derniereHeureTraiteePhysique = heureCourante;
        if (minuteCourante == 0) derniereHeureTraiteeFaim = heureCourante;
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

    }
    
    
    
    public void updateRelations() {
        if (chrono == null) return;

        int heureCourante = chrono.getHour().getValue();

        if (heureCourante == derniereHeureTraiteePhysique) return;

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


    public void declencherDeuil(Sim simMort) {
        for (RelationAmicale r : relationsRep.getRelationsDe(simMort.getNom())) {
            if (r.sontAmis()) {
                String nomAmi = r.getNomSim1().equals(simMort.getNom())
                              ? r.getNomSim2() : r.getNomSim1();
                Sim ami = sims.getSim(nomAmi);
                if (ami != null) {
                    ami.getEtats().ajouterEtat("deuil");
                }
            }
        }
    }
    
    public void generatorRandomEvent() {
		getRandomSim().getEtats().ajouterEtat(getRandomEvent().getNom());
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