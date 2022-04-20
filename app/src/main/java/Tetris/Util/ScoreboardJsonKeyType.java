package Tetris.Util;

public enum ScoreboardJsonKeyType {
    NAME("name"), SCORE("score"), DIFFICULTY("difficulty");

    private String key;

    ScoreboardJsonKeyType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
