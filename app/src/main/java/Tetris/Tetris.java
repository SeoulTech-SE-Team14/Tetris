package Tetris;


import Tetris.Controller.StartViewController;
import Tetris.Model.StartBoard;
import Tetris.View.StartView;

public class Tetris {
    public static void main(String[] args) {
        StartBoard field = new StartBoard();
        StartView startView = new StartView(300, 600);
        StartViewController controller = new StartViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
        startView.setVisible(true);
    }
}