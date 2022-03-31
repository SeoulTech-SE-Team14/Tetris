package Tetris.Manager;

import java.util.Observable;

/**
 * 현재 진행 중인 게임 상태 관리 클래스
 * @author 김영균
 */
public class GameManager extends Observable {
    private final int maxDelay = 1000;
    private final int minDelay = 200;

    private long spawnTime = 0; // 블럭이 생성된 시간
    private static int spawnedBlockNumber = 0; // 생성된 블럭 수
    private static int deletedLineNumber = 0;  // 삭제된 라인 수
    private int gameMode = 0; // normal 0, item 1
    private int difficulty = 0; // easy 0, normal 1, hard  2
    private boolean isPaused = false;
    private boolean isEnded = false;

    /**
     * Constructor
     */
    public GameManager() { /* 모든 멤버 변수가 값이 정해져 있어서 생성자 필요없음. */ }

    /**
     * Update Method
     */
    public int updateDelay() {
        // 여기서 블럭 떨어지는 속도 조절
        int standard1 = spawnedBlockNumber / 5;
        int standard2 = deletedLineNumber / 3;
        return Math.max(maxDelay - 50 * (standard1 + standard2), minDelay);
    }
    public void updateSpawnedBlockNumber() {
        spawnedBlockNumber++;
    }
    public void updateDeletedLineNumber() { deletedLineNumber++; }

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
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @return 게임 모드 normal: 0, item: 1
     */
    public int getGameMode() {
        return gameMode;
    }

    /**
     * 보너스 시간 = max(0, 11 - 10 * 걸린 초)
     * @return 보너스 점수
     */
    public int getBonusScore() {
        long placeTime = System.currentTimeMillis();
        double secondPer10s = ((double)(placeTime - spawnTime)/1000);
        int bonusScore = (int)(10.0 * secondPer10s);
        spawnTime = 0;
        return Math.max(0, 11 - bonusScore);
    }

    public static int getDeletedLineNumber() {
        return deletedLineNumber;
    }

    /**
     * Setter Method
     */
    public void setSpawnTime() { this.spawnTime = System.currentTimeMillis(); }
    public void setPaused(boolean paused) {
        isPaused = paused;
        setChanged();
        notifyObservers();
    }
    public void setEnded(boolean ended) {
        isEnded = ended;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
}
