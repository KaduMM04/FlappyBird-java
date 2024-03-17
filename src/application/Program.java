package application;

import javax.swing.JFrame;

import application.config.FlappyBird;

public class Program {

	public static void main(String[] args) throws Exception{
		
		int boardWidth = 360;
		int boardHeigth = 640;
		
		JFrame frame = new JFrame("Flappy Bird");
		//frame.setVisible(true);
		frame.setSize(boardWidth, boardHeigth);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FlappyBird flappybird = new FlappyBird();
		frame.add(flappybird);
		frame.pack();
		frame.setVisible(true);
	}
}
