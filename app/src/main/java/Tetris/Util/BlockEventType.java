package Tetris.Util;

public enum BlockEventType {
    RIGHT("right"), LEFT("left"), DOWN("down"), FALL("fall"), ROTATE("rotate"), PAUSE("pause");

    private String key;

    BlockEventType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
