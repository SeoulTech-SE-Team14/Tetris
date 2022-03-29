package Tetris.Model;

public class IBlock extends Block {

    public IBlock() {
        this.cx = 1;
        this.cy = 0;
        shape = new int[][] {
                {1, 2, 1, 1}
        };
    }
    @Override
    public int getNumber() {
        return 0;
    }
}