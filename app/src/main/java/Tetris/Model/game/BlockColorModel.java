package Tetris.Model.game;

import Tetris.Util.ColorBlindnessType;

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
    // 색맹 컬러 모드(적,녹색맹)
    private final Color[] blindColors = {
            Color.CYAN,
            Color.BLUE,
            Color.ORANGE,
            Color.YELLOW,
            new Color(46, 166, 122),
            Color.MAGENTA,
            new Color(217, 82, 4),
            Color.WHITE
    };
    // 색맹 컬러 모드(청색맹)
    private final Color[] blind2Colors = {
            Color.CYAN,
            Color.BLUE,
            Color.ORANGE,
            Color.YELLOW,
            new Color(46, 166, 122),
            Color.MAGENTA,   
            Color.RED,
            Color.WHITE
    };

    public Color getColor(int blockNumber) {
        if(blockNumber > 7) blockNumber = 7;
        return basicColors[blockNumber];
    }
    public Color getColor(int blockNumber, String colorMode){
        if(blockNumber > 7) blockNumber = 7;
       switch (colorMode) {
           case "deutan": case "protan":
               return blindColors[blockNumber];
           case "tritan":
               return blind2Colors[blockNumber];
           default:
               return getColor(blockNumber);
       }
    }
}
