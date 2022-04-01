package Tetris;


import Tetris.Controller.GameViewController;
import Tetris.Model.GameState;
import Tetris.Model.GameBoard;
import Tetris.View.GameView;

public class Tetris {
    public static void main(String[] args) {
        GameState gameState = new GameState();
        GameBoard field = new GameBoard(gameState,20, 10);

        GameView view = new GameView(field);

        GameViewController controller = new GameViewController(field);
        view.addKeyListener(controller);
        field.addObserver(view);
        gameState.addObserver(view);

        view.setSize(400, 800);
        view.setVisible(true);
    }
}