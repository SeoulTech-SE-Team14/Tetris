package Tetris.Util;

public enum GameType {
    BASIC_MODE("basic_mode"), ITEM_MODE("item_mode"), EASY("easy"), NORMAL("normal"), HARD("hard");
    private String key;

    GameType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
