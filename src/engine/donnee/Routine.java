package engine.donnee;

import java.util.HashMap;
import java.util.Map.Entry;

import engine.map.Batiment;
/**
 *Classe qui creer ou en sauve la routine du sim.
 *REPOSITORY???
 * 
 * @author LOUKILI
 * 
 */
public class Routine {
	
	private HashMap<Integer,Batiment> routine=new HashMap<>();
	
	public void ajouterActivite(int HeureDebut, Batiment lieuActivite) {
		this.routine.put(HeureDebut, lieuActivite);
	}

	public HashMap<Integer,Batiment> getRoutine() {
		return routine;
	}

	public void setRoutine(HashMap<Integer,Batiment> routine) {
		this.routine = routine;
	}
	/*méthode utilisée dans MobileSimMenager pour vérifier s'il y a 
	 * une nouvelle tâche qui commence en vérifiant si cette clé est dans les clés 
	 * de la HashMap
	 */
	public boolean containsKey(int heureCourante) {
		return this.routine.containsKey(heureCourante);
	}

	public Batiment get(int heureCourante) {
		return this.routine.get(heureCourante);
	}

	@Override
	public String toString() {
		StringBuilder resultat=new StringBuilder();
		for (int heure=0; heure<24; heure++) {
			if(routine.containsKey(heure)) {
	           resultat.append("\nHeure de debut: "+heure+"\tBatiment: "+routine.get(heure).getNomBatiment());
			}
		}
		return resultat.toString();
	}
	
	
}
