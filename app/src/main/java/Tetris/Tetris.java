package Tetris;

import Tetris.Controller.home.StartMenuViewController;
import Tetris.Model.home.StartMenuModel;
import Tetris.View.home.StartMenuView;

public class Tetris {
    public static void main(String[] args) {
        StartMenuModel field = new StartMenuModel();
        // 현재 초기 창 위치 임의로 저장해두었음.
        StartMenuView startView = new StartMenuView(500, 0);
        StartMenuViewController controller = new StartMenuViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
    }
}