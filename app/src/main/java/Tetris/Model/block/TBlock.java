package Tetris.Model.block;

public class TBlock extends Block {

    public TBlock() {
        shape = new int[][] {
                {0, 1, 0},
                {1, 2, 1},
        };
    }
    @Override
    public int getNumber() { return 5; }
}