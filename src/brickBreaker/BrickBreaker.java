package brickBreaker;

import javax.swing.*;

public class BrickBreaker {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setTitle("Brick breaker");
		f.setSize(700, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		
		GamePannel game = new GamePannel();
		f.add(game);

	}

}
