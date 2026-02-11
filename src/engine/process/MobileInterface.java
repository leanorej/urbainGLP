package engine.process;
/**
 * @author JEAN
 */
import java.util.List;

import engine.map.Block;
import engine.mobile.Sim;


public interface MobileInterface {

	void add(Sim sim);

	void nextRound();

	List<Sim> getSims();

}