package Tetris;

import Tetris.Controller.*;
import Tetris.Model.*;
import Tetris.View.*;

public class Tetris {
    public static void main(String[] args) {
        StartBoard field = new StartBoard();
        // 현재 초기 창 위치 임의로 저장해두었음.
        StartView startView = new StartView(500, 0);
        StartViewController controller = new StartViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
        startView.setVisible(true);
    }
}