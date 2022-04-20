package Tetris.Controller.game;

import Tetris.Model.game.*;
import Tetris.Model.block.*;
import Tetris.Model.scoreboard.ScoreModel;
import Tetris.Util.*;
import Tetris.View.game.EndDialog;
import Tetris.View.game.GameView;
import Tetris.View.game.InputDialog;
import Tetris.View.game.PauseDialog;

import javax.swing.Timer;

import java.awt.event.*;
import java.util.*;

/**
 * 테트리스 게임 View 조작 컨트롤러
 * @author 김영균
 */
public class GameViewController implements KeyListener, ActionListener {
    private GameModel currentGame;
    private GameView gameView;
    private Timer timer;
    private List<ScoreModel> scoreList;

    private final int ROTATE_KEY = JsonReader.getKey(BlockEventType.ROTATE);
    private final int RIGHT_KEY = JsonReader.getKey(BlockEventType.RIGHT);
    private final int LEFT_KEY = JsonReader.getKey(BlockEventType.LEFT);
    private final int DOWN_KEY = JsonReader.getKey(BlockEventType.DOWN);
    private final int FALL_KEY = JsonReader.getKey(BlockEventType.FALL);
    private final int PAUSE_KEY = JsonReader.getKey(BlockEventType.PAUSE);

    // INIT_INTERVAL: 시작 timer delay
    private static final int INIT_INTERVAL = 1000;
    private static final int NORMAL_BLOCK_COUNT = 7;
    private static final int ITEM_BLOCK_COUNT = 5;

    //test용
    public GameViewController(GameModel currentGame) {
        this.currentGame = currentGame;
        scoreList = JsonReader.getScoreBoard(currentGame.getGameState().getGameMode());
        timer = new Timer(INIT_INTERVAL, this);
        timer.setActionCommand("timer");
        timer.start();
    }

    public GameViewController(GameModel game, GameView view) {
        this.currentGame = game;
        this.gameView = view;
        scoreList = JsonReader.getScoreBoard(currentGame.getGameState().getGameMode());
        timer = new Timer(INIT_INTERVAL, this);
        timer.setActionCommand("timer");
        timer.start();

    }
    private void showPauseDialog() {
        PauseDialogModel pauseDialogModel = new PauseDialogModel();
        int x = gameView.getLocation().x + (currentGame.getScreenWidth() -  pauseDialogModel.getWidth()) / 2;
        int y = gameView.getLocation().y + (currentGame.getScreenHeight() - pauseDialogModel.getHeight()) / 2;
        PauseDialog dialog = new PauseDialog(gameView, x, y);
        GameType type = currentGame.getGameState().getGameMode();
        GameType difficulty = currentGame.getGameState().getDifficulty();
        PauseDialogController pauseDialogController = new PauseDialogController(pauseDialogModel, dialog, gameView, type, difficulty);
        dialog.addKeyListener(pauseDialogController);
        pauseDialogModel.addObserver(dialog);
        dialog.setVisible(true);
        // 나중에 수정해야함.
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosing(e);
                dialog.dispose();
                reversePause();
            }
        });
    }
    private void showEndDialog() {
        EndDialogModel endDialogModel = new EndDialogModel();
        int x = gameView.getLocation().x + (currentGame.getScreenWidth() -  endDialogModel.getWidth()) / 2;
        int y = gameView.getLocation().y + (currentGame.getScreenHeight() - endDialogModel.getHeight()) / 2;
        EndDialog dialog = new EndDialog(gameView, currentGame, x, y);
        EndDialogController endDialogController = new EndDialogController(endDialogModel, dialog, gameView);
        dialog.addKeyListener(endDialogController);
        endDialogModel.addObserver(dialog);
        dialog.setVisible(true);
    }
    // 사용자 이름 입력받는 창. 입력 받은 이름은 GameState에 Player에 저장된다.
    private void showInputDialog() {
        int x = gameView.getLocation().x + (currentGame.getScreenWidth() -  200) / 2;
        int y = gameView.getLocation().y + (currentGame.getScreenHeight() - 100) / 2;
        InputDialog inputDialog = new InputDialog(gameView, currentGame, x, y);
        inputDialog.setVisible(true);
    }
    public boolean isPossibleUpdateScore() {
        int currentScore = currentGame.getGameState().getScore();
        return scoreList.size() < 10 || scoreList.get(scoreList.size() - 1).getScore() < currentScore;
    }
    public void updateScore() {
        Player player = currentGame.getGameState().getPlayer();
        if (currentGame.getGameState().getGameMode() == GameType.BASIC_MODE) {
            scoreList.add(new ScoreModel(player.getScore(), player.getName(), player.getDifficulty().getKey()));
        } else if (currentGame.getGameState().getGameMode() == GameType.ITEM_MODE) {
            scoreList.add(new ScoreModel(player.getScore(), player.getName()));
        }
        scoreList.sort(ScoreModel::compareTo);
        List<Map<String, String>> scoreboardJsonArray = new ArrayList<>();
        for(int i = 0; i < Math.min(scoreList.size(), 10); i++){
            ScoreModel scoreModel = scoreList.get(i);
            Map<String, String> json = new HashMap<>();
            json.put(ScoreboardJsonKeyType.SCORE.getKey(), Integer.toString(scoreModel.getScore()));
            json.put(ScoreboardJsonKeyType.NAME.getKey(), scoreModel.getName());
            if(currentGame.getGameState().getGameMode() == GameType.BASIC_MODE) {
                json.put(ScoreboardJsonKeyType.DIFFICULTY.getKey(), scoreModel.getDifficulty());
            }
            scoreboardJsonArray.add(json);
        }
        JsonWriter.setScoreBoard(scoreboardJsonArray, currentGame.getGameState().getGameMode());
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
            showPauseDialog();
        }
    }
    public void endGame()  {
        currentGame.getGameState().setEnded(true);
        timer.stop();
        if(isPossibleUpdateScore()){
            showInputDialog();
            updateScore();
        }
        showEndDialog();
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
        }
        return null;
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
        Block curr = new Block();
        switch (currentGame.getGameState().getDifficulty()) {
            case EASY:
                curr = getEasyModeRandomBlock();
                break;
            case NORMAL:
                curr = getBasicModeRandomBlock();
                break;
            case HARD:
                curr = getHardModeRandomBlock();
                break;
            default:
                break;
        }
        boolean gameContinues = currentGame.spawn(curr);
        if(!gameContinues) {
            endGame();
        }
    }
    // 아이템 모드 블럭 생성 메서드
    public void spawnItemModeBlock(){
        Block curr = null;
        if(GameStateModel.getSpawnedBlockNumber() > 0 && GameStateModel.getSpawnedBlockNumber() % 9 == 0){
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
                switch(currentGame.getGameState().getGameMode()){
                    case BASIC_MODE:
                        spawnBasicModeBlock();
                        break;
                    case ITEM_MODE:
                        spawnItemModeBlock();
                        break;
                    default:
                        break;
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
