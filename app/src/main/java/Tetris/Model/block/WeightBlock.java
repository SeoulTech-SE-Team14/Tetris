package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

public class WeightBlock extends Block {
    private boolean isOverlapped = false;

    public WeightBlock() {
        shape = new int[][] {
                {0, 1, 1, 0},
                {1, 1, 1, 1}
        };
    }
    public WeightBlock(BlockType type) {
        shape = new int[][] {
                {0, 1, 1, 0},
                {1, 1, 1, 1}
        };
        blockType = type;

    }
    public void setOverlapped(boolean overlapped) {
        isOverlapped = overlapped;
    }
    public boolean isOverlapped() { return isOverlapped; }

    @Override
    public int getNumber() {
        return BlockNumber.WEIGHT_BLOCK.getBlockNumber();
    }
}
