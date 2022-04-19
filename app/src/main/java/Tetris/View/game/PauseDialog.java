package Tetris.View.game;

import Tetris.Model.game.PauseDialogModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class PauseDialog extends JDialog implements Observer {
    private final PauseDialogModel model = new PauseDialogModel();
    private final JButton resumeBtn = new JButton(model.getResumeBtnImage());
    private final JButton restartBtn = new JButton(model.getRestartBtnImage());
    private final JButton backMenuBtn = new JButton(model.getBackMenuBtnImage());

    private final int width = model.getWidth();
    private final int height = model.getHeight();

    public PauseDialog(JFrame frame, int x, int y) {
        super(frame, "일시정지", true);

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

        resumeBtn.setBorderPainted(false);
        resumeBtn.setContentAreaFilled(false);
        resumeBtn.setFocusPainted(false);
        resumeBtn.setOpaque(false);
        buttons.add(resumeBtn);

        restartBtn.setBorderPainted(false);
        restartBtn.setContentAreaFilled(false);
        restartBtn.setFocusPainted(false);
        restartBtn.setOpaque(false);
        buttons.add(restartBtn);

        backMenuBtn.setBorderPainted(false);
        backMenuBtn.setContentAreaFilled(false);
        backMenuBtn.setFocusPainted(false);
        backMenuBtn.setOpaque(false);
        buttons.add(backMenuBtn);

        add(buttons, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        requestFocus();
    }
    public void setActionListener(ActionListener listener){
        resumeBtn.addActionListener(listener);
        restartBtn.addActionListener(listener);
        backMenuBtn.addActionListener(listener);
    }
    @Override
    public void paint(Graphics g) {
        int indicator = model.getIndicator();
        if(indicator == 0){
            resumeBtn.setIcon(model.getFocusResumeBtnImage());
        } else {
            resumeBtn.setIcon(model.getResumeBtnImage());
        }
        if(indicator == 1){
            restartBtn.setIcon(model.getFocusRestartBtnImage());
        } else {
            restartBtn.setIcon(model.getRestartBtnImage());
        }
        if(indicator == 2){
            backMenuBtn.setIcon(model.getFocusBackMenuBtnImage());
        } else {
            backMenuBtn.setIcon(model.getBackMenuBtnImage());
        }
        pack();
        super.paint(g);
    }
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
