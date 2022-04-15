package Tetris.View;

import Tetris.Model.SizeSettingBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class SizeSettingView extends JFrame implements Observer {
    private final SizeSettingBoard model = new SizeSettingBoard();
    private final JButton smallSizeBtn = new JButton(model.getFocusSize300600BtnImage());
    private final JButton defaultSizeBtn = new JButton(model.getFocusSize350700BtnImage());
    private final JButton bigSizeBtn = new JButton(model.getFocusSize400800BtnImage());
    private final JButton backBtn = new JButton(model.getFocusDefaultBackBtnImage());

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
