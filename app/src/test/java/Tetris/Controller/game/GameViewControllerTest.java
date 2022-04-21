package Tetris.Controller.game;

import Tetris.Model.block.Block;
import Tetris.Model.game.EndDialogModel;
import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Model.game.Player;
import Tetris.Model.scoreboard.ScoreModel;
import Tetris.Model.scoreboard.ScoreboardModel;
import Tetris.Util.BlockType;
import Tetris.Util.GameType;
import Tetris.Util.JsonReader;
import Tetris.View.game.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameViewControllerTest {
    GameType type;
    GameType difficulty;
    GameStateModel gameStateModel;
    GameModel gameModel;
    GameViewController gameViewController;
    ScoreboardModel scoreboardModel;
    GameView gameView;
    Player player;
    List<ScoreModel> basicModeScorelist;
    List<ScoreModel> itemModeScorelist;
    @BeforeEach
    void init(){
        basicModeScorelist = JsonReader.getScoreBoard(GameType.BASIC_MODE);
        itemModeScorelist = JsonReader.getScoreBoard(GameType.ITEM_MODE);
    }
    @Test
    @DisplayName("[아이템모드] 순위 변경 시 순위표 업데이트 테스트")
    void itemModeUpdateScoreTest() {
        gameStateModel = new GameStateModel(GameType.ITEM_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        int inputScore = 1;
        if(itemModeScorelist.size() > 0){
            inputScore = itemModeScorelist.get(0).getScore() + 1;
        }
        gameModel.getGameState().setScore(inputScore);
        player = new Player("최씨", gameModel.getGameState().getScore(), GameType.ITEM_MODE);
        gameModel.getGameState().setPlayer(player);
        gameViewController.updateScore();

        List<ScoreModel> list = JsonReader.getScoreBoard(GameType.ITEM_MODE);
        assertEquals("최씨", list.get(0).getName());
        assertEquals(inputScore, list.get(0).getScore());
    }

    @Test
    @DisplayName("[일반모드] 스코어 업데이트 가능 여부 테스트")
    void basicModeIsPossibleUpdateScoreTest() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE,  GameType.NORMAL);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        gameModel.getGameState().setScore(-1);

        //then
        assertFalse(gameViewController.isPossibleUpdateScore());
    }

    @Test
    @DisplayName("[일반모드] 순위표 업데이트 테스트")
    void basicModeUpdateScoreTest() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE, GameType.EASY);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        int inputScore = 1;
        if(itemModeScorelist.size() > 0){
            inputScore = basicModeScorelist.get(0).getScore() + 1;
        }
        gameModel.getGameState().setScore(inputScore);
        player = new Player("최씨", gameModel.getGameState().getScore(), GameType.EASY);
        gameModel.getGameState().setPlayer(player);
        gameViewController.updateScore();

        List<ScoreModel> list = JsonReader.getScoreBoard(GameType.BASIC_MODE);
        assertEquals("최씨", list.get(0).getName());
        assertEquals(inputScore, list.get(0).getScore());
    }

    @Test
    @DisplayName("[일반모드] 일시정지 true -> false 전환 테스트")
    void reversePauseTest1() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        gameModel.getGameState().setPaused(true);
        gameViewController.reversePause();
        assertFalse(gameModel.getGameState().isPaused());
    }
    @Test
    @DisplayName("[일반모드] 일시정지 false -> true 전환 테스트")
    void reversePauseTest2() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(0, 0, gameModel);
        gameView.dispose();
        gameViewController = new GameViewController(gameModel, gameView);

        gameModel.getGameState().setPaused(false);
        gameViewController.reversePause();
        assertTrue(gameModel.getGameState().isPaused());
    }
    @Test
    @DisplayName("[일반모드] 일반 블럭 생성 테스트")
    void spawnBasicBlockTest() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);
        Block block =  gameViewController.getBasicBlock(0, BlockType.NORMAL);
        assertEquals(BlockType.NORMAL, block.getBlockType());
    }
    @Test
    @DisplayName("[일반모드] 난이도 쉬움 블럭 생성 확률 테스트")
    void easyModeSpawnRandomBlockTest(){
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameStateModel.setDifficulty(GameType.EASY);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        gameViewController.spawnBasicModeBlock();
        Block block;
        int[] createdBlockNumberList = new int[7];
        for(int i = 0; i < 100000; i++){
            gameViewController.spawnBasicModeBlock();
            createdBlockNumberList[gameModel.getCurr().getNumber()] += 1;
        }
        for(int i = 0; i < 7; i++){
            System.out.println(createdBlockNumberList[i]);
        }
        int iBlockCnt = createdBlockNumberList[0];
        assertTrue(createdBlockNumberList[1] * 1.15 <= iBlockCnt);
        assertTrue(createdBlockNumberList[2] * 1.15 <= iBlockCnt);
        assertTrue(createdBlockNumberList[3] * 1.15 <= iBlockCnt);
        assertTrue(createdBlockNumberList[4] * 1.15 <= iBlockCnt);
        assertTrue(createdBlockNumberList[5] * 1.15 <= iBlockCnt);
        assertTrue(createdBlockNumberList[6] * 1.15 <= iBlockCnt);
    }
    @Test
    @DisplayName("[일반모드] 난이도 어려움 블럭 생성 확률 테스트")
    void hardModeSpawnRandomBlockTest(){
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameStateModel.setDifficulty(GameType.HARD);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        gameViewController.spawnBasicModeBlock();
        Block block;
        int[] createdBlockNumberList = new int[7];
        for(int i = 0; i < 100000; i++){
            gameViewController.spawnBasicModeBlock();
            createdBlockNumberList[gameModel.getCurr().getNumber()] += 1;
        }


        int iBlockCnt = createdBlockNumberList[0];
        assertTrue(createdBlockNumberList[1] >= iBlockCnt * 1.15);
        assertTrue(createdBlockNumberList[2] >= iBlockCnt * 1.15);
        assertTrue(createdBlockNumberList[3] >= iBlockCnt * 1.15);
        assertTrue(createdBlockNumberList[4] >= iBlockCnt * 1.15);
        assertTrue(createdBlockNumberList[5] >= iBlockCnt * 1.15);
        assertTrue(createdBlockNumberList[6] >= iBlockCnt * 1.15);
    }
    @Test
    @DisplayName("[일반모드] 난이도 일반 블럭 생성 확률 테스트")
    void normalModeSpawnRandomBlockTest(){
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameStateModel.setDifficulty(GameType.NORMAL);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        gameViewController.spawnBasicModeBlock();
        Block block;
        int[] createdBlockNumberList = new int[7];
        for(int i = 0; i < 70000000; i++){
            gameViewController.spawnBasicModeBlock();
            createdBlockNumberList[gameModel.getCurr().getNumber()] += 1;
        }


        assertTrue(createdBlockNumberList[0] <= 10500000);
        assertTrue(createdBlockNumberList[1] <= 10500000);
        assertTrue(createdBlockNumberList[2] <= 10500000);
        assertTrue(createdBlockNumberList[3] <= 10500000);
        assertTrue(createdBlockNumberList[4] <= 10500000);
        assertTrue(createdBlockNumberList[5] <= 10500000);
        assertTrue(createdBlockNumberList[6] <= 10500000);
    }
    @Test
    @DisplayName("[아이템 모드] 아이템 생성")
    void spawnItemBlockTest(){
        gameStateModel = new GameStateModel(GameType.ITEM_MODE);
        gameStateModel.setDifficulty(GameType.NORMAL);
        GameStateModel.setDeletedLineNumber(10);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        gameViewController.spawnItemModeBlock();

        assertEquals(20, gameStateModel.getNextItemThreshold());
    }
    @Test
    @DisplayName("[아이템 모드] 일반블럭 생성")
    void spawnItemBlockTest2(){
        gameStateModel = new GameStateModel(GameType.ITEM_MODE);
        gameStateModel.setDifficulty(GameType.NORMAL);
        GameStateModel.setDeletedLineNumber(0);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        gameViewController.spawnItemModeBlock();
        gameViewController.spawnItemModeBlock();
        assertEquals(BlockType.NORMAL, gameModel.getCurr().getBlockType());
    }
    @Test
    @DisplayName("[아이템모드] 무게추 아이템 블럭 생성 테스트")
    void weightBlock(){
        gameStateModel = new GameStateModel(GameType.ITEM_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);
        Block block =  gameViewController.getBasicBlock(7, BlockType.WEIGHT);
        assertEquals(BlockType.WEIGHT, block.getBlockType());
    }
    @Test
    @DisplayName("블럭 널일 때 입력 테스트")
    void keyPressedTest1() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(10, 20, gameModel);
        gameViewController = new GameViewController(gameModel, gameView);
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        gameViewController.keyPressed(key);
        assertNull(gameModel.getCurr());
    }
    @Test
    @DisplayName("정지상태 키 입력 테스트")
    void keyPressedTest2() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(10, 20, gameModel);
        gameViewController = new GameViewController(gameModel, gameView);
        gameModel.getGameState().setPaused(true);
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        gameViewController.keyPressed(key);
        assertTrue(gameModel.getGameState().isPaused());
    }
    @Test
    @DisplayName("아래 키 입력 테스트")
    void keyPressedTest3() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(10, 20, gameModel);
        gameViewController = new GameViewController(gameModel, gameView);
        gameViewController.spawnBasicModeBlock();
        gameViewController.spawnBasicModeBlock();
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        gameViewController.keyPressed(key);
        assertEquals(3, gameModel.getCurr().getX());
        assertEquals(1, gameModel.getCurr().getY());
    }
    @Test
    @DisplayName("오른쪽 키 입력 테스트")
    void keyPressedTest4() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(10, 20, gameModel);
        gameViewController = new GameViewController(gameModel, gameView);
        gameViewController.spawnBasicModeBlock();
        gameViewController.spawnBasicModeBlock();
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'a');
        gameViewController.keyPressed(key);
        assertEquals(4, gameModel.getCurr().getX());
        assertEquals(0, gameModel.getCurr().getY());
    }
    @Test
    @DisplayName("왼쪽 키 입력 테스트")
    void keyPressedTest5() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(10, 20, gameModel);
        gameViewController = new GameViewController(gameModel, gameView);
        gameViewController.spawnBasicModeBlock();
        gameViewController.spawnBasicModeBlock();
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'a');
        gameViewController.keyPressed(key);
        assertEquals(2, gameModel.getCurr().getX());
        assertEquals(0, gameModel.getCurr().getY());
    }
    @Test
    @DisplayName("회전 키 입력 테스트 - 회전 불가")
    void keyPressedTest6() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(10, 20, gameModel);
        gameViewController = new GameViewController(gameModel, gameView);
        gameViewController.spawnBasicModeBlock();
        gameViewController.spawnBasicModeBlock();
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        gameViewController.keyPressed(key);
        assertEquals(3, gameModel.getCurr().getX());
        assertEquals(0, gameModel.getCurr().getY());
    }

//    @Test
//    @DisplayName("게임 종료 테스트 - 순위표 업데이트 없음")
//    void gameEndTest(){
//        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
//        gameStateModel.setDifficulty(GameType.NORMAL);
//        gameStateModel.setScore(0);
//        GameStateModel.setDeletedLineNumber(10);
//        gameModel = new GameModel(gameStateModel, 20, 10);
//        gameView = new GameView(200, 100, gameModel);
//        gameViewController = new GameViewController(gameModel, gameView);
//        int[][] visited = new int[20][10];
//        for(int i = 0; i < 10; i++){ visited[0][i] = 1; }
//        gameModel.setVisited(visited);
//        gameViewController.spawnBasicModeBlock();
//        gameViewController.spawnBasicModeBlock();
//        assertTrue(gameStateModel.isEnded());
//    }
//    @Test
//    @DisplayName("게임 종료 테스트 - 순위표 업데이트 있음")
//    void gameEndTest2(){
//        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
//        gameStateModel.setDifficulty(GameType.NORMAL);
//        gameStateModel.setScore(basicModeScorelist.get(0).getScore()+ 1);
//        GameStateModel.setDeletedLineNumber(10);
//        gameModel = new GameModel(gameStateModel, 20, 10);
//        gameView = new GameView(200, 100, gameModel);
//        gameViewController = new GameViewController(gameModel, gameView);
//        int[][] visited = new int[20][10];
//        for(int i = 0; i < 10; i++){ visited[0][i] = 1; }
//        gameModel.setVisited(visited);
//        gameViewController.spawnBasicModeBlock();
//        gameViewController.spawnBasicModeBlock();
//        assertTrue(gameStateModel.isEnded());
//    }
}