package Tetris.Model.block;

public class SBlock extends Block {

    public SBlock() {
        shape = new int[][] {
                {0, 2, 1},
                {1, 1, 0},
        };
    }
    @Override
    public int getNumber() {
        return 4;
    }
}