package engine.donnee;
/**
 *Représente une relation entre deux {@link engine.mobile.Sim}
 *Compte le nombre d'heures passées ensemble dans le même bâtiment
 *et gère l'évolution vers une amitié à partir de 20 heures passé 
 *dans le meme batiment il deviennet amis
 * 
 *@author JEAN
 *@version 1.0
 */
public class RelationAmicale {

	private String nomSim1;
	private String nomSim2;
    private int heuresPasseesEnsemble;
    private boolean amis;

    /**
     *Crée une relation amicale entre deux Sims.
     *Le compteur d'heures est initialisé à -1 et l'amitié 
     *est false car la relation vient de commencer.
     *
     *@param nomSim1 nom du premier Sim
     *@param nomSim2 nom du second Sim
     */
    public RelationAmicale(String nomSim1, String nomSim2) {
    	this.nomSim1 = nomSim1;
        this.nomSim2 = nomSim2;
        this.heuresPasseesEnsemble = -1;
        this.amis = false;
    }
    
    /**
     *Verifie si un Sim participe à cette relation.
     *
     *@param nom est le nom du Sim à vérifier
     *@return true si ce Sim est l'un des deux membres de la relation
     */
    public boolean implique(String nom) {
        return nomSim1.equals(nom) || nomSim2.equals(nom);
    }

    /**
     *Verifie si les duex sim sont dans cette relation.
     *
     *@param nom1 nom du premier Sim
     *@param nom2 nom du second Sim
     *@return si la relation implique exactement ces deux Sims
     */
    public boolean impliqueLesDeuxSims(String nom1, String nom2) {
        return (nomSim1.equals(nom1) && nomSim2.equals(nom2)) || (nomSim1.equals(nom2) && nomSim2.equals(nom1));
    }

    /**
     *Incrémente d'une heure le temps passé ensemble.
     *
     *Si les Sims ne sont pas amis, chaque appel augmente le compteur.
     *Si le le temps passé atteint 20 heures, l'amitié est déclenchée
     *et le compteur repart à 1. Une fois amis, le compteur continue à incrémenter.
     */
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
