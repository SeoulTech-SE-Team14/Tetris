package Tetris.Model;

import java.util.Observable;

/**
 * 현재 진행 중인 게임 상태 관리 클래스
 * @author 김영균
 */
public class GameState extends Observable {
    private final int MAX_DELAY = 1000;
    private final int MIN_DELAY = 200;

    private static int spawnedBlockNumber = 0; // 생성된 블럭 수
    private static int deletedLineNumber = 0;  // 삭제된 라인 수

    private long spawnTime = 0; // 블럭이 생성된 시간
    private int score = 0;
    /**
     * normal: 0
     * 제1 색이상: 1
     * 제2 색각이상: 2
     * 제3 색각이상: 3
     * 전색약: 4
     */
    private int colorMode = 0; // 0 normal. ... +a
    private int gameMode = 0; // normal - 0, item - 1
    private int difficulty = 0; // easy - 0, normal - 1, hard - 2
    private boolean isPaused = false;
    private boolean isEnded = false;

    public GameState(int colorMode, int gameMode, int difficulty) {
        this.colorMode = colorMode;
        this.gameMode = gameMode;
        this.difficulty = difficulty;
    }

    /**
     * 블럭이 생성되는 속도 조절 메서드
     */
    public int updateDelay() {
        int standard1 = spawnedBlockNumber / 5;
        int standard2 = deletedLineNumber / 3;
        return Math.max(MAX_DELAY - 50 * (standard1 + standard2), MIN_DELAY);
    }

    /**
     * 생성된 블럭 +1 하는 메서드
     */
    public void updateSpawnedBlockNumber() {
        spawnedBlockNumber++;
    }

    /**
     * 삭제된 라인 +1 하는 메서드
     */
    public void updateDeletedLineNumber() { deletedLineNumber++; }

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
     * 보너스 점수 = max(0, 11 - 10 * 걸린 초)
     * @return 보너스 점수
     */
    public int getBonusScore() {
        long placeTime = System.currentTimeMillis();
        double secondPer10s = (double)(placeTime - spawnTime) / 1000;
        int bonusScore = (int)(10.0 * secondPer10s);
        spawnTime = 0;
        return Math.max(0, 11 - bonusScore);
    }

    public static int getDeletedLineNumber() {
        return deletedLineNumber;
    }
    public int getScore() {
        return score;
    }
    public int getColorMode() {
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
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
    public void setColorMode(int colorMode) {
        this.colorMode = colorMode;
    }
}
