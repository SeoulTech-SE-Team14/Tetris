package Tetris.Util;

public enum ColorBlindnessType {
    NORMAL("normal"), PROTAN("protan"), DEUTAN("deutan"), TRITAN("tritan");  // 정상, 적색맹, 녹색맹, 청색맹
    private String key;

    ColorBlindnessType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
