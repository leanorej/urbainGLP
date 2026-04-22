package engine.donnee;

import java.util.HashMap;

import engine.map.Batiment;

/**
 *Représente la routine d'un {@link engine.mobile.Sim}.
 *Cette classe permet d'associer une heure de début à un bâtiment
 *correspondant à l'activitéde cette heure.
 * 
 * @author LOUKILI
 */
public class Routine {

    private HashMap<Integer, Batiment> routine = new HashMap<>();

    /**
     *Ajoute une activité à une heure donnée.
     *@param heureDebut   heure de début de l'activité (0 à 23)
     *@param lieuActivite bâtiment où se déroule l'activité
     */
    public void ajouterActivite(int heureDebut, Batiment lieuActivite) {
        this.routine.put(heureDebut, lieuActivite);
    }

    /**
     *Retourne la routine complète.
     *
     *@return la map contenant les activités
     */
    public HashMap<Integer, Batiment> getRoutine() {
        return routine;
    }

    /**
     *Remplace la routine actuelle par une nouvelle.
     *
     *@param routine nouvelle routine à affecter
     */
    public void setRoutine(HashMap<Integer, Batiment> routine) {
        this.routine = routine;
    }

    /**
     *Vérifie si une activité commence à l'heure donnée.
     *
     *Utilisée notamment dans {@link engine.process.MobileSimManager} pour détecter
     *le démarrage d'une nouvelle tâche.
     *
     *@param heureCourante heure à vérifier
     *@return true si une activité existe à cette heure,
     *         sinon false
     */
    public boolean containsKey(int heureCourante) {
        return this.routine.containsKey(heureCourante);
    }

    /**
     *Retourne le bâtiment associé à une heure donnée.
     *
     *@param heureCourante heure recherchée
     *@return le bâtiment associé a l'heure
     */
    public Batiment get(int heureCourante) {
        return this.routine.get(heureCourante);
    }

    /**
     *Les activités sont affichées heure par heure sur 24 heures.
     *
     *@return chaîne décrivant la routine
     */
    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder();

        for (int heure = 0; heure < 24; heure++) {
            if (routine.containsKey(heure)) {
                resultat.append("\nHeure de debut: ").append(heure).append("\tBatiment: ").append(routine.get(heure).getNomBatiment());
            }
        }

        return resultat.toString();
    }
}