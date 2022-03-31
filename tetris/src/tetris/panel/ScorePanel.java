package tetris.panel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScorePanel extends JPanel{
	
	// ��ư �̹���������
	private ImageIcon btn01 = new ImageIcon("img/button/button_back.png");
	
	// JTable ä���
	private String columnNames[] =
		{ "Rank", "Name", "Score"};

		Object rowData[][] =
		{
				
		 };
	private JLabel title;
	private JButton btnBack;
	private JTable board;
	
	public ScorePanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		// ����
		title = new JLabel("SCORE-BOARD");
		title.setFont(title.getFont().deriveFont(20.0f)); 
		title.setBounds( 110, 10, 200, 50);
		add(title);
		
		// ���ھ��
		board = new JTable(rowData, columnNames);
		board.getColumn("Rank").setPreferredWidth(10);
		board.getColumn("Name").setPreferredWidth(50);
		board.getColumn("Score").setPreferredWidth(150);
		board.getTableHeader().setReorderingAllowed(false);
		board.getTableHeader().setResizingAllowed(false);
		board.setBackground(Color.white);
		board.setFillsViewportHeight(true);
		JScrollPane jScollPane = new JScrollPane(board);
		jScollPane.setBounds( 45, 70, 300, 200);
		add(jScollPane);
		
		// �ڷΰ����ư
		btnBack = new JButton(btn01);
		btnBack.setName("BackBtn");
		btnBack.setBounds( 100, 450, 180, 60);
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		add(btnBack);
		
	}
}
