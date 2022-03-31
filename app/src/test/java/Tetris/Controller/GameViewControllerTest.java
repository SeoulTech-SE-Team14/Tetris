package Tetris.Controller;

import Tetris.Model.GameState;
import Tetris.Model.GameBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameViewControllerTest {
    GameState state = new GameState();
    GameBoard field = new GameBoard(state, 20, 10);
    GameViewController gvc = new GameViewController(field);
    @Test
    @DisplayName("")
    void getEasyModeRandomBlock() {
        double[] fitness = {1.2, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        int[] blockCount = new int[7];
        for(int i = 0 ; i < 100000000; i++){
            int number =  gvc.rouletteWheelSelection(fitness);
            blockCount[number]++;
        }
        String[] blockName = {"IBlock", "JBlock", "LBlock", "OBlock", "SBlock", "TBlock", "ZBlock"};
        System.out.println("Easy Mode: 1억번 실행");
        for(int i = 0; i < fitness.length; i++){
            System.out.println(blockName[i] + ": " + blockCount[i] + "개 " + (double)blockCount[i] / 100000000 * 100.0 + " %");
        }
    }

    @Test
    void getHardModeRandomBlock() {
        double[] fitness = {0.8, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        int[] blockCount = new int[7];
        for(int i = 0 ; i < 100000000; i++){
            int number =  gvc.rouletteWheelSelection(fitness);
            blockCount[number]++;
        }
        String[] blockName = {"IBlock", "JBlock", "LBlock", "OBlock", "SBlock", "TBlock", "ZBlock"};
        System.out.println("Hard Mode: 1억번 실행");
        for(int i = 0; i < fitness.length; i++){
            System.out.println(blockName[i] + ": " + blockCount[i] + "개 " + (double)blockCount[i] / 100000000 * 100.0 + " %");
        }
    }
}