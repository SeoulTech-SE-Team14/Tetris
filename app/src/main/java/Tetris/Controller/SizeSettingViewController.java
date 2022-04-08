package Tetris.Controller;

import Tetris.Model.SizeSettingBoard;
import Tetris.View.SizeSettingView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SizeSettingViewController implements KeyListener {
    private SizeSettingBoard model;
    private SizeSettingView sizeSettingView;

    public SizeSettingViewController(SizeSettingBoard model, SizeSettingView view) {
        this.model = model;
        this.sizeSettingView = view;
    }

    public void navigateGameView(){
//        GameState gameState = new GameState();
//        GameBoard field = new GameBoard(gameState,20, 10);
//        GameView view = new GameView(sizeSettingView.getLocation().x, sizeSettingView.getLocation().y, field);
//        GameViewController controller = new GameViewController(field);
//        view.addKeyListener(controller);
//        field.addObserver(view);
//        gameState.addObserver(view);
//        view.setVisible(true);
    }
    
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
                switch (indicator){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
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
