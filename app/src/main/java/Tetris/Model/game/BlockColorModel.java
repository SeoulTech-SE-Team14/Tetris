package Tetris.Model.game;

import java.awt.*;
import java.util.Objects;

public class BlockColorModel {
    public BlockColorModel() { /*  */ }

    // 기본 컬러 모드
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
    // 색맹 컬러 모드
    private final Color[] blindColors = {
            Color.PINK,
            Color.MAGENTA,
            Color.YELLOW,
            Color.BLACK,
            Color.BLUE,
            Color.GREEN,
            Color.RED,
            Color.WHITE
    };

    public Color getColor(int blockNumber) {
        if(blockNumber > 7) blockNumber = 7;
        return basicColors[blockNumber];
    }
    public Color getColor(int blockNumber, String colorMode){
        if (Objects.equals(colorMode, "blind")) {
            return blindColors[blockNumber];
        }
        return basicColors[blockNumber];
    }
}
