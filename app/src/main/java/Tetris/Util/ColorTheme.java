package Tetris.Util;

import java.awt.*;

public class ColorTheme {
    public ColorTheme() { }
    private Color[] basicColors = {
            Color.CYAN,
            Color.BLUE,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
    };
    private Color[] blindColors = {
            Color.PINK,
            Color.MAGENTA,
            Color.YELLOW,
            Color.BLACK,
            Color.BLUE,
            Color.GREEN,
            Color.RED
    };
    public Color getColor(int num) {
        return basicColors[num];
    }
    public Color getColor(int num, int mode){
        if (mode == 1) {
            return blindColors[num];
        }
        return basicColors[num];
    }
}
