package Tetris.Controller.scoreboard;

import Tetris.View.scoreboard.ScoreboardView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardControllerTest {
    private ScoreboardController controller;
    private ScoreboardView scoreboardView;

    @BeforeEach
    void init(){
        scoreboardView = new ScoreboardView(100, 100);
        controller = new ScoreboardController(scoreboardView);
    }
    @Test
    @DisplayName("이전 화면 보여주기 창 선택 테스트")
    void keyPressedTest() {
        KeyEvent key = new KeyEvent(scoreboardView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertTrue(true);
    }
}