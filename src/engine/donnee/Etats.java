package engine.donnee;

import java.util.ArrayList;
/**
 * Représente un ensemble d'états.
 * @author CHOUAREF
 * @version 1.0
 */
public class Etats {
    
    private ArrayList<String> etats = new ArrayList<>() ;

    /**
     * Ajoute un état à la liste des etats.
     *
     * @param etat l'état à ajouter ;
     */
    public void ajouterEtat(String etat) {
		if(!etats.contains(etat))
			this.etats.add(etat) ;
	}
    /**
    * Retourne la liste de tous les états.
    *
    * @return une ArrayList contenant les états.
    */
	public ArrayList<String> getEtats(){
		return etats ;
	}
	
	/**
     * Supprime un état de la liste.
     *
     * @param etat l'état à supprimer 
     */
	public void supprimerEtat(String etat) {
		if(etats.contains(etat))
			this.etats.remove(etat) ;
	}

	/**
     * Verifie si la liste des états est vide
     *
     * @return true si la liste est vide false sinon
     */
	public boolean estVide(){
		return etats.isEmpty() ;
	}

	public String toString(){
		String e ="";
		for (String etat : etats)
			e += "  " + etat ;
		return e;
	}


}
