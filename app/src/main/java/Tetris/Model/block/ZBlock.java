package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

public class ZBlock extends Block {
    public ZBlock() {
        shape = new int[][] {
                {1, 2, 0},
                {0, 1, 1},
        };
        blockType = BlockType.NORMAL;
        itemXposition = new int[]{0, 1, 1, 2};
        itemYposition = new int[]{0, 0, 1, 1};
    }

    public ZBlock(BlockType type) {
        shape = new int[][] {
                {1, 2, 0},
                {0, 1, 1},
        };
        blockType = type;
        itemXposition = new int[]{0, 1, 1, 2};
        itemYposition = new int[]{0, 0, 1, 1};
    }

    @Override
    public int getNumber() {
        return BlockNumber.ZBLOCK.getBlockNumber();
    }
}