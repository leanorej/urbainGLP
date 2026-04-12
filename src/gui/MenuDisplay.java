package gui;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class MenuDisplay extends JPanel{

    private static final long serialVersionUID = 1L;

    private PaintStrategy paintStrategy = new PaintStrategy();


    public MenuDisplay() {
		
	}

    @Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		
		Graphics2D g2 = (Graphics2D) g;
		
		paintStrategy.paintMenu(g2);

	}
	
	


}
