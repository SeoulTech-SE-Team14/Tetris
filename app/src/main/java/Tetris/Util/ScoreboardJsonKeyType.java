package Tetris.Util;

public enum ScoreboardJsonKeyType {
    BASIC_MODE("basic_mode"), ITEM_MODE("item_mode"), NAME("name"), SCORE("score");

    private String key;

    ScoreboardJsonKeyType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
