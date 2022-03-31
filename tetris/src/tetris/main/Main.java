package tetris.main;

import javax.swing.JFrame;

import tetris.panel.KeySetPanel;
import tetris.panel.StartPanel;

public class Main {
	

	public static void main(String[] args) {

		StartPanel start = new StartPanel();
		
		//ScorePanel score = new ScorePanel();
		
		//KeySetPanel key = new KeySetPanel();
		JFrame frame = new JFrame();
		
		frame.add(start);
		frame.setVisible(true);
		frame.setSize(400, 800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
