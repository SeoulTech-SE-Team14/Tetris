package Tetris.Controller;

import Tetris.Model.GameState;
import Tetris.Model.*;

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

    /**
     * initInterval: 시작 timer delay
     *
     */
    private static final int initInterval = 1000;
    private static final int BLOCK_COUNT = 7;
    private static final int ITEM_COUNT = 5;
    /**
     * Constructor
     */
    public GameViewController(GameBoard game) {
        this.currentGame = game;
        timer = new Timer(initInterval, this);
        timer.setActionCommand("timer");
        timer.start();
    }
    /**
     * 중지 <-> 재개 바꾸는 메서드
     */
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
     * @param number 블럭 숫자
     * @return 랜덤 블럭
     */
    public Block getBlockByNumber(int number){
        switch(number) {
            case 1:
                return new JBlock();
            case 2:
                return new LBlock();
            case 3:
                return new ZBlock();
            case 4:
                return new SBlock();
            case 5:
                return new TBlock();
            case 6:
                return new OBlock();
            default:
                return new IBlock();
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
        for(int i = 0; i < fitness.length; i++){
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
     * 일반모드
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
        int number = rnd.nextInt(BLOCK_COUNT);
        return getBlockByNumber(number);
    }
    /**
     * 아이템 모드 - 랜덤 블럭 번호 얻기.
     */
    public Block getRandomItemBlock() {
        Random rnd = new Random(System.nanoTime());
        int number = rnd.nextInt(ITEM_COUNT);
        return getBlockByNumber(number);
    }
    /**
     * 일반모드 블럭 생성 메서드
     */
    public void spawnBasicModeBlock(){
        Block curr = null;
        switch (currentGame.getGameState().getDifficulty()) {
            case 0:
                curr = getEasyModeRandomBlock();
                break;
            case 2:
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
    /**
     * 아이템 모드 블럭 생성 메서드
     */
    public void spawnItemModeBlock(){
        Block curr = null;
        if(GameState.getDeletedLineNumber() % 10 == 0){
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
    /**
     * ActionListener 메서드
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("timer".equals(e.getActionCommand())) {
            if (currentGame.getCurr() == null) {
                // 게임 모드에 따라 분기
                if (currentGame.getGameState().getGameMode() == 1) {
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
    /**
     * KeyListener 메서드
     * @param e 입력 키
     */
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) {
        if(currentGame.getCurr() == null) return;
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if(currentGame.getGameState().isPaused()) break;
                currentGame.moveDown();
                break;
            case KeyEvent.VK_RIGHT:
                if(currentGame.getGameState().isPaused()) break;
                currentGame.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                if(currentGame.getGameState().isPaused()) break;
                currentGame.moveLeft();
                break;
            case KeyEvent.VK_UP:
                if(currentGame.getGameState().isPaused()) break;
                if(currentGame.canRotate()) {
                    currentGame.eraseCurr();
                    currentGame.getCurr().rotate();
                }
                break;
            case KeyEvent.VK_S:
                if(currentGame.getGameState().isPaused()) break;
                currentGame.fall();
                break;
            case KeyEvent.VK_P:
                reversePause();
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { }
}
