package Tetris.Controller.scoreboard;

import Tetris.Controller.home.StartViewController;

import Tetris.Model.home.StartBoard;
import Tetris.Model.scoreboard.ScoreBoard;

import Tetris.View.home.StartView;
import Tetris.View.scoreboard.ScoreBoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ScoreBoardController implements KeyListener, ActionListener {
    private final ScoreBoard model;
    private final ScoreBoardView scoreBoardView;

    public ScoreBoardController(ScoreBoard model, ScoreBoardView view) {
        this.model = model;
        this.scoreBoardView = view;
        this.scoreBoardView.setActionListener(this);
    }

    public void navigatePreviousView(){
        StartBoard field = new StartBoard();
        StartView startView = new StartView(scoreBoardView.getLocation().x, scoreBoardView.getLocation().y);
        StartViewController controller = new StartViewController(field, startView);
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
        if(e.getSource().toString().contains("button_back_focused.png")){
            navigatePreviousView();
        }
    }
}
