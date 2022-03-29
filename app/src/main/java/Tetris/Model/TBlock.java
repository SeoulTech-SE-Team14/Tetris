package Tetris.Model;

public class TBlock extends Block {

    public TBlock() {
        shape = new int[][] {
                {0, 2, 0},
                {1, 1, 1},
        };
    }
    @Override
    public int getNumber() { return 5; }
}