package application.config;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class FlappyBird extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Integer boardwidth = 360;
	private Integer boardheigth = 640;

	public FlappyBird() {
		setPreferredSize(new Dimension(boardwidth, boardheigth));
		setBackground(Color.blue);
	}
}
