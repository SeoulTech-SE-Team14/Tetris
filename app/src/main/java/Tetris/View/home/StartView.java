package Tetris.View.home;

import Tetris.Model.home.StartBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class StartView extends JFrame implements Observer {
    private final StartBoard model = new StartBoard();
    private final JButton basicModeStartBtn = new JButton(model.getFocusStartBtnImage());
    private final JButton itemModeStartBtn = new JButton(model.getDefaultStartItemBtnImage());
    private final JButton settingBtn = new JButton(model.getDefaultSettingBtnImage());
    private final JButton scoreboardBtn = new JButton(model.getDefaultScoreboardBtnImage());
    private final JButton exitBtn = new JButton(model.getDefaultExitBtnImage());

    public StartView(int x, int y){
        super("SeoulTech SE Tetris");

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
        setContentPane(background);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(model.getButtonCount(), 1));

        basicModeStartBtn.setBorderPainted(false);
        basicModeStartBtn.setContentAreaFilled(false);
        basicModeStartBtn.setFocusPainted(false);
        basicModeStartBtn.setOpaque(false);
        buttons.add(basicModeStartBtn);

        itemModeStartBtn.setBorderPainted(false);
        itemModeStartBtn.setContentAreaFilled(false);
        itemModeStartBtn.setFocusPainted(false);
        itemModeStartBtn.setOpaque(false);
        buttons.add(itemModeStartBtn);

        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);
        settingBtn.setFocusPainted(false);
        settingBtn.setOpaque(false);
        buttons.add(settingBtn);

        scoreboardBtn.setBorderPainted(false);
        scoreboardBtn.setContentAreaFilled(false);
        scoreboardBtn.setFocusPainted(false);
        scoreboardBtn.setOpaque(false);
        buttons.add(scoreboardBtn);

        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false);
        exitBtn.setOpaque(false);
        buttons.add(exitBtn);

        buttons.setOpaque(false);

        GridBagConstraints[] gbc = new GridBagConstraints[3];
        GridBagLayout gbl = new GridBagLayout();
        for(int i = 0; i < 3; i++){ gbc[i] = new GridBagConstraints(); }
        background.setLayout(gbl);

        // 빈 공백
        JPanel blank = new JPanel();
        blank.setPreferredSize(new Dimension(width, height / 4));
        blank.setOpaque(false);

        gbc[0].gridx = 0;
        gbc[0].gridy = 0;
        add(blank, gbc[0]);

        //사용가능한 키 표시
        JLabel keyDescribeLabel = new JLabel();
        keyDescribeLabel.setForeground(Color.WHITE);
        keyDescribeLabel.setText("현재 키세팅 보여주는 부분.");
        keyDescribeLabel.setFont(new Font("Courier", Font.PLAIN, model.getFontSize()));
        keyDescribeLabel.setVisible(true);
        keyDescribeLabel.setPreferredSize(new Dimension(260, 35));
        keyDescribeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc[1].gridx = 0;
        gbc[1].gridy = 1;
        gbc[1].gridheight = 2;
        add(keyDescribeLabel, gbc[1]);

        gbc[2].gridx = 0;
        gbc[2].gridy = 3;
        gbc[2].gridheight = 2;
        add(buttons, gbc[2]);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        requestFocus();
        pack();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        int indicator = model.getIndicator();
        if(indicator == 0){
            basicModeStartBtn.setIcon(model.getFocusStartBtnImage());
        } else {
            basicModeStartBtn.setIcon(model.getDefaultStartBtnImage());
        }
        if(indicator == 1){
            itemModeStartBtn.setIcon(model.getFocusStartItemBtnImage());
        } else {
            itemModeStartBtn.setIcon(model.getDefaultStartItemBtnImage());
        }
        if(indicator == 2){
            settingBtn.setIcon(model.getFocusSettingBtnImage());
        } else {
            settingBtn.setIcon(model.getDefaultSettingBtnImage());
        }
        if(indicator == 3){
            scoreboardBtn.setIcon(model.getFocusScoreboardBtnImage());
        } else {
            scoreboardBtn.setIcon(model.getDefaultScoreboardBtnImage());
        }
        if(indicator == 4){
            exitBtn.setIcon(model.getFocusExitBtnImage());
        } else {
            exitBtn.setIcon(model.getDefaultExitBtnImage());
        }
        pack();
        super.paint(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
