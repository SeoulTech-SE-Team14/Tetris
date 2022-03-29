package Tetris.Model;

public class ZBlock extends Block {

    public ZBlock() {
        shape = new int[][] {
                {1, 2, 0},
                {0, 1, 1},
        };
    }
    @Override
    public int getNumber() {
        return 6;
    }
}