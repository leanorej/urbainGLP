package engine.map;

import java.util.HashMap;



/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr Loukili
 *
 */
public class Map {
	private Block[][] blocks;
	private int lineCount;
	private int columnCount;
	//J'hésite entre utiliser la position (c'est-à-dire le Bllock associé) ou le nom du bâtiment comme key.
	private HashMap<Block, Batiment> batiments = new HashMap<Block, Batiment>();



	public Map(int lineCount, int columnCount) {
		init(lineCount, columnCount);

		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
			}
		}
	}
	
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
