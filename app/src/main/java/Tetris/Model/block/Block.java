package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

import java.util.Observable;

public class Block extends Observable {
    protected int x = 3;
    protected int y = 0;
    protected int cx = 1;   // centerX
    protected int cy = 0;   // centerY
    protected BlockType blockType = BlockType.NORMAL;

    protected int[] itemXposition = {0, 1, 0, 1};
    protected int[] itemYposition = {0, 0, 1, 1};
    protected int itemXpos;
    protected int itemYpos;
    protected int[][] shape = new int[][]{
            {1, 1},
            {1, 1}
    };

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
        return shape[y][x];
    }
    public int getNumber(){
        return BlockNumber.IBLOCK.getBlockNumber();
    }


    public int getItemXpos(){
        return itemXpos;
    }
    public int getItemYpos(){
        return itemYpos;
    }
    public BlockType getBlockType() {
        return blockType;
    }

    public void setItemIndex(int itemIndex) {
        itemXpos = itemXposition[itemIndex];
        itemYpos = itemYposition[itemIndex];

    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    // 블럭 중심 기준 회전 메서드
    public void rotate() {
        // Rotate the block 90 deg. clockwise.
        int height = shape.length;
        int width = shape[0].length;
        int dx = 0;
        int dy = 0;
        int[][] ret = new int[width][height];

        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                ret[col][height - 1 - row] = shape[row][col];
                if(row == itemYpos && col == itemXpos){
                    itemXpos = height - 1 - row;
                    itemYpos = col;
                }
                if(shape[row][col] == 2){
                    dx = height - 1 - row - col;
                    dy = col - row;
                    cy = col;
                    cx = height - 1 - row;
                }
            }
        }
        this.x -= dx;
        this.y -= dy;
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