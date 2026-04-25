package engine.map;

import java.util.HashMap;

/**
 * Représente la map du jeu sous forme d'une grille de {@link engine.map.Block}.
 *
 * @author tianxiao.liu@cyu.fr
 * @author LOUKILI
 */
public class Map {
	private Block[][] blocks;

	private int lineCount;
	private int columnCount;

	
	private HashMap<Block, Batiment> batiments = new HashMap<Block, Batiment>();


	/**
	 * Contruction de la map.
	 * 
	 * @param lineCount nombre de ligne donc longeur
	 * @param columnCount nombre de colonne donc largeur
	 */
	public Map(int lineCount, int columnCount) {
		init(lineCount, columnCount);

		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
			}
		}
	}
	
    /**
     * Initialise les dimensions de la carte et alloue la grille de blocs.
     * Cette méthode est appelée par par le constructeur.
     * 
     * @param lineCount   le nombre de lignes
     * @param columnCount le nombre de colonnes
     */
	private void init(int lineCount, int columnCount) {
		this.lineCount = lineCount;	
		this.columnCount = columnCount;
		blocks = new Block[lineCount][columnCount];
		
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public int getLineCount() {
		return lineCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public Block getBlock(int line, int column) {
		return blocks[line][column];
	}

	public HashMap<Block, Batiment> getBatiments() {
		return batiments;
	}

	public void setBatiments(HashMap<Block, Batiment> batiments) {
		this.batiments = batiments;
	}



}
