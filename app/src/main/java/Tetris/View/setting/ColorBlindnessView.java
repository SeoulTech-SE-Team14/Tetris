package Tetris.View.setting;

import Tetris.Model.setting.ColorBlindnessModel;
import Tetris.Util.ColorBlindnessType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ColorBlindnessView extends JFrame implements Observer{
    private final ColorBlindnessModel model = new ColorBlindnessModel();
    private final JButton colorDefaultBtn = new JButton(model.getFocusColorDefaultBtnImage());
    private final JButton colorRedBtn = new JButton(model.getFocusColorRedBtnImage());
    private final JButton colorGreenBtn = new JButton(model.getFocusColorGreenBtnImage());
    private final JButton colorBlueBtn = new JButton(model.getFocusColorBlueBtnImage());
    private final JButton backBtn = new JButton(model.getFocusDefaultBackBtnImage());

    private JLabel blockPreviewImage = new JLabel(model.getPreviewDefaultImage());

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
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
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

        buttons.setOpaque(false);
        add(buttons, gbc);

        //블록색상 미리보기
        add(blockPreviewImage,gbc);

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        add(backBtn, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        requestFocus();
        pack();
        setVisible(true);
    }
    public void setActionListener(ActionListener listener){
        colorDefaultBtn.addActionListener(listener);
        colorBlueBtn.addActionListener(listener);
        colorGreenBtn.addActionListener(listener);
        colorRedBtn.addActionListener(listener);
        backBtn.addActionListener(listener);
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

        String colorMode = model.getColorMode();
        if(colorMode.equals(ColorBlindnessType.NORMAL.getKey())) {
            blockPreviewImage.setIcon(model.getPreviewDefaultImage());
        } else if(colorMode.equals(ColorBlindnessType.PROTAN.getKey())){
            blockPreviewImage.setIcon(model.getPreviewRedImage());
        } else if(colorMode.equals(ColorBlindnessType.DEUTAN.getKey())) {
            blockPreviewImage.setIcon(model.getPreviewGreenImage());
        } else if(colorMode.equals(ColorBlindnessType.TRITAN.getKey())) {
            blockPreviewImage.setIcon(model.getPreviewBlueImage());
        }
        pack();
        super.paint(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

