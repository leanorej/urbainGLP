package engine.donnee;

public class Evenement {
	private String nom ;
    private String barreImpactee ;
    private int impact ;

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
