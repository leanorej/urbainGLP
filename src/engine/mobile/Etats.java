package engine.mobile;

import java.util.ArrayList;

public class Etats {
    
    private ArrayList<String> etats = new ArrayList<>() ;

    public void ajouterEtat(String etat) {
		if(!etats.contains(etat))
			this.etats.add(etat) ;
	}

	public ArrayList<String> getEtats(){
		return etats ;
	}

	public void supprimerEtat(String etat) {
		if(etats.contains(etat))
			this.etats.remove(etat) ;
	}

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
