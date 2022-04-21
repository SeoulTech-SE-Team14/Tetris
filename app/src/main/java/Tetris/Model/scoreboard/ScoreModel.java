package Tetris.Model.scoreboard;

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

    // 내림차순
    @Override
    public int compareTo(ScoreModel o) {
        return Integer.compare(o.score,score);
    }
}
