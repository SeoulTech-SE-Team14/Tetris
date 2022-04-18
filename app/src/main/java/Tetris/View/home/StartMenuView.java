package Tetris.View.home;

import Tetris.Model.home.StartMenuModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class StartMenuView extends JFrame implements Observer {
    private final StartMenuModel model = new StartMenuModel();
    private final JButton basicModeStartBtn = new JButton(model.getFocusStartBtnImage());
    private final JButton itemModeStartBtn = new JButton(model.getDefaultStartItemBtnImage());
    private final JButton settingBtn = new JButton(model.getDefaultSettingBtnImage());
    private final JButton scoreboardBtn = new JButton(model.getDefaultScoreboardBtnImage());
    private final JButton exitBtn = new JButton(model.getDefaultExitBtnImage());

    private final int width = model.getScreenWidth();
    private final int height = model.getScreenHeight();

    public StartMenuView(int x, int y){
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
        gbc.insets = new Insets(10, 0, 0, 0);
        setContentPane(background);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
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

        // 빈 공백
        JLabel titleImage = new JLabel(model.getTitleImage());
        add(titleImage, gbc);

        //사용가능한 키 표시
        JLabel keyDescribeLabel = new JLabel();
        keyDescribeLabel.setForeground(Color.WHITE);
        keyDescribeLabel.setText("현재 키세팅 보여주는 부분.");
        keyDescribeLabel.setFont(new Font("Courier", Font.PLAIN, model.getFontSize()));
        keyDescribeLabel.setVisible(true);
        keyDescribeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(keyDescribeLabel, gbc);
        add(buttons, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
        requestFocus();
        pack();
    }
    public void setActionListener(ActionListener listener){
        basicModeStartBtn.addActionListener(listener);
        itemModeStartBtn.addActionListener(listener);
        settingBtn.addActionListener(listener);
        scoreboardBtn.addActionListener(listener);
        exitBtn.addActionListener(listener);

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
