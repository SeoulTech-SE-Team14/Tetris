package Tetris.Model.scoreboard;

public class ScoreModel implements Comparable<ScoreModel>{
    private int score;
    private String name;

    public ScoreModel(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(ScoreModel o) {
        return Integer.compare(score, o.score);
    }
}
