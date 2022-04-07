package Tetris.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    int x = 7;
    int y = 9;
    int cx = 1;
    int cy = 1;
    int[][] shape = new int[][] {
            {1, 0},
            {1, 2},
            {1, 0}
    };
    @Test
    void rotate() {
        int[][] expected = new int[][] {
                {1, 1, 1},{0, 2, 0}
        };
        int expected_center_x = 1;
        int expected_center_y = 1;
        int expected_x = 7;
        int expected_y = 9;
        int height = shape.length;
        int width = shape[0].length;
        int dx = 0;
        int dy = 0;
        int[][] ret = new int[width][height];
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                ret[col][height - 1 - row] = shape[row][col];
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
        assertArrayEquals(expected, shape);
        assertEquals(expected_x, x);
        assertEquals(expected_y, y);
        assertEquals(expected_center_x, cx);
        assertEquals(expected_center_y, cy);
    }
}