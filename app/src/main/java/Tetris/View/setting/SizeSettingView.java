package Tetris.View.setting;

import Tetris.Model.setting.SizeSettingBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class SizeSettingView extends JFrame implements Observer {
    private final SizeSettingBoard model = new SizeSettingBoard();
    private final JButton smallSizeBtn = new JButton(model.getFocusSize300600BtnImage());
    private final JButton defaultSizeBtn = new JButton(model.getFocusSize350700BtnImage());
    private final JButton bigSizeBtn = new JButton(model.getFocusSize400800BtnImage());
    private final JButton backBtn = new JButton(model.getFocusBackBtnImage());

    public SizeSettingView(int x, int y){
        super("SeoulTech SE Tetris");

        setSize(new Dimension(model.getScreenWidth(), model.getScreenHeight()));
        setPreferredSize(new Dimension(model.getScreenWidth(), model.getScreenHeight()));
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

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 1));

        smallSizeBtn.setBorderPainted(false);
        smallSizeBtn.setContentAreaFilled(false);
        smallSizeBtn.setFocusPainted(false);
        smallSizeBtn.setOpaque(false);
        buttons.add(smallSizeBtn);

        defaultSizeBtn.setBorderPainted(false);
        defaultSizeBtn.setContentAreaFilled(false);
        defaultSizeBtn.setFocusPainted(false);
        defaultSizeBtn.setOpaque(false);
        buttons.add(defaultSizeBtn);

        bigSizeBtn.setBorderPainted(false);
        bigSizeBtn.setContentAreaFilled(false);
        bigSizeBtn.setFocusPainted(false);
        bigSizeBtn.setOpaque(false);
        buttons.add(bigSizeBtn);

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        buttons.add(backBtn);

        buttons.setOpaque(false);
        add(buttons, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setVisible(true);
        requestFocus();
        pack();
    }

    @Override
    public void paint(Graphics g) {
        int indicator = model.getIndicator();
        if(indicator == 0){
            smallSizeBtn.setIcon(model.getFocusSize300600BtnImage());
        } else {
            smallSizeBtn.setIcon(model.getSize300600BtnImage());
        }
        if(indicator == 1){
            defaultSizeBtn.setIcon(model.getFocusSize350700BtnImage());
        } else {
            defaultSizeBtn.setIcon(model.getSize350700BtnImage());
        }
        if(indicator == 2){
            bigSizeBtn.setIcon(model.getFocusSize400800BtnImage());
        } else {
            bigSizeBtn.setIcon(model.getSize400800BtnImage());
        }
        if(indicator == 3){
            backBtn.setIcon(model.getFocusBackBtnImage());
        } else {
            backBtn.setIcon(model.getBackBtnImage());
        }
        pack();
        super.paint(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
