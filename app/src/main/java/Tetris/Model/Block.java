package Tetris.Model;

import java.util.Observable;

public abstract class Block extends Observable {
    protected int x;
    protected int y;
    protected int cx;   // centerX
    protected int cy;   // centerY
    protected int[][] shape;

    protected Block() {
        this.x = 3;
        this.y = 0;
        this.cx = 1;
        this.cy = 0;
        // 0: 빈칸 , 1: 블럭, 2: 블럭 중심
        shape = new int[][]{
                {1, 1},
                {1, 1}
        };
    }

    /**
     * Getter Method
     */
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getCx() {
        return cx;
    }
    public int getCy() {
        return cy;
    }
    public int getShape(int x, int y) {
        if(shape[y][x] == 0) return -1;
        return getNumber();
    }
    public int getNumber(){
        return 0;
    }

    /**
     * 블럭 중심 기준 회전 메서드
     */
    public void rotate() {
        // Rotate the block 90 deg. clockwise.
        int length = shape.length;
        int width = shape[0].length;
        int[][] ret = new int[width][length];
        for(int row = 0; row < length; row++){
            for(int col = 0; col < width; col++){
                ret[col][length - 1 - row] = shape[row][col];
            }
        }
        shape = ret;
    }
    public int height() {
        return shape.length;
    }
    public int width() {
        if(shape.length > 0)
            return shape[0].length;
        return 0;
    }
}