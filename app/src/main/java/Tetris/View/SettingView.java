package Tetris.View;

import Tetris.Model.SettingBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class SettingView extends JFrame implements Observer {
    private SettingBoard model = new SettingBoard();
    private JButton settingSizeBtn = new JButton(model.getFocusSettingSizeBtnImage());
    private JButton colorBlindnessBtn = new JButton(model.getFocusColorblindnessBtnImage());
    private JButton resetSettingBtn = new JButton(model.getFocusResetSettingBtnImage());
    private JButton resetScoreboardBtn = new JButton(model.getFocusResetScoreboardBtnImage());
    private JButton settingKeyBtn = new JButton(model.getFocusSettingKeyBtnImage());
    private JButton backBtn = new JButton(model.getFocusDefaultBackBtnImage());

    // 현재 설정 가져와서 바꾸어야함.
    private int height = 700;
    private int width = 350;

    public SettingView(int x, int y){
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
        setContentPane(background);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(6, 1));

        settingSizeBtn.setBorderPainted(false);
        settingSizeBtn.setContentAreaFilled(false);
        settingSizeBtn.setFocusPainted(false);
        settingSizeBtn.setOpaque(false);
        buttons.add(settingSizeBtn);

        colorBlindnessBtn.setBorderPainted(false);
        colorBlindnessBtn.setContentAreaFilled(false);
        colorBlindnessBtn.setFocusPainted(false);
        colorBlindnessBtn.setOpaque(false);
        buttons.add(colorBlindnessBtn);

        resetSettingBtn.setBorderPainted(false);
        resetSettingBtn.setContentAreaFilled(false);
        resetSettingBtn.setFocusPainted(false);
        resetSettingBtn.setOpaque(false);
        buttons.add(resetSettingBtn);

        resetScoreboardBtn.setBorderPainted(false);
        resetScoreboardBtn.setContentAreaFilled(false);
        resetScoreboardBtn.setFocusPainted(false);
        resetScoreboardBtn.setOpaque(false);
        buttons.add(resetScoreboardBtn);

        settingKeyBtn.setBorderPainted(false);
        settingKeyBtn.setContentAreaFilled(false);
        settingKeyBtn.setFocusPainted(false);
        settingKeyBtn.setOpaque(false);
        buttons.add(settingKeyBtn);

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        buttons.add(backBtn);

        buttons.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        background.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttons, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        pack();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        int indicator = model.getIndicator();
        if(indicator == 0){
            settingSizeBtn.setIcon(model.getFocusSettingSizeBtnImage());
        } else {
            settingSizeBtn.setIcon(model.getSettingSizeBtnImage());
        }
        if(indicator == 1){
            colorBlindnessBtn.setIcon(model.getFocusColorblindnessBtnImage());
        } else {
            colorBlindnessBtn.setIcon(model.getColorblindnessBtnImage());
        }
        if(indicator == 2){
            resetSettingBtn.setIcon(model.getFocusResetSettingBtnImage());
        } else {
            resetSettingBtn.setIcon(model.getResetSettingBtnImage());
        }
        if(indicator == 3){
            resetScoreboardBtn.setIcon(model.getFocusResetScoreboardBtnImage());
        } else {
            resetScoreboardBtn.setIcon(model.getResetScoreboardBtnImage());
        }
        if(indicator == 4){
            settingKeyBtn.setIcon(model.getFocusSettingKeyBtnImage());
        } else {
            settingKeyBtn.setIcon(model.getSettingKeyBtnImagee());
        }
        if(indicator == 5){
            backBtn.setIcon(model.getFocusDefaultBackBtnImage());
        } else {
            backBtn.setIcon(model.getDefaultBackBtnImage());
        }
        pack();
        super.paint(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}