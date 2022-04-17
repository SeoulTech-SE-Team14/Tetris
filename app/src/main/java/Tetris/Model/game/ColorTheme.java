package Tetris.Model.game;

import java.awt.*;
import java.util.Objects;

public class ColorTheme {
    public ColorTheme() { /*  */ }

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
            Color.WHITE
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
    public Color getColor(int blockNumber, String colorMode){
        if (Objects.equals(colorMode, "blind")) {
            return blindColors[blockNumber];
        }
        return basicColors[blockNumber];
    }
}
