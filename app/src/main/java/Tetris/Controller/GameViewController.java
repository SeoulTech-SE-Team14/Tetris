package Tetris.Controller;

import Tetris.Manager.GameManager;
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
    private Game model;
    private GameManager manager;
    private Timer timer;

    /**
     * initInterval: 시작 timer delay
     * timerDelay: 업데이트 할 timer delay
     */
    private int timerDelay = 0;
    private static final int initInterval = 1000;

    /**
     * Constructor
     */
    public GameViewController(Game model, GameManager manager) {
        this.model = model;
        this.manager = manager;
        timer = new Timer(initInterval, this);
        timer.setActionCommand("timer");
        timer.start();
    }

    /**
     * 중지 <-> 재개 바꾸는 메서드
     */
    public void reversePause() {
        if(manager.isPaused()){
            timer.restart();
            manager.setPaused(false);
        }
        else {
            timer.stop();
            manager.setPaused(true);
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
    public Block getEasyModeRandomBlock(){
        /**
         * fitness
         * J, L, O, S, T, Z : 10
         * I : 12
         */
        double[] fitness = {12, 10, 10, 10, 10, 10, 10};
        int blockNumber = rouletteWheelSelection(fitness);
        return getBlockByNumber(blockNumber);
    }
    public Block getHardModeRandomBlock(){
        /**
         * fitness
         * J, L, O, S, T, Z : 10
         * I : 8
         */
        double[] fitness = {8,  10, 10, 10, 10, 10, 10};
        int blockNumber = rouletteWheelSelection(fitness);
        return getBlockByNumber(blockNumber);
    }

    /**
     * 모드에 따른 랜덤 블럭 생성 메서드
     */
    public void spawnBasicModeBlock(){
        Block curr = null;
        switch (manager.getDifficulty()) {
            case 0:
                curr = getEasyModeRandomBlock();
                break;
            case 2:
                curr = getHardModeRandomBlock();
                break;
            default:
                Random rnd = new Random(System.currentTimeMillis());
                int number = rnd.nextInt(7);
                curr = getBlockByNumber(number);
                break;
        }

        boolean gameContinues = model.spawn(curr);

        if(!gameContinues) {
            manager.setEnded(true);
            timer.stop();
        }
    }
//    public void spawnItemModeBlock(){ }

    /**
     * ActionListener 메서드
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "timer":
                if(model.getCurr() == null) {
                    // 게임 모드에 따라 분기
                    switch (manager.getGameMode()){
                        case 1:
                            //spawnItemModeBlock();
                            break;
                        default:
                            spawnBasicModeBlock();
                            break;
                    }
                    timerDelay = manager.updateDelay();
                    timer.setDelay(timerDelay);
                } else {
                    model.moveDown();
                }
                break;
        }
    }

    /**
     * KeyListener 메서드
     * @param e: 키
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(model.getCurr() == null) return;
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if(manager.isPaused()) break;
                model.moveDown();
                break;
            case KeyEvent.VK_RIGHT:
                if(manager.isPaused()) break;
                model.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                if(manager.isPaused()) break;
                model.moveLeft();
                break;
            case KeyEvent.VK_UP:
                if(manager.isPaused()) break;
                if(model.canRotate()) {
                    model.eraseCurr();
                    model.rotateBlock();
                }
                break;
            case KeyEvent.VK_S:
                if(manager.isPaused()) break;
                model.fall();
                break;
            case KeyEvent.VK_P:
                reversePause();
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
