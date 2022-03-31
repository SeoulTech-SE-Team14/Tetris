package Tetris.Util;

import java.awt.*;

public class ColorTheme {
    public ColorTheme() { }

    /**
     * 기본모드 컬러 테마
     */
    private final Color[] basicColors = {
            Color.CYAN,
            Color.BLUE,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
    };

    private final Color[] blindColors = {
            Color.PINK,
            Color.MAGENTA,
            Color.YELLOW,
            Color.BLACK,
            Color.BLUE,
            Color.GREEN,
            Color.RED
    };
    public Color getColor(int blockNumber) {
        return basicColors[blockNumber];
    }
    public Color getColor(int blockNumber, int colorMode){
        if (colorMode == 1) {
            return blindColors[blockNumber];
        }
        return basicColors[blockNumber];
    }
}
