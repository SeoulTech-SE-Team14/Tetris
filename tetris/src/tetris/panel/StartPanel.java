package tetris.panel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	
	// ��ư �̹���������
	private ImageIcon btn01 = new ImageIcon("img/button/button_game-start1.png");
	private ImageIcon btn02 = new ImageIcon("img/button/button_setting1.png");
	private ImageIcon btn03 = new ImageIcon("img/button/button_score-board1.png");
	private ImageIcon btn04 = new ImageIcon("img/button/button_exit.png");
	
	private JLabel title;
	private JButton btnStart, btnSet, btnScore, btnExit;
	
	
	
	public StartPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		
		// ����
		title = new JLabel("TETRIS");
		title.setFont(title.getFont().deriveFont(20.0f)); 
		title.setBounds( 110, 10, 100, 50);
		add(title);
		
		// ���۹�ư
		btnStart = new JButton(btn01);
		btnStart.setName("StartBtn");
		btnStart.setBounds( 55, 80, 180, 60);
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setFocusPainted(false);
		add(btnStart);
		btnStart.requestFocus(true); 
		
		// ������ư
		btnSet = new JButton(btn02);
		btnSet.setName("SettingBtn");
		btnSet.setBounds( 55, 140, 180, 60);
		btnSet.setBorderPainted(false);
		btnSet.setContentAreaFilled(false);
		btnSet.setFocusPainted(false);
		add(btnSet);
		
		// ���ھ��ư
		btnScore = new JButton(btn03);
		btnScore.setName("ScoreBtn");
		btnScore.setBounds( 55, 200, 180, 60);
		btnScore.setBorderPainted(false);
		btnScore.setContentAreaFilled(false);
		btnScore.setFocusPainted(false);
		add(btnScore);
		
		// ���������ư
		btnExit = new JButton(btn04);
		btnExit.setName("ExitBtn");
		btnExit.setBounds( 200, 600, 70, 40);
		btnExit.setBorderPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setFocusPainted(false);
		add(btnExit);
		
	} 
	

}
