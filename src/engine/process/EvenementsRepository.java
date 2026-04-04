package engine.process;
import engine.donnee.Evenement;



import java.util.HashMap;

public class EvenementsRepository {
	
    
	private HashMap<String, Evenement> evenements = new HashMap<String, Evenement>();

	
	private static EvenementsRepository instance = new EvenementsRepository();

	
	private EvenementsRepository() {

	}

	
    
	public static EvenementsRepository getInstance() {
		return instance;
	}

	
    
	public void register(Evenement event) {
		evenements.put(event.getNom(), event);
	}

	public HashMap<String, Evenement> getValues () {
		return evenements ;
	}


	
    
	public Evenement getEvenement(String nom) {
		if (evenements.containsKey(nom)) {
			return evenements.get(nom);
		}
        else 
            return null ;
	}

}

