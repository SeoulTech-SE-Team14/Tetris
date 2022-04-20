package Tetris.Util;

public enum BlockType {
    NORMAL("normal", 5), WEIGHT("weight", 0),
    LINE_DELETE("line_delete", 1), CLEAR("clear", 2),
    SLOW("slow", 3), BOOST("boost", 4);

    private String blockType;
    private int blockTypeNumber;

    BlockType(String blockType, int blockTypeNumber) {
        this.blockType = blockType;
        this.blockTypeNumber = blockTypeNumber;
    }

    public int getBlockTypeNumber() {
        return blockTypeNumber;
    }

    public String getBlockType() {
        return blockType;
    }
}
