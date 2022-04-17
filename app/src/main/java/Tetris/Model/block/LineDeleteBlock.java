package Tetris.Model.block;

import java.util.Random;

public class LineDeleteBlock extends Block {
    private int itemPositionIndex = 0;
    private boolean centerCordOverlappedItemCord = false;
    private int[] itemCordX = {1, 0, 1, 2};
    private int[] itemCordY = {0, 1, 1, 1};

    public LineDeleteBlock() {
        shape = new int[][] {
                {0, 2, 0},
                {1, 1, 1},
        };
        Random rnd = new Random(System.nanoTime());
        itemPositionIndex = rnd.nextInt(4);
        int itemX = itemCordX[itemPositionIndex];
        int itemY = itemCordY[itemPositionIndex];
        // 3은 아이템 위치!
        shape[itemY][itemX] = 3;
        if(itemX == 1 && itemY == 0) centerCordOverlappedItemCord = true;
    }

    @Override
    public int getNumber() { return 8; }

    @Override
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
                if(shape[row][col] == 2 || (shape[row][col] == 3 && centerCordOverlappedItemCord)){
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

    public boolean isCenterCordOverlappedItemCord() {
        return centerCordOverlappedItemCord;
    }
    public int getItemPositionX() {
        return itemCordX[itemPositionIndex];
    }
    public int getItemPositionY() {
        return itemCordY[itemPositionIndex];
    }
}
