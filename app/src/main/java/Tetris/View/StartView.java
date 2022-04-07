package Tetris.View;

import Tetris.Model.StartBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class StartView extends JFrame implements Observer {
    private StartBoard model = new StartBoard();
    private JButton basicModeStartBtn = new JButton(model.getFocusStartBtnImage());
    private JButton itemModeStartBtn = new JButton(model.getDefaultStartItemBtnImage());
    private JButton settingBtn = new JButton(model.getDefaultSettingBtnImage());
    private JButton scoreboardBtn = new JButton(model.getDefaultScoreboardBtnImage());
    private JButton exitBtn = new JButton(model.getDefaultExitBtnImage());
    private JLabel keyDescribeLabel;

    public StartView(int width, int height){
        super("SeoulTech SE Tetris");
        setSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
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

        Dimension frameSize = getSize();
        int startX = (frameSize.width - model.getButtonWidth()) / 2;
        int startY = frameSize.height / 3;
        int space = model.getButtonHeight() + ((frameSize.height * 2 / 3) - (model.getButtonHeight() * model.getButtonCount())) / model.getButtonCount();

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(model.getButtonCount(), 1));

        basicModeStartBtn.setBounds(startX, startY, model.getButtonWidth(), model.getButtonHeight());
        basicModeStartBtn.setBorderPainted(false);
        basicModeStartBtn.setContentAreaFilled(false);
        basicModeStartBtn.setFocusPainted(false);
        basicModeStartBtn.setOpaque(false);
        buttons.add(basicModeStartBtn);

        itemModeStartBtn.setBounds(startX, basicModeStartBtn.getY() + space, model.getButtonWidth(), model.getButtonHeight());
        itemModeStartBtn.setBorderPainted(false);
        itemModeStartBtn.setContentAreaFilled(false);
        itemModeStartBtn.setFocusPainted(false);
        itemModeStartBtn.setOpaque(false);
        buttons.add(itemModeStartBtn);

        settingBtn.setBounds(startX, itemModeStartBtn.getY() + space, model.getButtonWidth(), model.getButtonHeight());
        settingBtn.setBorderPainted(false);
        settingBtn.setContentAreaFilled(false);
        settingBtn.setFocusPainted(false);
        settingBtn.setOpaque(false);
        buttons.add(settingBtn);

        scoreboardBtn.setBounds(startX, settingBtn.getY() + space, model.getButtonWidth(), model.getButtonHeight());
        scoreboardBtn.setBorderPainted(false);
        scoreboardBtn.setContentAreaFilled(false);
        scoreboardBtn.setFocusPainted(false);
        scoreboardBtn.setOpaque(false);
        buttons.add(scoreboardBtn);

        exitBtn.setBounds(startX, scoreboardBtn.getY() + space, model.getButtonWidth(), model.getButtonHeight());
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false);
        exitBtn.setOpaque(false);
        buttons.add(exitBtn);

        buttons.setBounds(0, height / 3, width, height * 2/ 3);
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
        keyDescribeLabel = new JLabel();
        keyDescribeLabel.setForeground(Color.WHITE);
        keyDescribeLabel.setText("current Keysetting");
        keyDescribeLabel.setFont(keyDescribeLabel.getFont().deriveFont(14.0f));
        keyDescribeLabel.setVisible(true);
        keyDescribeLabel.setPreferredSize(new Dimension(260, 35));
        keyDescribeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc[1].gridx = 0;
        gbc[1].gridy = 1;
        gbc[1].gridheight = 2;
        gbc[1].fill = GridBagConstraints.BOTH;
        add(keyDescribeLabel, gbc[1]);

        gbc[2].gridx = 0;
        gbc[2].gridy = 3;
        gbc[2].gridheight = 2;
        gbc[2].fill = GridBagConstraints.BOTH;
        add(buttons, gbc[2]);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFocusable(true);
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
