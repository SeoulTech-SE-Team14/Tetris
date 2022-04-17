package Tetris.View.scoreboard;

import Tetris.Model.scoreboard.ScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ScoreBoardView extends JFrame{
    private static final String BLANK_STRING = " ";
    private static final String ZERO_WIDTH_SPACE = "​";

    private final ScoreBoard model;
    private final JButton backBtn;
    private final JLabel titleImage;
    private JPanel scoreboard;
    private List<JLabel> scoreboardList;

    public ScoreBoardView(int x, int y){
        super("SeoulTech SE Tetris");

        model = new ScoreBoard();
        int width = model.getScreenWidth();
        int height = model.getScreenHeight();

        setSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setLocation(x, y);
        setLayout(null);

        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                g.drawImage(model.getBackgroundImage().getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };

        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        setContentPane(background);

        titleImage = new JLabel(model.getTitleImage());
        titleImage.setPreferredSize(new Dimension(260, 60));
        add(titleImage, gbc);

        scoreboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                g.drawImage(model.getScoreboardBackground().getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        for(int i = 0; i < 10; i++){
            String str = Integer.toString(i + 1) + "등" + BLANK_STRING + "김영균" + BLANK_STRING + "임시 점수" + BLANK_STRING + "임시 모드";
            scoreboard.add(new JLabel(str,SwingConstants.CENTER));
        }
        scoreboard.setLayout(new GridLayout(10, 1));
        scoreboard.setPreferredSize(new Dimension(260, 350));
        add(scoreboard,gbc);

        backBtn = new JButton(model.getFocusBackBtnImage());
        backBtn.setOpaque(true);
        backBtn.setPreferredSize(new Dimension(260, 60));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        add(backBtn,gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
        requestFocus();
        pack();
    }
    public void setActionListener(ActionListener listener){
        backBtn.addActionListener(listener);
    }
}
