package Tetris.Model.game;

import Tetris.Model.block.Block;
import Tetris.Model.block.TBlock;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    /**
     * 000000
     * 000000
     * 000000
     * 000000
     * 000000
     * 000000
     */
    GameState gameState = new GameState("normal");

    GameBoard board = new GameBoard(gameState, 20, 10);
    @Before
    void init() {
        Block block = new TBlock();
        block.setX(3);
        block.setY(3);

    }
    @Test
    void moveDown() {

    }
    @Test
    void moveRight() {
    }

    @Test
    void moveLeft() {
    }

    @Test
    void fall() {
    }

    @Test
    void canRotate() {
    }
}