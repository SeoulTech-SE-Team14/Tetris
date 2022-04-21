package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

public class JBlock extends Block {
    public JBlock() {
        shape = new int[][] {
                {1, 2, 1},
                {0, 0, 1},
        };
        blockType = BlockType.NORMAL;
        itemXposition = new int[]{0, 1, 2, 2};
        itemYposition = new int[]{0, 0, 0, 1};

    }
    public JBlock(BlockType type) {
        shape = new int[][] {
                {1, 2, 1},
                {0, 0, 1},
        };
        blockType = type;
        itemXposition = new int[]{0, 1, 2, 2};
        itemYposition = new int[]{0, 0, 0, 1};

    }

    @Override
    public int getNumber() {
        return BlockNumber.JBLOCK.getBlockNumber();
    }
}