package engine.donnee;

public class RelationAmicale {

	private String nomSim1;
	private String nomSim2;
    private int heuresPasseesEnsemble;
    private boolean amis;

    public RelationAmicale(String nomSim1, String nomSim2) {
    	this.nomSim1 = nomSim1;
        this.nomSim2 = nomSim2;
        this.heuresPasseesEnsemble = -1;
        this.amis = false;
    }

    public boolean implique(String nom) {
        return nomSim1.equals(nom) || nomSim2.equals(nom);
    }

    public boolean impliqueLesDeuxSims(String nom1, String nom2) {
        return (nomSim1.equals(nom1) && nomSim2.equals(nom2)) || (nomSim1.equals(nom2) && nomSim2.equals(nom1));
    }

    public void incrementerTemps() {
        if (!amis) {
            heuresPasseesEnsemble++;
            if (heuresPasseesEnsemble >= 20) {
                amis = true;
                heuresPasseesEnsemble = 1;
            }
        } else {
            heuresPasseesEnsemble++;
        }
    }

    public boolean sontAmis() {
    	return amis;
    }
    
    public int getHeuresPasseesEnsemble() {
    	return heuresPasseesEnsemble;
    }
    public String getNomSim1() {
    	return nomSim1;
    }
    public String getNomSim2() {
    	return nomSim2;
    }
}
