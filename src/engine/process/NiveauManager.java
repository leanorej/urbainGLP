package engine.process;

/**
 * Cette classe gère la progression des niveaux en fonction du nombre de
 * sims vivants et du nombre de jours écoulés. Le niveau augmente
 * tous les 3 jours si le nombre de simulants vivants est égal à 6.
 * @author CHOUAREF
 */
public class NiveauManager {
    
    private int niveau ;
    private int nbEventJour ;
    private int cptJour ;
    
    /**
     * Constructeur initialise au niveau 1 et le nombre de evenment par 
     * jour vaux 1 et le compteur de jour zero
     */
    public NiveauManager(){
        this.niveau = 1 ;
        this.nbEventJour = 1 ;
        this.cptJour = 0 ;
    }

    public int getNiveau(){
        return this.niveau ;
    }

    public int getNbEventJour(){
        return this.nbEventJour ;
    }

    public int getCptJour(){
        return this.cptJour ;
    }

    public void majCptJour(){
        this.cptJour++;
    }
    
    /**
     * Met à jour le niveau en fonction du nombre de simulants vivants.
     * Incrémente d'abord le compteur de jours, puis vérifie les conditions
     * de montée de niveau : tous les 3 jours,
     * si exactement 6 simulants sont en vie, le niveau et le nombre
     * d'événements par jour augmentent chacun de 1.
     *
     * @param nbSimVivant le nombre de simulants actuellement vivants
     */
    public void majNiveau(int nbSimVivant){
        cptJour++ ;
        if (cptJour%3==0 && nbSimVivant==6){
            niveau++ ;
            nbEventJour++ ;
        }
    }

    



}
