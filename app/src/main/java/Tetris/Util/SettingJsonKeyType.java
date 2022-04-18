package Tetris.Util;

public enum SettingJsonKeyType {
    DIFFICULTY("difficulty"), COLOR_MODE("color_mode"), RESOLUTION("resolution"),
    FONT_SIZE("font_size"), WIDTH("width"), HEIGHT("height"), KEY("key");

    private String key;

    SettingJsonKeyType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
