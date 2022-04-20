package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

public class OBlock extends Block {
    public OBlock() {
        shape = new int[][] {
                {1, 1},
                {1, 1}
        };
        blockType = BlockType.NORMAL;
        itemXposition = new int[]{0, 1, 0, 1};
        itemYposition = new int[]{0, 0, 1, 1};
    }
    public OBlock(BlockType type) {
        shape = new int[][] {
                {1, 1},
                {1, 1}
        };
        blockType = type;
        itemXposition = new int[]{0, 1, 0, 1};
        itemYposition = new int[]{0, 0, 1, 1};
    }

    @Override
    public int getNumber() {
        return BlockNumber.OBLOCK.getBlockNumber();
    }
}