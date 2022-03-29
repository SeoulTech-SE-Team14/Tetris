package Tetris.Controller;

import Tetris.Manager.GameManager;
import Tetris.Model.Game;
import org.junit.jupiter.api.Test;

class GameViewControllerTest {
    Game model = new Game(20, 10);
    GameManager manager = new GameManager();
    GameViewController gvc = new GameViewController(model, manager);
    @Test
    void getEasyModeRandomBlock() {
        double[] fitness = {1.2, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        int[] blockCount = new int[7];
        for(int i = 0 ; i < 100000000; i++){
            int number =  gvc.rouletteWheelSelection(fitness);
            blockCount[number]++;
        }
        String[] blockName = {"IBlock", "JBlock", "LBlock", "OBlock", "SBlock", "TBlock", "ZBlock"};
        System.out.println("Easy Mode: 1억번 실행");
        for(int i = 0; i < 7; i++){
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
        for(int i = 0; i < 7; i++){
            System.out.println(blockName[i] + ": " + blockCount[i] + "개 " + (double)blockCount[i] / 100000000 * 100.0 + " %");
        }
    }
}