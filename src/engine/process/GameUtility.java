package engine.process;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This utility class contains unit functions used by the train simulation.
 * 
 * @see 
 * @author Loukili
 */
public class GameUtility {
	
	/**
	 * Reads a image from an image file.
	 * 
	 * @param filePath the path (from "src") of the image file
	 * @return the read file
	 */
	public static Image readImage(String filePath) {
		try {
			return ImageIO.read(new File(filePath));
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
			return null;
		}
	}
	private static int getRandom(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}
	
	//ca peut etre utile unitTime() et windowRefreshTime() dans simulation simulationutility de train
}
