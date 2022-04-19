package Tetris.View.game;

import Tetris.Model.game.SelectDialogModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class SelectDialog extends JDialog implements Observer {
    private final SelectDialogModel model = new SelectDialogModel();
    private final JButton easyModeBtn = new JButton(model.getDefaultEasyModeBtnImage());
    private final JButton normalModeBtn = new JButton(model.getDefaultNormalModeBtnImage());
    private final JButton hardModeBtn = new JButton(model.getDefaultHardModeBtnImage());
    private final JButton backBtn = new JButton(model.getDefaultBackBtnImage());

    private final int width = model.getWidth();
    private final int height = model.getHeight();

    public SelectDialog(JFrame frame, int x, int y) {
        super(frame, "난이도선택", true);

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

        JLabel blank = new JLabel(model.getEmptyImage());
        background.add(blank, gbc);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setLayout(new GridLayout(model.getButtonCount(), 1));

        easyModeBtn.setBorderPainted(false);
        easyModeBtn.setContentAreaFilled(false);
        easyModeBtn.setFocusPainted(false);
        easyModeBtn.setOpaque(false);
        buttons.add(easyModeBtn);

        normalModeBtn.setBorderPainted(false);
        normalModeBtn.setContentAreaFilled(false);
        normalModeBtn.setFocusPainted(false);
        normalModeBtn.setOpaque(false);
        buttons.add(normalModeBtn);

        hardModeBtn.setBorderPainted(false);
        hardModeBtn.setContentAreaFilled(false);
        hardModeBtn.setFocusPainted(false);
        hardModeBtn.setOpaque(false);
        buttons.add(hardModeBtn);

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(false);
        buttons.add(backBtn);
        add(buttons, gbc);

        setResizable(false);
        setFocusable(true);
        requestFocus();
    }
    public void setActionListener(ActionListener listener){
        easyModeBtn.addActionListener(listener);
        normalModeBtn.addActionListener(listener);
        hardModeBtn.addActionListener(listener);
        backBtn.addActionListener(listener);
    }
    @Override
    public void paint(Graphics g) {
        int indicator = model.getIndicator();
        if(indicator == 0){
            easyModeBtn.setIcon(model.getFocusEasyModeBtnImage());
        } else {
            easyModeBtn.setIcon(model.getDefaultEasyModeBtnImage());
        }
        if(indicator == 1){
            normalModeBtn.setIcon(model.getFocusNormalModeBtnImage());
        } else {
            normalModeBtn.setIcon(model.getDefaultNormalModeBtnImage());
        }
        if(indicator == 2){
            hardModeBtn.setIcon(model.getFocusHardModeBtnImage());
        } else {
            hardModeBtn.setIcon(model.getDefaultHardModeBtnImage());
        }
        if(indicator == 3){
            backBtn.setIcon(model.getFocusBackBtnImage());
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
