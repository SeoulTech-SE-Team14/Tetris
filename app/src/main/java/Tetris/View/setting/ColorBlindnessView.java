package Tetris.View.setting;

import Tetris.Model.setting.ColorBlindnessBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ColorBlindnessView extends JFrame implements Observer{
    private final ColorBlindnessBoard model = new ColorBlindnessBoard();
    private final JButton colorDefaultBtn = new JButton(model.getFocusColorDefaultBtnImage());
    private final JButton colorRedBtn = new JButton(model.getFocusColorRedBtnImage());
    private final JButton colorGreenBtn = new JButton(model.getFocusColorGreenBtnImage());
    private final JButton colorBlueBtn = new JButton(model.getFocusColorBlueBtnImage());
    private final JButton backBtn = new JButton(model.getFocusDefaultBackBtnImage());
    //블록색상 미리보기 이미지 추가해야함

    public ColorBlindnessView(int x, int y){
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

        colorDefaultBtn.setBorderPainted(false);
        colorDefaultBtn.setContentAreaFilled(false);
        colorDefaultBtn.setFocusPainted(false);
        colorDefaultBtn.setOpaque(false);
        buttons.add(colorDefaultBtn);

        colorRedBtn.setBorderPainted(false);
        colorRedBtn.setContentAreaFilled(false);
        colorRedBtn.setFocusPainted(false);
        colorRedBtn.setOpaque(false);
        buttons.add(colorRedBtn);

        colorGreenBtn.setBorderPainted(false);
        colorGreenBtn.setContentAreaFilled(false);
        colorGreenBtn.setFocusPainted(false);
        colorGreenBtn.setOpaque(false);
        buttons.add(colorGreenBtn);

        colorBlueBtn.setBorderPainted(false);
        colorBlueBtn.setContentAreaFilled(false);
        colorBlueBtn.setFocusPainted(false);
        colorBlueBtn.setOpaque(false);
        buttons.add(colorBlueBtn);

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        buttons.add(backBtn);

        //블록색상 미리보기 칸 추가해야함

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
        requestFocus();
        pack();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        int indicator = model.getIndicator();
        if(indicator == 0){
            colorDefaultBtn.setIcon(model.getFocusColorDefaultBtnImage());
        } else {
            colorDefaultBtn.setIcon(model.getColorDefaultBtnImage());
        }
        if(indicator == 1){
            colorRedBtn.setIcon(model.getFocusColorRedBtnImage());
        } else {
            colorRedBtn.setIcon(model.getColorRedBtnImage());
        }
        if(indicator == 2){
            colorGreenBtn.setIcon(model.getFocusColorGreenBtnImage());
        } else {
            colorGreenBtn.setIcon(model.getColorGreenBtnImage());
        }
        if(indicator == 3){
            colorBlueBtn.setIcon(model.getFocusColorBlueBtnImage());
        } else {
            colorBlueBtn.setIcon(model.getColorBlueBtnImage());
        }
        if(indicator == 4){
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

