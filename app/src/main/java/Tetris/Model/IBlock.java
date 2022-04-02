package Tetris.Model;

public class IBlock extends Block {

    public IBlock() {
        shape = new int[][] {
                {1, 2, 1, 1}
        };
    }
    @Override
    public int getNumber() {
        return 0;
    }
}