package Tetris.Util;

public enum BlockNumber {
    IBLOCK(0), JBLOCK(1), LBLOCK(2), OBLOCK(3), SBLOCK(4), TBLOCK(5), ZBLOCK(6), WEIGHT_BLOCK(7), LINE_DELETE_BLOCK(8);

    private final int blockNumber;

    BlockNumber(int number) {
        this.blockNumber = number;
    }

    public int getBlockNumber() {
        return blockNumber;
    }
}
