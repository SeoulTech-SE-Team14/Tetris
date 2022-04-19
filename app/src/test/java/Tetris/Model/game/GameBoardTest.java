package Tetris.Model.game;

import Tetris.Model.block.*;
import Tetris.Util.GameType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    GameStateModel gameState;
    GameModel board;
    Block secondBlock;

    @BeforeEach
    void init() {
        gameState = new GameStateModel(GameType.BASIC_MODE, GameType.NORMAL);
        board = new GameModel(gameState, 20, 10);
    }

    @Test
    @DisplayName("일반 블럭이 내려갈 수 있는 경우")
    void moveDownTest1() {
        // given
        Block tempBlock = new IBlock();
        secondBlock = new TBlock();

        secondBlock.setX(3);
        secondBlock.setY(3);

        board.spawn(secondBlock);
        board.spawn(tempBlock);

        //when
        board.moveDown();

        //then
        assertEquals(4, secondBlock.getY());
    }
    @Test
    @DisplayName("일반 블럭이 내려갈 수 없는 경우")
    void moveDownTest2() {
        // given
        Block tempBlock = new IBlock();
        secondBlock = new TBlock();

        secondBlock.setX(3);
        secondBlock.setY(18);

        board.spawn(secondBlock);
        board.spawn(tempBlock);

        //when
        board.moveDown();

        //then
        assertEquals(18, secondBlock.getY());
    }
    @Test
    @DisplayName("무게추 아이템이 움직일 때")
    void moveDownTest3() {

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