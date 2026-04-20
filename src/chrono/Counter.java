package chrono;

/**
 * This is the basic counter implementation. The counter has a integer value that can be incremented or decremented. 
 * 
 * We can only increment or decrement the value by 1 each time.
 * 
 * @author tliu@u-cergy.fr
 **/
public class Counter {
	private int value;
	private int initialValue;

	public Counter(int value) {
		this.value = value;
		this.initialValue = value;
	}

	public int getValue() {
		return value;
	}

	public void increment() {
		value++;
	}

	public void decrement() {
		value--;
	}

	protected void setValue(int value) {
		this.value = value;
	}
	public void reset() {
	    this.value = initialValue; 
	}

}
