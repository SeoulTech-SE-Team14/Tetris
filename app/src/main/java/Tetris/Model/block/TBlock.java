package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

public class TBlock extends Block {
    public TBlock() {
        shape = new int[][] {
                {0, 1, 0},
                {1, 2, 1},
        };
        blockType = BlockType.NORMAL;
        itemXposition = new int[]{1, 0, 1, 2};
        itemYposition = new int[]{0, 1, 1, 1};
    }
    public TBlock(BlockType type) {
        shape = new int[][] {
                {0, 1, 0},
                {1, 2, 1},
        };
        blockType = type;
        itemXposition = new int[]{1, 0, 1, 2};
        itemYposition = new int[]{0, 1, 1, 1};
    }

    @Override
    public int getNumber() { return BlockNumber.TBLOCK.getBlockNumber(); }
}