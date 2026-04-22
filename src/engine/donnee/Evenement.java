package engine.donnee;

/**
 * Représente un événement du jeu qui affecte une barre de vie de un sim.
 * Chaque événement possède un nom, la barre impactée
 * et une valeur d'impact.
 *
 * @author CHOUAREF
 * @version 1.0
 */

public class Evenement {
	private String nom ;
    private String barreImpactee ;
    private int impact ;

    /**
     * Construit un nouvel événement.
     *
     * @param nom			le nom de l'événement
     * @param barreImpactee	le nom de la barre de statistiques affectée
     * @param impact		la valeur de l'impact appliqué à la barre affectée
     */
    public Evenement(String nom, String barreImpactee, int impact){
        this.nom = nom ;
        this.barreImpactee = barreImpactee ;
        this.impact = impact ;
    }

	public String getNom() {
		return nom;
	}

    public String getBarreImpactee() {
		return barreImpactee;
	}

    public int getImpact() {
		return impact;
	}

    @Override
	public String toString() {
		return nom +":  perds "+ impact +" de " + barreImpactee;
	}

}
