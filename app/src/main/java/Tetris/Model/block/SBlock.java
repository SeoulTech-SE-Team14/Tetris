package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

public class SBlock extends Block {
    public SBlock() {
        shape = new int[][] {
                {0, 2, 1},
                {1, 1, 0},
        };
        blockType = BlockType.NORMAL;
        itemXposition = new int[]{1, 2, 0, 1};
        itemYposition = new int[]{0, 0, 1, 1};
    }
    public SBlock(BlockType type) {
        shape = new int[][]{
                {0, 2, 1},
                {1, 1, 0},
        };
        blockType = type;
        itemXposition = new int[]{1, 2, 0, 1};
        itemYposition = new int[]{0, 0, 1, 1};
    }

    @Override
    public int getNumber() {
        return BlockNumber.SBLOCK.getBlockNumber();
    }
}