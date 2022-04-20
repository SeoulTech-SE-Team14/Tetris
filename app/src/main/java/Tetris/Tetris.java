package Tetris;

import Tetris.Controller.home.StartMenuViewController;
import Tetris.Model.home.StartMenuModel;
import Tetris.View.home.StartMenuView;

public class Tetris {
    public static void main(String[] args) {
        StartMenuModel field = new StartMenuModel();
        StartMenuView startView = new StartMenuView(500, 0);
        StartMenuViewController controller = new StartMenuViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
    }
}