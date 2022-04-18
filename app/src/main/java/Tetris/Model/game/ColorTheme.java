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
            Color.CYAN,
            Color.BLUE,
            Color.ORANGE,
            Color.YELLOW,
            getColor(0x009E73),
            //연두 대신 Bluish Green(청록), 적녹/청황 둘다에서 바꿔줄 색
            Color.MAGENTA,   
            getColor(0xD55E00)
            //빨강 대신 Vermillion(다홍), 적녹에서만 바꿔줄 색
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
