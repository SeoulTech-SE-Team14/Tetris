package Tetris.Controller.home;

import Tetris.Controller.game.GameViewController;
import Tetris.Controller.scoreboard.ScoreBoardController;
import Tetris.Controller.setting.SettingViewController;

import Tetris.Model.game.GameBoard;
import Tetris.Model.game.GameState;
import Tetris.Model.home.StartBoard;
import Tetris.Model.scoreboard.ScoreBoard;
import Tetris.Model.setting.SettingBoard;

import Tetris.View.game.GameView;
import Tetris.View.home.StartView;
import Tetris.View.scoreboard.ScoreBoardView;
import Tetris.View.setting.SettingView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartViewController implements KeyListener {
    private final StartBoard model;
    private final StartView startView;

    public StartViewController(StartBoard model, StartView view) {
        this.model = model;
        this.startView = view;
    }

    public void navigateGameView(String gameMode){
        GameState gameState = new GameState(gameMode);
        GameBoard field = new GameBoard(gameState,20, 10);
        GameView view = new GameView(startView.getLocation().x, startView.getLocation().y, field);
        GameViewController controller = new GameViewController(field);
        view.addKeyListener(controller);
        field.addObserver(view);
        gameState.addObserver(view);
        startView.dispose();
    }
    public void navigateSettingView(){
        SettingBoard field = new SettingBoard();
        SettingView view = new SettingView(startView.getLocation().x, startView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        startView.dispose();
    }
    public void navigateScoreboardView(){
        ScoreBoard field = new ScoreBoard();
        ScoreBoardView view = new ScoreBoardView(startView.getLocation().x, startView.getLocation().y);
        ScoreBoardController controller = new ScoreBoardController(field, view);
        view.addKeyListener(controller);
        startView.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored.
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int indicator = model.getIndicator();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                indicator++;
                if(indicator == model.getButtonCount()) indicator = 0;
                model.setIndicator(indicator);
                break;
            case KeyEvent.VK_UP:
                indicator--;
                if(indicator == -1) indicator = model.getButtonCount() - 1;
                model.setIndicator(indicator);
                break;
            case KeyEvent.VK_ENTER:
                switch (indicator){
                    case 0:
                        navigateGameView("normal" );
                        break;
                    case 1:
                        navigateGameView("item");
                        break;
                    case 2:
                        navigateSettingView();
                        break;
                    case 3:
                        navigateScoreboardView();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // default implementation ignored.
    }
}