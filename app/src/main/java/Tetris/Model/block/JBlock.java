package Tetris.Model.block;

public class JBlock extends Block {

    public JBlock() {
        shape = new int[][] {
                {1, 2, 1},
                {0, 0, 1},
        };
    }
    @Override
    public int getNumber() {
        return 1;
    }
}