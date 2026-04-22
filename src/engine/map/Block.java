package engine.map;

/**
 * C'est l'ellemet qui compose la mappe avec un longeur et un largeur
 * 
 *  @author LOUKILI
 */
public class Block {
	private int line;
	private int column;

	/**
	 * Constructeur du bloc
	 * @param line longuer du bloc
	 * @param column largeur du bloc
	 */
	public Block(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}

}
