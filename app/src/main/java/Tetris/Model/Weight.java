package Tetris.Model;

public class Weight extends Block {
    boolean isOverlapped = false;
    public Weight() {
        shape = new int[][] {
                {0, 1, 1, 0},
                {1, 1, 1, 1}
        };

    }

    public void setOverlapped(boolean overlapped) {
        isOverlapped = overlapped;
    }
    public boolean isOverlapped() {
        return isOverlapped;
    }

    @Override
    public int getNumber() {
        return 7;
    }
}
