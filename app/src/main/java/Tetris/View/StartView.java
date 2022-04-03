package Tetris.View;

import Tetris.Model.StartBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class StartView extends JFrame implements Observer {
    private StartBoard model = new StartBoard();
    private JButton gameStartBtn = new JButton(model.getFocusStartBtnImage());
    private JButton settingBtn = new JButton(model.getDefaultSettingBtnImage());
    private JButton scoreboardBtn = new JButton(model.getDefaultScoreboardBtnImage());
    private JButton exitBtn = new JButton(model.getDefaultExitBtnImage());

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
        buttons.setLayout(new GridLayout(4, 1));

        gameStartBtn.setBounds(startX, startY, model.getButtonWidth(), model.getButtonHeight());
        gameStartBtn.setBorderPainted(false);
        gameStartBtn.setContentAreaFilled(false);
        gameStartBtn.setFocusPainted(false);
        gameStartBtn.setOpaque(false);
        buttons.add(gameStartBtn);

        settingBtn.setBounds(startX, gameStartBtn.getY() + space, model.getButtonWidth(), model.getButtonHeight());
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

        buttons.setBounds(0, height / 3, width, height * 2/ 3);
        buttons.setOpaque(false);
        buttons.add(exitBtn);


        GridBagConstraints[] gbc = new GridBagConstraints[2];
        GridBagLayout gbl = new GridBagLayout();
        for(int i = 0; i < 2; i++){ gbc[i] = new GridBagConstraints(); }
        background.setLayout(gbl);

        JPanel blank = new JPanel();
        blank.setPreferredSize(new Dimension(width, height / 5));
        blank.setOpaque(false);
        gbc[0].gridx = 0;
        gbc[0].gridy = 0;
        add(blank, gbc[0]);

        gbc[1].gridx = 0;
        gbc[1].gridy = 1;
        gbc[1].gridheight = 2;
        gbc[1].fill = GridBagConstraints.BOTH;
        add(buttons, gbc[1]);

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
            gameStartBtn.setIcon(model.getFocusStartBtnImage());
        } else {
            gameStartBtn.setIcon(model.getDefaultStartBtnImage());
        }
        if(indicator == 1){
            settingBtn.setIcon(model.getFocusSettingBtnImage());
        } else {
            settingBtn.setIcon(model.getDefaultSettingBtnImage());
        }
        if(indicator == 2){
            scoreboardBtn.setIcon(model.getFocusScoreboardBtnImage());
        } else {
            scoreboardBtn.setIcon(model.getDefaultScoreboardBtnImage());
        }
        if(indicator == 3){
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
