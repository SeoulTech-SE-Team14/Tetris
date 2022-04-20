package Tetris.Model.game;

import Tetris.Util.GameType;

import java.util.Observable;

/**
 * 플레이어 현재 정보 클래스
 * @author 김영균
 */
public class Player {
    private String name;
    private int score;
    private GameType mode;
    private GameType difficulty;
    public Player() {
        this.name = "default#user#name";
    }

    public Player(String name, int score, GameType mode, GameType difficulty) {
        this.name = name;
        this.score = score;
        this.mode = mode;
        this.difficulty = difficulty;
    }

    public Player(String name, int score, GameType mode) {
        this.name = name;
        this.score = score;
        this.mode = mode;
        this.difficulty = GameType.NORMAL;
    }

    public int getScore() {
        return score;
    }

    public GameType getMode() {
        return mode;
    }

    public GameType getDifficulty() {
        return difficulty;
    }

    public String getName()
    {
        return name;
    }

}