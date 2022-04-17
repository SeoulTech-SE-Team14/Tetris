package Tetris.Controller.game;

import Tetris.Model.game.GameBoard;
import Tetris.Model.game.GameState;
import Tetris.Model.block.*;
import Tetris.Util.JsonReader;
import Tetris.Util.KeyEventType;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * 테트리스 게임 View 조작 컨트롤러
 * @author 김영균
 */
public class GameViewController implements KeyListener, ActionListener {
    private GameBoard currentGame;
    private Timer timer;

    private final int ROTATE_KEY = JsonReader.getKey(KeyEventType.ROTATE);
    private final int RIGHT_KEY = JsonReader.getKey(KeyEventType.RIGHT);
    private final int LEFT_KEY = JsonReader.getKey(KeyEventType.LEFT);
    private final int DOWN_KEY = JsonReader.getKey(KeyEventType.DOWN);
    private final int FALL_KEY = JsonReader.getKey(KeyEventType.FALL);
    private final int PAUSE_KEY = JsonReader.getKey(KeyEventType.PAUSE);

    // INIT_INTERVAL: 시작 timer delay
    private static final int INIT_INTERVAL = 1000;
    private static final int NORMAL_BLOCK_COUNT = 7;
    private static final int ITEM_BLOCK_COUNT = 5;

    public GameViewController(GameBoard game) {
        this.currentGame = game;
        timer = new Timer(INIT_INTERVAL, this);
        timer.setActionCommand("timer");
        timer.start();
    }

    // 중지 <-> 게임 중 바꾸는 메서드
    public void reversePause() {
        if(currentGame.getGameState().isPaused()){
            timer.restart();
            currentGame.getGameState().setPaused(false);
        }
        else {
            timer.stop();
            currentGame.getGameState().setPaused(true);
            // 종료, 재개 모달창 보여줘야함.
        }
    }
    /**
     * 랜덤 블럭 넘버 -> 블럭으로 바꾸는 메서드
     * @param blockNumber 블럭 숫자
     * @return 랜덤 블럭
     */
    public Block getBlockByNumber(int blockNumber){
        if(blockNumber == BlockNumber.IBLOCK.getBlockNumber()) {
            return new IBlock();
        } else if(blockNumber == BlockNumber.JBLOCK.getBlockNumber()) {
            return new JBlock();
        } else if(blockNumber == BlockNumber.LBLOCK.getBlockNumber()) {
            return new LBlock();
        } else if(blockNumber == BlockNumber.OBLOCK.getBlockNumber()) {
            return new OBlock();
        } else if(blockNumber == BlockNumber.SBLOCK.getBlockNumber()) {
            return new SBlock();
        } else if(blockNumber == BlockNumber.TBLOCK.getBlockNumber()) {
            return new TBlock();
        } else if(blockNumber == BlockNumber.ZBLOCK.getBlockNumber()) {
            return new ZBlock();
        } else if(blockNumber == BlockNumber.WEIGHT_BLOCK.getBlockNumber()) {
            return new WeightBlock();
        } else {
            return new LineDeleteBlock();
        }

    }
    /**
     * @param fitness 블럭 별 생성 가중치 배열
     * @return 가중치에 따라 생성된 블럭 넘버
     */
    public int rouletteWheelSelection(double[] fitness){
        double totalSum = 0;
        for (double f : fitness) totalSum += f;
        double[] probability = new double[fitness.length];
        for(int i = 0; totalSum != 0 && i < fitness.length; i++){
            probability[i] = fitness[i] / totalSum;
        }
        Random rnd = new Random(System.nanoTime());
        double rndNumber = rnd.nextDouble();
        double offset = 0;
        int blockNumber = 0;
        for(; blockNumber < fitness.length; blockNumber++){
            offset += probability[blockNumber];
            if(offset > rndNumber){
                return blockNumber;
            }
        }
        return blockNumber;
    }
    /**
     * 일반모드 - 난이도에 따른 블럭생성 메서드
     * @return 난이도에 따른 랜덤 블럭 번호 얻기.
     */
    public Block getEasyModeRandomBlock(){
        /*
         * fitness
         * J, L, O, S, T, Z : 10
         * I : 12
         */
        double[] fitness = {12, 10, 10, 10, 10, 10, 10};
        int blockNumber = rouletteWheelSelection(fitness);
        return getBlockByNumber(blockNumber);
    }
    public Block getHardModeRandomBlock(){
        /*
         * fitness
         * J, L, O, S, T, Z : 10
         * I : 8
         */
        double[] fitness = {8,  10, 10, 10, 10, 10, 10};
        int blockNumber = rouletteWheelSelection(fitness);
        return getBlockByNumber(blockNumber);
    }
    public Block getBasicModeRandomBlock(){
        Random rnd = new Random(System.nanoTime());
        int number = rnd.nextInt(NORMAL_BLOCK_COUNT);
        return getBlockByNumber(number);
    }


    // 아이템 모드 - 랜덤 블럭 번호 얻기.
    public Block getRandomItemBlock() {
        Random rnd = new Random(System.nanoTime());
        // 아이템 블럭 넘버를 구하기 위해 일반 블럭 개수를 더해준다.
        int itemNumber = rnd.nextInt(ITEM_BLOCK_COUNT) + NORMAL_BLOCK_COUNT;
        return getBlockByNumber(itemNumber);
    }

    // 일반모드 블럭 생성 메서드
    public void spawnBasicModeBlock(){
        Block curr = null;
        switch (currentGame.getGameState().getDifficulty()) {
            case "easy":
                curr = getEasyModeRandomBlock();
                break;
            case "hard":
                curr = getHardModeRandomBlock();
                break;
            default:
                curr = getBasicModeRandomBlock();
                break;
        }

        boolean gameContinues = currentGame.spawn(curr);

        if(!gameContinues) {
            currentGame.getGameState().setEnded(true);
            timer.stop();
            // 게임 종료 모달 띄우기.
        }
    }
    // 아이템 모드 블럭 생성 메서드
    public void spawnItemModeBlock(){
        Block curr = null;
        if(GameState.getSpawnedBlockNumber() > 0 && GameState.getSpawnedBlockNumber() % 9 == 0){
            curr = getRandomItemBlock();
        } else {
            curr = getBasicModeRandomBlock();
        }
        boolean gameContinues = currentGame.spawn(curr);
        if(!gameContinues) {
            currentGame.getGameState().setEnded(true);
            timer.stop();
            // 게임 종료 모달 띄우기.
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("timer".equals(e.getActionCommand())) {
            if (currentGame.getCurr() == null) {
                // 게임 모드에 따라 분기
                if (currentGame.getGameState().getGameMode().equals("item")) {
                    spawnItemModeBlock();
                } else {
                    spawnBasicModeBlock();
                }
                timer.setDelay(currentGame.getGameState().updateDelay());
            } else {
                currentGame.moveDown();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(currentGame.getCurr() == null) return;
        if(currentGame.getGameState().isPaused()) return;
        if(e.getKeyCode() == DOWN_KEY) {
            currentGame.moveDown();
        } else if(e.getKeyCode() == RIGHT_KEY){
            currentGame.moveRight();
        } else if(e.getKeyCode() == LEFT_KEY) {
            currentGame.moveLeft();
        } else if(e.getKeyCode() == ROTATE_KEY && currentGame.canRotate()) {
            currentGame.eraseCurr();
            currentGame.getCurr().rotate();
        } else if(e.getKeyCode() == FALL_KEY) {
            currentGame.fall();
        } else if(e.getKeyCode() == PAUSE_KEY) {
            reversePause();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // default implementation ignored
    }
}
