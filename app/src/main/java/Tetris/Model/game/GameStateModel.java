package Tetris.Model.game;

import Tetris.Util.GameType;
import Tetris.Util.JsonReader;

import java.util.Observable;

/**
 * 현재 진행 중인 게임 상태 관리 클래스
 * @author 김영균
 */
public class GameStateModel extends Observable {
    private static final int MAX_DELAY = 1000;
    private static final int MIN_DELAY = 200;
    private int maxScore = 11;

    private static int spawnedBlockNumber = 0; // 생성된 블럭 수
    private static int deletedLineNumber = 0;  // 삭제된 라인 수

    private long spawnTime = 0; // 블럭이 생성된 시간
    private int nextItemThreshold = 10;
    private int score = 0;
    private Player player;
    private String colorMode;
    /**
     * 현재 게임 난이도
     * easy, normal, hard
     */
    private GameType difficulty;
    /**
     * 현재 게임 모드
     * basic mode, item mode;
     */
    private GameType gameMode;
    private boolean isPaused = false;
    private boolean isEnded = false;

    public GameStateModel(GameType gameMode) {
        this.colorMode = JsonReader.getColorMode();
        this.gameMode = gameMode;
        this.difficulty = GameType.NORMAL;
        this.player = new Player();
    }
    public GameStateModel(GameType gameMode, GameType difficulty) {
        this.colorMode = JsonReader.getColorMode();
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.player = new Player();
    }

    public Player getPlayer() {
        return player;
    }

    public int getNextItemThreshold() {
        return nextItemThreshold;
    }

    public void setNextItemThreshold(int nextItemThreshold) {
        this.nextItemThreshold = nextItemThreshold;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 블럭이 생성되는 속도 조절 메서드
     */

    public int updateDelay() {
        int standard1 = spawnedBlockNumber / 5;
        int standard2 = deletedLineNumber / 3;
        System.out.println(Math.max(MAX_DELAY - 50 * (standard1 + standard2), MIN_DELAY));
        return Math.max(MAX_DELAY - 50 * (standard1 + standard2), MIN_DELAY);
    }

    /**
     * 생성된 블럭 +1 하는 메서드
     */
    public static void updateSpawnedBlockNumber() {
        spawnedBlockNumber++;
    }

    /**
     * 삭제된 라인 +1 하는 메서드
     */
    public static void updateDeletedLineNumber() { deletedLineNumber++; }

    public static void setSpawnedBlockNumber(int spawnedBlockNumber) {
        GameStateModel.spawnedBlockNumber = spawnedBlockNumber;
    }

    public static void setDeletedLineNumber(int deletedLineNumber) {
        GameStateModel.deletedLineNumber = deletedLineNumber;
    }

    /**
     * 점수 업데이트 메서드
     * @param point 추가할 점수
     */
    public void updateScore(int point) { this.score += point; }

    /**
     * Getter Method
     */
    public boolean isPaused() {
        return isPaused;
    }
    public boolean isEnded() {
        return isEnded;
    }

    /**
     * @return 게임 난이도 easy: 0, normal: 1, hard: 2
     */
    public GameType getDifficulty() {
        return difficulty;
    }

    /**
     * @return 게임 모드 normal: 0, item: 1
     */
    public GameType getGameMode() {
        return gameMode;
    }

    public int getMaxScore() {
        return maxScore;
    }



    /**
     * 보너스 점수 = max(0, maxScore - 10 * 걸린 초)
     * @return 보너스 점수
     */
    public int getBonusScore() {
        long placeTime = System.currentTimeMillis();
        double secondPer10s = (double)(placeTime - spawnTime) / 1000;
        int bonusScore = (int)(10.0 * secondPer10s);
        spawnTime = 0;
        return Math.max(0, maxScore - bonusScore);
    }

    public static int getSpawnedBlockNumber() {
        return spawnedBlockNumber;
    }

    public static int getDeletedLineNumber() {
        return deletedLineNumber;
    }
    public int getScore() {
        return score;
    }
    public String getColorMode() {
        return colorMode;
    }

    public void notice(){
        setChanged();
        notifyObservers();
    }
    /**
     * Setter Method
     */
    public void setSpawnTime() { this.spawnTime = System.currentTimeMillis(); }
    public void setPaused(boolean paused) {
        isPaused = paused;
        notice();
    }
    public void setEnded(boolean ended) {
        isEnded = ended;
    }
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
