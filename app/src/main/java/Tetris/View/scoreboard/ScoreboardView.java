package Tetris.View.scoreboard;

import Tetris.Model.scoreboard.ScoreModel;
import Tetris.Model.scoreboard.ScoreboardModel;
import Tetris.Util.GameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ScoreboardView extends JFrame{
    private static final String BLANK_STRING = " ";
    private static final String ZERO_WIDTH_SPACE = "​";

    private final JButton backBtn;
    private final JLabel basicModeTitle;
    private final JLabel itemModeTitle;

    private final ScoreboardModel model = new ScoreboardModel();
    private final int width = model.getScreenWidth();
    private final int height = model.getScreenHeight();

    private JPanel basicModeScoreboard;
    private JPanel itemModeScoreboard;
    private List<ScoreModel> basicModeScoreList;
    private List<ScoreModel> itemModeScoreList;

    public ScoreboardView(int x, int y){
        super("SeoulTech SE Tetris");

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
        gbc.insets = new Insets(7, 0, 0, 0);
        setContentPane(background);

        // 일반 모드 순위표 타이틀
        basicModeTitle = new JLabel(model.getBasicModeTitleImage());
        basicModeTitle.setPreferredSize(new Dimension(260, 35));
        add(basicModeTitle, gbc);

        // 일반 모드 순위표 보드
        basicModeScoreboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                g.drawImage(model.getScoreboardBackground().getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        basicModeScoreboard.setLayout(new GridLayout(10, 1));
        basicModeScoreboard.setPreferredSize(new Dimension(260, 190));
        basicModeScoreList = model.getScoreboard(GameType.BASIC_MODE);
        for(int i = 0; i < basicModeScoreList.size(); i++){
            ScoreModel scoreInfo = basicModeScoreList.get(i);
            String str = Integer.toString(i + 1) + "등" + BLANK_STRING + scoreInfo.getName() +
                    BLANK_STRING + scoreInfo.getScore() + "점" + BLANK_STRING + scoreInfo.getDifficulty() + "모드";
            JLabel label = new JLabel(str, SwingConstants.CENTER);
            label.setFont(new Font("Courier", Font.PLAIN, 12));
            basicModeScoreboard.add(label);
        }
        add(basicModeScoreboard,gbc);

        // 아이템 모드 타이틀
        itemModeTitle = new JLabel(model.getItemModeTitleImage());
        itemModeTitle.setPreferredSize(new Dimension(260, 35));
        add(itemModeTitle, gbc);

        // 아이템 모드 순위표 보드
        itemModeScoreboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                g.drawImage(model.getScoreboardBackground().getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };

        itemModeScoreboard.setLayout(new GridLayout(10, 1));
        itemModeScoreboard.setPreferredSize(new Dimension(260, 190));

        itemModeScoreList = model.getScoreboard(GameType.ITEM_MODE);
        for(int i = 0; i < itemModeScoreList.size(); i++){
            ScoreModel scoreInfo = itemModeScoreList.get(i);
            String str = Integer.toString(i + 1) + "등" + BLANK_STRING + scoreInfo.getName() + BLANK_STRING + scoreInfo.getScore() +"점";
            JLabel label = new JLabel(str, SwingConstants.CENTER);
            label.setFont(new Font("Courier", Font.PLAIN, 12));
            itemModeScoreboard.add(label);
        }
        add(itemModeScoreboard,gbc);

        backBtn = new JButton(model.getBackBtnImage());
        backBtn.setOpaque(true);
        backBtn.setPreferredSize(new Dimension(260, 45));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        add(backBtn,gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
        requestFocus();
    }

    public void setActionListener(ActionListener listener){
        backBtn.addActionListener(listener);
    }
}
