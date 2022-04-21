package Tetris.Model.game;

import Tetris.Model.block.*;
import Tetris.Util.BlockType;
import Tetris.Util.GameType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    GameStateModel gameState;
    GameModel model;
    Block secondBlock;

    @BeforeEach
    void init() {
        gameState = new GameStateModel(GameType.BASIC_MODE, GameType.NORMAL);
        model = new GameModel(gameState, 20, 10);
    }
    @Test
    @DisplayName("일반 블럭이 내려갈 수 있는 경우")
    void moveDownTest1() {
        // given
        Block tempBlock = new IBlock();
        secondBlock = new TBlock();

        secondBlock.setX(3);
        secondBlock.setY(3);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

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

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(18, secondBlock.getY());
    }

    @Test
    @DisplayName("무게추 아이템이 움직일 때")
    void moveDownTest3() {
        Block tempBlock = new TBlock();
        secondBlock = new WeightBlock();

        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(6, secondBlock.getY());
    }

    @Test
    @DisplayName("무게추 아이템이 못 움직일 때")
    void moveDownTest4() {
        Block tempBlock = new TBlock();
        secondBlock = new WeightBlock();

        secondBlock.setX(3);
        secondBlock.setY(18);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(18, secondBlock.getY());
    }
    @Test
    @DisplayName("라인 삭제 아이템이 움직일 때")
    void moveDownTest5() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.LINE_DELETE);

        secondBlock.setX(3);
        secondBlock.setY(8);
        secondBlock.setItemIndex(0);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(9, secondBlock.getY());
    }
    @Test
    @DisplayName("클리어 아이템이 움직일 때")
    void moveDownTest6() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.CLEAR);

        secondBlock.setX(3);
        secondBlock.setY(8);
        secondBlock.setItemIndex(0);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(9, secondBlock.getY());
    }

    @Test
    @DisplayName("슬로우 아이템이 움직일 때")
    void moveDownTest7() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.SLOW);

        secondBlock.setX(3);
        secondBlock.setY(8);
        secondBlock.setItemIndex(0);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(9, secondBlock.getY());
    }
    @Test
    @DisplayName("부스트 아이템이 움직일 때")
    void moveDownTest8() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.BOOST);

        secondBlock.setX(3);
        secondBlock.setY(8);
        secondBlock.setItemIndex(0);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(9, secondBlock.getY());
    }
    @Test
    @DisplayName("라인 삭제 아이템이 못 움직일 때")
    void moveDownTest9() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.LINE_DELETE);

        secondBlock.setX(3);
        secondBlock.setY(18);
        secondBlock.setItemIndex(0);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(18, secondBlock.getY());
    }
    @Test
    @DisplayName("클리어 아이템이 못 움직일 때")
    void moveDownTest10() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.CLEAR);

        secondBlock.setX(3);
        secondBlock.setY(18);
        secondBlock.setItemIndex(0);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(18, secondBlock.getY());
    }

    @Test
    @DisplayName("슬로우 아이템이 못 움직일 때")
    void moveDownTest11() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.SLOW);

        secondBlock.setX(3);
        secondBlock.setY(18);
        secondBlock.setItemIndex(0);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(18, secondBlock.getY());
    }
    @Test
    @DisplayName("부스트 아이템이 못 움직일 때")
    void moveDownTest12() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.BOOST);

        secondBlock.setX(3);
        secondBlock.setY(18);
        secondBlock.setItemIndex(0);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveDown();

        //then
        assertEquals(18, secondBlock.getY());
    }

    @Test
    @DisplayName("무게추 블럭 오른쪽 움직이기- 겹쳤을 때")
    void moveRight() {
        Block tempBlock = new TBlock();
        WeightBlock weightBlock = new WeightBlock();
        weightBlock.setOverlapped(true);
        weightBlock.setX(3);
        weightBlock.setY(5);

        model.spawn(weightBlock);
        model.spawn(tempBlock);

        //when
        model.moveRight();

        //then
        assertEquals(3, weightBlock.getX());
    }
    @Test
    @DisplayName("무게추 블럭 오른쪽 움직이기- 안 겹쳤을 때")
    void moveRight2() {
        Block tempBlock = new TBlock();
        WeightBlock weightBlock = new WeightBlock();
        weightBlock.setOverlapped(false);
        weightBlock.setX(3);
        weightBlock.setY(5);

        model.spawn(weightBlock);
        model.spawn(tempBlock);

        //when
        model.moveRight();

        //then
        assertEquals(4, weightBlock.getX());
    }

    @Test
    @DisplayName("무게추 블럭 오른쪽 움직이기- 겹치려고 할 때")
    void moveRight3() {
        Block tempBlock = new TBlock();
        WeightBlock weightBlock = new WeightBlock();

        weightBlock.setX(3);
        weightBlock.setY(5);
        int[][] visited = new int[20][10];
        visited[6][5] = 1;
        model.setVisited(visited);
        model.spawn(weightBlock);
        model.spawn(tempBlock);

        //when
        model.moveRight();

        //then
        assertTrue(weightBlock.isOverlapped());
    }
    @Test
    @DisplayName("일반 블럭 오른쪽 움직이기- 겹쳤을 때")
    void moveRight4() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock();
        secondBlock.setX(17);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveRight();

        //then
        assertEquals(17, secondBlock.getX());
    }

    @Test
    @DisplayName("일반 블럭 오른쪽 움직이기- 안 겹쳤을 때")
    void moveRight5() {
        Block tempBlock = new TBlock();
        secondBlock = new WeightBlock();
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveRight();

        //then
        assertEquals(4, secondBlock.getX());
    }

    @Test
    @DisplayName("무게추 블럭 왼쪽 움직이기- 겹쳤을 때")
    void moveLeft() {
        Block tempBlock = new TBlock();
        WeightBlock weightBlock = new WeightBlock();
        weightBlock.setOverlapped(true);
        weightBlock.setX(3);
        weightBlock.setY(5);

        model.spawn(weightBlock);
        model.spawn(tempBlock);

        //when
        model.moveLeft();

        //then
        assertEquals(3, weightBlock.getX());
    }
    @Test
    @DisplayName("무게추 블럭 왼쪽 움직이기- 안 겹쳤을 때")
    void moveLeft2() {
        Block tempBlock = new TBlock();
        WeightBlock weightBlock = new WeightBlock();
        weightBlock.setOverlapped(false);
        weightBlock.setX(3);
        weightBlock.setY(5);

        model.spawn(weightBlock);
        model.spawn(tempBlock);

        //when
        model.moveLeft();

        //then
        assertEquals(2, weightBlock.getX());
    }

    @Test
    @DisplayName("무게추 블럭 왼쪽 움직이기- 겹치려고 할 때")
    void moveLeft3() {
        Block tempBlock = new TBlock();
        WeightBlock weightBlock = new WeightBlock();

        weightBlock.setX(3);
        weightBlock.setY(5);
        int[][] visited = new int[20][10];
        visited[2][5] = 1;
        model.setVisited(visited);
        model.spawn(weightBlock);
        model.spawn(tempBlock);

        //when
        model.moveLeft();

        //then
        assertTrue(weightBlock.isOverlapped());
    }
    @Test
    @DisplayName("일반 블럭 왼쪽 움직이기- 겹쳤을 때")
    void moveLeft4() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock();
        secondBlock.setX(0);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveLeft();

        //then
        assertEquals(0, secondBlock.getX());
    }

    @Test
    @DisplayName("일반 블럭 왼쪽 움직이기- 안 겹쳤을 때")
    void moveLeft5() {
        Block tempBlock = new TBlock();
        secondBlock = new WeightBlock();
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.moveLeft();

        //then
        assertEquals(2, secondBlock.getX());
    }

    @Test
    @DisplayName("무게추 블럭 떨어트리기")
    void fall1() {
        Block tempBlock = new TBlock();
        secondBlock = new WeightBlock();
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.fall();

        //then
        assertEquals(18, secondBlock.getY());
    }

    @Test
    @DisplayName("일반 블럭 떨어트리기")
    void fall2(){

        Block tempBlock = new TBlock();
        secondBlock = new TBlock();
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.fall();

        //then
        assertEquals(18, secondBlock.getY());

    }

    @Test
    @DisplayName("라인삭제 블럭 떨어트리기")
    void fall3() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.LINE_DELETE);
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.fall();

        //then
        assertEquals(18, secondBlock.getY());
    }

    @Test
    @DisplayName("슬로우 블럭 떨어트리기")
    void fall4(){
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.SLOW);
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.fall();

        //then
        assertEquals(18, secondBlock.getY());

    }
    @Test
    @DisplayName("부스트 블럭 떨어트리기")
    void fall5() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.BOOST);
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.fall();

        //then
        assertEquals(18, secondBlock.getY());
    }

    @Test
    @DisplayName("클리어 블럭 떨어트리기")
    void fall6(){
        Block tempBlock = new TBlock();
        secondBlock = new TBlock(BlockType.CLEAR);
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        //when
        model.fall();

        //then
        assertEquals(18, secondBlock.getY());

    }
    @Test
    @DisplayName("무게추 블럭 회전 테스트")
    void canRotate() {
        Block tempBlock = new TBlock();
        secondBlock = new WeightBlock();
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        assertFalse(model.canRotate());
    }
    @Test
    @DisplayName("일반 블럭 회전 테스트 - 가능할 때")
    void canRotate1() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock();
        secondBlock.setX(3);
        secondBlock.setY(5);

        model.spawn(secondBlock);
        model.spawn(tempBlock);

        assertTrue(model.canRotate());
    }
    @Test
    @DisplayName("일반 블럭 회전 테스트 - 아래 경계 걸려서 못 할때")
    void canRotate2() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock();
        secondBlock.setX(3);
        secondBlock.setY(19);
        model.spawn(secondBlock);
        model.spawn(tempBlock);

        assertFalse(model.canRotate());
    }
    @Test
    @DisplayName("일반 블럭 회전 테스트 - 양쪽 경계 걸려서 못 할때")
    void canRotate3() {
        Block tempBlock = new TBlock();
        secondBlock = new TBlock();
        secondBlock.setX(17);
        secondBlock.setY(3);
        model.spawn(secondBlock);
        model.spawn(tempBlock);

        assertFalse(model.canRotate());
    }
}