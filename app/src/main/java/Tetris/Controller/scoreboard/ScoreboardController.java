package Tetris.Controller.scoreboard;

import Tetris.Controller.home.StartMenuViewController;

import Tetris.Model.home.StartMenuModel;
import Tetris.Model.scoreboard.ScoreboardModel;
import Tetris.View.home.StartMenuView;
import Tetris.View.scoreboard.ScoreboardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ScoreboardController implements KeyListener, ActionListener {
    private final ScoreboardView scoreBoardView;

    public ScoreboardController(ScoreboardView view) {
        this.scoreBoardView = view;
        this.scoreBoardView.setActionListener(this);
    }

    public void navigatePreviousView(){
        StartMenuModel field = new StartMenuModel();
        StartMenuView startView = new StartMenuView(scoreBoardView.getLocation().x, scoreBoardView.getLocation().y);
        StartMenuViewController controller = new StartMenuViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
        scoreBoardView.dispose();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                navigatePreviousView();
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // default implementation ignored
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().toString().contains("button_back")){
            navigatePreviousView();
        }
    }
}
