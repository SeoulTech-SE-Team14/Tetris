package Tetris.Model;

public class OBlock extends Block {

    public OBlock() {
        shape = new int[][] {
                {1, 1},
                {1, 1}
        };
    }
    @Override
    public int getNumber() {
        return 3;
    }
}