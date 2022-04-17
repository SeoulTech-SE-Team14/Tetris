package Tetris.View.setting;

import Tetris.Model.setting.KeySettingBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class KeySettingView extends JFrame implements Observer {
    private final KeySettingBoard model = new KeySettingBoard();
    private final JButton rightKeyBtn = new JButton(model.getRightKeyImage());
    private final JButton leftKeyBtn = new JButton(model.getLeftKeyImage());
    private final JButton downKeyBtn = new JButton(model.getDownKeyImage());
    private final JButton fallKeyBtn = new JButton(model.getFallKeyImage());
    private final JButton rotateKeyBtn = new JButton(model.getRotateKeyImage());
    private final JButton pauseKeyBtn = new JButton(model.getPauseKeyImage());
    private final JButton storeBtn = new JButton(model.getStoreBtnImage());
    private final JButton backBtn = new JButton(model.getBackBtnImage());

    public final JTextField rightKeyField = new JTextField();
    public final JTextField leftKeyField = new JTextField();
    public final JTextField downKeyField = new JTextField();
    public final JTextField fallKeyField = new JTextField();
    public final JTextField rotateKeyField = new JTextField();
    public final JTextField pauseKeyField = new JTextField();

    public KeySettingView(int x, int y) {
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
        buttons.setOpaque(false);
        buttons.setLayout(new GridLayout(8, 2));

        rightKeyBtn.setBorderPainted(false);
        rightKeyBtn.setContentAreaFilled(false);
        rightKeyBtn.setFocusPainted(false);
        rightKeyBtn.setOpaque(false);
        buttons.add(rightKeyBtn);

        rightKeyField.setOpaque(false);
        rightKeyField.setText(model.getKeyMap().get("right").toString());
        buttons.add(rightKeyField);

        leftKeyBtn.setBorderPainted(false);
        leftKeyBtn.setContentAreaFilled(false);
        leftKeyBtn.setFocusPainted(false);
        leftKeyBtn.setOpaque(false);
        buttons.add(leftKeyBtn);

        leftKeyField.setOpaque(false);
        leftKeyField.setText(model.getKeyMap().get("left").toString());
        buttons.add(leftKeyField);

        downKeyBtn.setBorderPainted(false);
        downKeyBtn.setContentAreaFilled(false);
        downKeyBtn.setFocusPainted(false);
        downKeyBtn.setOpaque(false);
        buttons.add(downKeyBtn);

        downKeyField.setOpaque(false);
        downKeyField.setText(model.getKeyMap().get("down").toString());
        buttons.add(downKeyField);

        fallKeyBtn.setBorderPainted(false);
        fallKeyBtn.setContentAreaFilled(false);
        fallKeyBtn.setFocusPainted(false);
        fallKeyBtn.setOpaque(false);
        buttons.add(fallKeyBtn);

        fallKeyField.setOpaque(false);
        fallKeyField.setText(model.getKeyMap().get("fall").toString());
        buttons.add(fallKeyField);

        rotateKeyBtn.setBorderPainted(false);
        rotateKeyBtn.setContentAreaFilled(false);
        rotateKeyBtn.setFocusPainted(false);
        rotateKeyBtn.setOpaque(false);
        buttons.add(rotateKeyBtn);

        rotateKeyField.setOpaque(false);
        rotateKeyField.setText(model.getKeyMap().get("rotate").toString());
        buttons.add(rotateKeyField);

        pauseKeyBtn.setBorderPainted(false);
        pauseKeyBtn.setContentAreaFilled(false);
        pauseKeyBtn.setFocusPainted(false);
        pauseKeyBtn.setOpaque(false);
        buttons.add(pauseKeyBtn);

        pauseKeyField.setOpaque(false);
        pauseKeyField.setText(model.getKeyMap().get("pause").toString());
        buttons.add(pauseKeyField);

        storeBtn.setBorderPainted(false);
        storeBtn.setContentAreaFilled(false);
        storeBtn.setFocusPainted(false);
        storeBtn.setOpaque(false);

        storeBtn.setName("storeBtn");
        buttons.add(storeBtn);

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(false);

        backBtn.setName("backBtn");
        buttons.add(backBtn);

        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        background.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttons, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
        requestFocus();
        pack();
    }
    public void setActionListener(ActionListener listener){
        storeBtn.addActionListener(listener);
        backBtn.addActionListener(listener);
    }
    @Override
    public void paint(Graphics g) {
        int indicator = model.getIndicator();
        if(indicator == 0){
            storeBtn.setIcon(model.getFocusStoreBtnImage());
        } else {
            storeBtn.setIcon(model.getStoreBtnImage());
        }
        if(indicator == 1){
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
