package Tetris.Controller.game;

import Tetris.Model.game.*;
import Tetris.Model.block.*;
import Tetris.Model.scoreboard.ScoreModel;
import Tetris.Model.scoreboard.ScoreboardModel;
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
    private ScoreboardModel scoreboardModel;
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

    public GameViewController(GameModel currentGame) {
        this.currentGame = currentGame;
        this.scoreboardModel = new ScoreboardModel();
        scoreList = scoreboardModel.getScoreboard(currentGame.getGameState().getGameMode());
        timer = new Timer(INIT_INTERVAL, this);
        timer.setActionCommand("timer");
        timer.start();
    }

    public GameViewController(GameModel game, GameView view) {
        this.currentGame = game;
        this.gameView = view;
        this.scoreboardModel = new ScoreboardModel();
        scoreList = scoreboardModel.getScoreboard(currentGame.getGameState().getGameMode());
        timer = new Timer(INIT_INTERVAL, this);
        timer.setActionCommand("timer");
        timer.start();

    }

    // 일시정지 창 보여주는 메서드
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
    // 게임 종료 창 보여주는 메서드
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
    // 스코어보드 업데이트 가능 여부 체크하는 메서드
    public boolean isPossibleUpdateScore() {
        int currentScore = currentGame.getGameState().getScore();
        return scoreList.size() < 10 || scoreList.get(scoreList.size() - 1).getScore() < currentScore;
    }
    // 게임 모드에 따라 스코어보드에 현재 점수를 업데이트하는 메서드
    public void updateScore() {
        Player player = currentGame.getGameState().getPlayer();
        if (currentGame.getGameState().getGameMode() == GameType.BASIC_MODE) {
            scoreList.add(new ScoreModel(player.getScore(), player.getName(), player.getDifficulty().getKey()));
        } else if (currentGame.getGameState().getGameMode() == GameType.ITEM_MODE) {
            scoreList.add(new ScoreModel(player.getScore(), player.getName()));
        }
        // 기존 스코어보드 리스트에 새로운 점수를 넣고 정렬 후 상위 10개 이하 추출
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
    // 게임 종료 메서드
    private void endGame()  {
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
    public Block getBasicBlock(int blockNumber, BlockType blockType){
        if(blockNumber == BlockNumber.IBLOCK.getBlockNumber()) {
            return new IBlock(blockType);
        } else if(blockNumber == BlockNumber.JBLOCK.getBlockNumber()) {
            return new JBlock(blockType);
        } else if(blockNumber == BlockNumber.LBLOCK.getBlockNumber()) {
            return new LBlock(blockType);
        } else if(blockNumber == BlockNumber.OBLOCK.getBlockNumber()) {
            return new OBlock(blockType);
        } else if(blockNumber == BlockNumber.SBLOCK.getBlockNumber()) {
            return new SBlock(blockType);
        } else if(blockNumber == BlockNumber.TBLOCK.getBlockNumber()) {
            return new TBlock(blockType);
        } else if(blockNumber == BlockNumber.ZBLOCK.getBlockNumber()) {
            return new ZBlock(blockType);
        } else if(blockNumber == BlockNumber.WEIGHT_BLOCK.getBlockNumber()) {
            return new WeightBlock(blockType);
        }
        return new IBlock();
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
        return getBasicBlock(blockNumber, BlockType.NORMAL);
    }
    public Block getNormalModeRandomBlock(){
        Random rnd = new Random(System.nanoTime());
        int number = rnd.nextInt(NORMAL_BLOCK_COUNT);
        return getBasicBlock(number, BlockType.NORMAL);
    }
    public Block getHardModeRandomBlock(){
        /*
         * fitness
         * J, L, O, S, T, Z : 10
         * I : 8
         */
        double[] fitness = {8, 10, 10, 10, 10, 10, 10};
        int blockNumber = rouletteWheelSelection(fitness);
        return getBasicBlock(blockNumber, BlockType.NORMAL);
    }
    // 일반모드 - 블럭 생성 메서드
    public void spawnBasicModeBlock(){
        Block curr;
        switch (currentGame.getGameState().getDifficulty()) {
            case EASY:
                curr = getEasyModeRandomBlock();
                break;
            case HARD:
                curr = getHardModeRandomBlock();
                break;
            default:
                curr = getNormalModeRandomBlock();
                break;
        }
        if(curr == null) return;
        boolean gameContinues = currentGame.spawn(curr);
        if(!gameContinues) {
            endGame();
        }
    }
    // 아이템모드 - 랜덤 블럭 번호 얻기.

    public Block getRandomItemBlock() {
        Random rnd = new Random(System.nanoTime());
        // 화면에 나오는 블럭 개수는 기본 블럭 7개에 무게추 블럭 1개
        int number = rnd.nextInt(NORMAL_BLOCK_COUNT + 1);
        // weight 블럭
        if(number == BlockNumber.WEIGHT_BLOCK.getBlockNumber()) return getBasicBlock(number, BlockType.WEIGHT);

        Block itemBlock;
        // 무게추 제외한 아이템 4개
        int type = rnd.nextInt(ITEM_BLOCK_COUNT - 1);
        switch (type) {
            case 1:
                itemBlock = getBasicBlock(number, BlockType.LINE_DELETE);
                break;
            case 2:
                itemBlock = getBasicBlock(number, BlockType.CLEAR);
                break;
            case 3:
                itemBlock = getBasicBlock(number, BlockType.SLOW);
                break;
            default:
                itemBlock = getBasicBlock(number, BlockType.BOOST);
                break;
        }
        // 아이템 위치 정해주기
        itemBlock.setItemIndex(rnd.nextInt(4));
        return itemBlock;
    }
    // 아이템모드 - 블럭 생성 메서드
    public void spawnItemModeBlock(){
        Block curr;
        if(GameStateModel.getDeletedLineNumber() >= currentGame.getGameState().getNextItemThreshold()){
            curr = getRandomItemBlock();
            currentGame.getGameState().setNextItemThreshold(currentGame.getGameState().getNextItemThreshold() + 10);
        } else {
            curr = getNormalModeRandomBlock();
        }
        if(curr == null) return;
        boolean gameContinues = currentGame.spawn(curr);
        if(!gameContinues) {
            endGame();
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
