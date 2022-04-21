package Tetris.Model.block;

import Tetris.Util.BlockNumber;
import Tetris.Util.BlockType;

public class IBlock extends Block {
    public IBlock() {
        shape = new int[][]{
                {1, 2, 1, 1}
        };
        blockType = BlockType.NORMAL;
        itemXposition = new int[]{0, 1, 2, 3};
        itemYposition = new int[]{0, 0, 0, 0};
    }
    public IBlock(BlockType type) {
        shape = new int[][]{
                {1, 2, 1, 1}
        };
        blockType = type;
        itemXposition = new int[]{0, 1, 2, 3};
        itemYposition = new int[]{0, 0, 0, 0};
    }

    @Override
    public int getNumber() {
        return BlockNumber.IBLOCK.getBlockNumber();
    }

}