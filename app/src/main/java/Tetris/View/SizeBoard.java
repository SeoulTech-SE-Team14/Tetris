package Tetris.View;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SizeBoard extends JPanel{
	
	// 
	private ImageIcon btn01 = new ImageIcon("assets/button_default.png");
	private ImageIcon btn02 = new ImageIcon("assets/button_small.png");
	private ImageIcon btn03 = new ImageIcon("assets/button_wide.png");
	private ImageIcon btn04 = new ImageIcon("assets/button_back.png");
	
	
	public SizeBoard() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		// 타이틀
		JLabel title = new JLabel("DISPLAY SIZE");
		title.setFont(title.getFont().deriveFont(20.0f)); 
		title.setBounds( 140, 10, 200, 50);
		add(title);
		
		// 기본화면크기 버튼
		JButton btnDef = new JButton(btn01);
		btnDef.setName("DefaultBtn");
		btnDef.setBounds( 50, 100, 300, 60);
		add(btnDef);
		btnDef.setBorderPainted(false);
		btnDef.setContentAreaFilled(false);
		btnDef.setFocusPainted(false);
		
		
		// 작은화면 버튼
		JButton btnSmall = new JButton(btn02);
		btnSmall.setName("SmallBtn");
		btnSmall.setBounds( 50, 180, 300, 60);
		add(btnSmall);
		btnSmall.setBorderPainted(false);
		btnSmall.setContentAreaFilled(false);
		btnSmall.setFocusPainted(false);
		
		// 큰화면 버튼
		JButton btnWide = new JButton(btn03);
		btnWide.setName("WideBtn");
		btnWide.setBounds( 50, 260, 300, 60);
		add(btnWide);
		btnWide.setBorderPainted(false);
		btnWide.setContentAreaFilled(false);
		btnWide.setFocusPainted(false);
		
		// 뒤로가기 버튼
		JButton btnBack = new JButton(btn04);
		btnBack.setName("BackBtn");
		btnBack.setBounds( 50, 340, 300, 60);
		add(btnBack);
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
	}
}
