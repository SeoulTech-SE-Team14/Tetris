package Tetris.View.setting;

import Tetris.Model.setting.KeySettingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class KeySettingView extends JFrame implements Observer {
    private final KeySettingModel model = new KeySettingModel();
    private final JButton rightKeyBtn = new JButton(model.getRightKeyImage());
    private final JButton leftKeyBtn = new JButton(model.getLeftKeyImage());
    private final JButton downKeyBtn = new JButton(model.getDownKeyImage());
    private final JButton fallKeyBtn = new JButton(model.getFallKeyImage());
    private final JButton rotateKeyBtn = new JButton(model.getRotateKeyImage());
    private final JButton pauseKeyBtn = new JButton(model.getPauseKeyImage());
    private final JButton storeBtn = new JButton(model.getStoreBtnImage());
    private final JButton backBtn = new JButton(model.getBackBtnImage());

    public final JButton rightKeyInputBtn = new JButton(model.getRightKeyImage());
    public final JButton leftKeyInputBtn = new JButton(model.getLeftKeyImage());
    public final JButton downKeyInputBtn = new JButton(model.getDownKeyImage());
    public final JButton fallKeyInputBtn = new JButton(model.getFallKeyImage());
    public final JButton rotateKeyInputBtn = new JButton(model.getRotateKeyImage());
    public final JButton pauseKeyInputBtn = new JButton(model.getPauseKeyImage());

    private final int width = model.getScreenWidth();
    private final int height = model.getScreenHeight();

    public KeySettingView(int x, int y) {
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
        buttons.setOpaque(false);
        buttons.setLayout(new GridLayout(8, 2));

        rightKeyBtn.setBorderPainted(false);
        rightKeyBtn.setContentAreaFilled(false);
        rightKeyBtn.setFocusPainted(false);
        rightKeyBtn.setOpaque(false);
        buttons.add(rightKeyBtn);

        addButton(buttons, rightKeyBtn,"right");
        addButton(buttons, rightKeyInputBtn, "rightInput");
        addButton(buttons, leftKeyBtn,"left");
        addButton(buttons, leftKeyInputBtn, "leftInput");
        addButton(buttons, downKeyBtn,"down");
        addButton(buttons, downKeyInputBtn, "downInput");
        addButton(buttons, fallKeyBtn,"fall");
        addButton(buttons, fallKeyInputBtn, "fallInput");
        addButton(buttons, rotateKeyBtn,"rotate");
        addButton(buttons, rotateKeyInputBtn, "rotateInput");
        addButton(buttons, pauseKeyBtn,"pause");
        addButton(buttons, pauseKeyInputBtn, "pauseInput");

        addButton(buttons, storeBtn, "store");
        addButton(buttons,backBtn, "back2");
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        background.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttons, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public void addButton(JPanel parent, JButton component, String name) {
        component.setBorderPainted(false);
        component.setContentAreaFilled(false);
        component.setFocusPainted(false);
        component.setFocusable(true);
        component.setOpaque(false);
        component.setName(name);
        parent.add(component);
    }
    public void setKeyListener(KeyListener listener){
        rightKeyInputBtn.addKeyListener(listener);
        leftKeyInputBtn.addKeyListener(listener);
        downKeyInputBtn.addKeyListener(listener);
        fallKeyInputBtn.addKeyListener(listener);
        rotateKeyInputBtn.addKeyListener(listener);
        pauseKeyInputBtn.addKeyListener(listener);
    }
    public void setActionListener(ActionListener listener) {
        storeBtn.addActionListener(listener);
        backBtn.addActionListener(listener);
    }
    public void drawIndicatorBtn(){
        rightKeyInputBtn.setIcon(model.getRightKeyInputImage());
        leftKeyInputBtn.setIcon(model.getLeftKeyInputImage());
        downKeyInputBtn.setIcon(model.getDownKeyInputImage());
        fallKeyInputBtn.setIcon(model.getFallKeyInputImage());
        rotateKeyInputBtn.setIcon(model.getRotateKeyInputImage());
        pauseKeyInputBtn.setIcon(model.getPauseKeyInputImage());
    }

    @Override
    public void paint(Graphics g) {
        drawIndicatorBtn();
        pack();
        super.paint(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
