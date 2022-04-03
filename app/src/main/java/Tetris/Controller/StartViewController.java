package Tetris.Controller;

import Tetris.Model.GameBoard;
import Tetris.Model.GameState;
import Tetris.Model.StartBoard;
import Tetris.View.GameView;
import Tetris.View.StartView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartViewController implements KeyListener {
    private StartBoard model;
    private StartView view;

    public StartViewController(StartBoard model, StartView view) {
        this.model = model;
        this.view = view;
    }

    public void navigateGameView(){
        GameState gameState = new GameState();
        GameBoard field = new GameBoard(gameState,20, 10);
        GameView view = new GameView(field);
        GameViewController controller = new GameViewController(field);
        view.addKeyListener(controller);
        field.addObserver(view);
        gameState.addObserver(view);
        view.setVisible(true);
    }
    public void navigateSettingView(){ }
    public void navigateScoreboardView(){}
    @Override
    public void keyTyped(KeyEvent e) { }

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
                System.out.println("Enter");
                switch (indicator){
                    case 0:
                        navigateGameView();
                        view.setVisible(false);
                        break;
                    case 1:
                        System.out.println("Setting");
                        break;
                    case 2:
                        System.out.println("ScoreBoard");
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
