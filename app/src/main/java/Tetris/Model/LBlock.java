package Tetris.Model;

public class LBlock extends Block {

    public LBlock() {
        shape = new int[][] {
                {1, 2, 1},
                {1, 0, 0},
        };
    }
    @Override
    public int getNumber() {
        return 2;
    }
}