package Tetris.Model.block;

import Tetris.Util.BlockType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    Block block;

    @Test
    @DisplayName("아이템 돌리기")
    void itemRotateTest() {
        block = new LBlock(BlockType.LINE_DELETE);
        block.setX(5);
        block.setY(5);
        block.setItemIndex(3);
        block.rotate();
        block.rotate();
        assertEquals(2, block.getItemXpos());
        assertEquals(0, block.getItemYpos());
    }
    @Test
    @DisplayName("일반 블럭 돌리기")
    void blockRotateTest() {
        block = new LBlock();
        block.setX(5);
        block.setY(5);
        block.rotate();
        assertEquals(5, block.getX());
        assertEquals(4, block.getY());
        assertEquals(1, block.getCx());
        assertEquals(1, block.getCy());
    }
}