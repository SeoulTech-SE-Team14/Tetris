package Tetris.Model.scoreboard;

import Tetris.Util.ScoreboardJsonKeyType;

public class ScoreModel implements Comparable<ScoreModel>{
    private int score;
    private String name;
    private String difficulty;

    public ScoreModel(int score, String name) {
        this.score = score;
        this.name = name;
        this.difficulty = "normal";
    }
    public ScoreModel(int score, String name, String difficulty) {
        this.score = score;
        this.name = name;
        this.difficulty = difficulty;
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public int compareTo(ScoreModel o) {
        return Integer.compare(score, o.score);
    }
}
