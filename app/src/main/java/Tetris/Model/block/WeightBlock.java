package Tetris.Model.block;

public class WeightBlock extends Block {
    private boolean isOverlapped = false;
    public WeightBlock() {
        shape = new int[][] {
                {0, 1, 1, 0},
                {1, 1, 1, 1}
        };
    }
    public void setOverlapped(boolean overlapped) {
        isOverlapped = overlapped;
    }
    public boolean isOverlapped() { return isOverlapped; }

    @Override
    public int getNumber() {
        return 7;
    }
}
