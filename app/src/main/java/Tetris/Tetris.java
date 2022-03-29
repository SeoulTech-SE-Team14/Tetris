package Tetris;


import Tetris.Controller.GameViewController;
import Tetris.Manager.GameManager;
import Tetris.Model.Game;
import Tetris.View.GameBoard;

public class Tetris {
    public static void main(String[] args) {
        Game model = new Game(20, 10);
        GameBoard board = new GameBoard(model);
        GameManager manager = new GameManager();

        GameViewController controller = new GameViewController(model, manager);
        board.addKeyListener(controller);
        model.addObserver(board);
        manager.addObserver(board);

        board.setSize(400, 800);
        board.setVisible(true);
    }
}