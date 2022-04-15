package Tetris.Controller;

import Tetris.Model.GameBoard;
import Tetris.Model.GameState;
import Tetris.Model.StartBoard;
import Tetris.Model.SettingBoard;
import Tetris.Util.JsonReader;
import Tetris.View.GameView;
import Tetris.View.StartView;
import Tetris.View.SettingView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartViewController implements KeyListener {
    private StartBoard model;
    private StartView startView;

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
        view.setVisible(true);
    }
    public void navigateSettingView(){
        SettingBoard field = new SettingBoard();
        SettingView view = new SettingView(startView.getLocation().x, startView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        view.setVisible(true);
    }
    public void navigateScoreboardView(){
        // default implementation ignored.
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
                        startView.dispose();
                        break;
                    case 1:
                        navigateGameView("item");
                        startView.dispose();
                        break;
                    case 2:
                        navigateSettingView();
                        startView.dispose();
                        break;
                    case 3:
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