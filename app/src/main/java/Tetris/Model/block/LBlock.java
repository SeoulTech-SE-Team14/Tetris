package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

public class LBlock extends Block {
    public LBlock() {
        shape = new int[][] {
                {1, 2, 1},
                {1, 0, 0},
        };
        blockType = BlockType.NORMAL;
        itemXposition = new int[]{0, 1, 2, 0};
        itemYposition = new int[]{0, 0, 0, 1};
    }
    public LBlock(BlockType type) {
        shape = new int[][] {
                {1, 2, 1},
                {1, 0, 0},
        };
        blockType = type;
        itemXposition = new int[]{0, 1, 2, 0};
        itemYposition = new int[]{0, 0, 0, 1};
    }

    @Override
    public int getNumber() {
        return BlockNumber.LBLOCK.getBlockNumber();
    }
}