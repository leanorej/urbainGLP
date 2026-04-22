package engine.process;


public class NiveauManager {
    
    private int niveau ;
    private int nbEventJour ;
    private int cptJour ;

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

    public void majNiveau(int nbSimVivant){
        cptJour++ ;
        if (cptJour%3==0 && nbSimVivant==6){
            niveau++ ;
            nbEventJour++ ;
        }
    }

    



}
