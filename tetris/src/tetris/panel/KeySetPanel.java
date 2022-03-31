package tetris.panel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KeySetPanel extends JPanel{
	
	private ImageIcon btn01 = new ImageIcon("img/button/button_back.png");
	private ImageIcon btn02 = new ImageIcon("img/button/button_keep.png");
	
	private JLabel label1, label2, label3, label4, label5, label6;
	private JTextField text1, text2, text3, text4, text5, text6;
	private JButton btnBack, btnKeep;
	
	public KeySetPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		;
		
		// 제목
		JLabel title = new JLabel("KEYBOARD SETTING");
		title.setFont(title.getFont().deriveFont(20.0f)); 
		title.setBounds( 90, 10, 230, 50);
		add(title);
		
		// 오른쪽키 
		label1 = new JLabel("right");
		label1.setFont(label1.getFont().deriveFont(15.0f)); 
		label1.setBounds( 90, 70, 50, 50);
		add(label1);
		text1 = new JTextField("d",5);
		text1.setBounds( 250, 80, 40, 30);
		add(text1);
		
		// 왼쪽키
		label2 = new JLabel("left");
		label2.setFont(label2.getFont().deriveFont(15.0f)); 
		label2.setBounds( 90, 120, 50, 50);
		add(label2);
		text2 = new JTextField("a",5);
		text2.setBounds( 250, 130, 40, 30);
		add(text2);
		
		// 아래쪽키
		label3 = new JLabel("down");
		label3.setFont(label3.getFont().deriveFont(15.0f)); 
		label3.setBounds( 90, 170, 50, 50);
		add(label3);
		text3 = new JTextField("s",5);
		text3.setBounds( 250, 180, 40, 30);
		add(text3);
		
		// 회전키
		label4 = new JLabel("turn");
		label4.setFont(label4.getFont().deriveFont(15.0f)); 
		label4.setBounds( 90, 220, 50, 50);
		add(label4);
		text4 = new JTextField("r",5);
		text4.setBounds( 250, 230, 40, 30);
		add(text4);
		
		// 한번에 내리기
		label5 = new JLabel("floor");
		label5.setFont(label5.getFont().deriveFont(15.0f)); 
		label5.setBounds( 90, 270, 50, 50);
		add(label5);
		text5 = new JTextField("f",5);
		text5.setBounds( 250, 280, 40, 30);
		add(text5);
		
		// 일시정지
		label6 = new JLabel("pause");
		label6.setFont(label6.getFont().deriveFont(15.0f)); 
		label6.setBounds( 90, 320, 50, 50);
		add(label6);
		text6 = new JTextField("q",5);
		text6.setBounds( 250, 330, 40, 30);
		add(text6);
		
		// 뒤로가기버튼
		btnBack = new JButton(btn01);
		btnBack.setName("BackBtn");
		btnBack.setBounds( 100, 550, 180, 60);
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		add(btnBack);
		
		// 저장버튼
		btnKeep = new JButton(btn02);
		btnKeep.setName("KeepBtn");
		btnKeep.setBounds( 145, 400, 90, 40);
		btnKeep.setBorderPainted(false);
		btnKeep.setContentAreaFilled(false);
		btnKeep.setFocusPainted(false);
		add(btnKeep);
	}
}
