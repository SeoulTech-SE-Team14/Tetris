package Tetris.Controller.home;

import Tetris.Model.home.StartMenuModel;
import Tetris.View.home.StartMenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class StartMenuViewControllerTest {
    StartMenuView startMenuView;
    StartMenuViewController controller;
    StartMenuModel model;

    @BeforeEach
    void init() {
        model = new StartMenuModel();
        model.setIndicator(0);
        startMenuView = new StartMenuView(100, 100);
        controller = new StartMenuViewController(model, startMenuView);
    }

    @Test
    @DisplayName("아래 키 테스트")
    void keyPressedTest1() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(startMenuView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        controller.keyPressed(key);
        assertEquals(1, model.getIndicator());
    }

    @Test
    @DisplayName("위 키 테스트")
    void keyPressedTest2() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(startMenuView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        controller.keyPressed(key);
        assertEquals(4, model.getIndicator());
    }
    @Test
    @DisplayName("난이도 화면 보여주기 창 선택 테스트")
    void keyPressedTest3() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(startMenuView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(0, model.getIndicator());
    }
    @Test
    @DisplayName("아이템 모드 선택 테스트")
    void keyPressedTest4() {
        model.setIndicator(1);
        KeyEvent key = new KeyEvent(startMenuView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(1, model.getIndicator());
    }
    @Test
    @DisplayName("설정 화면 선택 테스트")
    void keyPressedTest5() {
        model.setIndicator(2);
        KeyEvent key = new KeyEvent(startMenuView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(2, model.getIndicator());
    }
    @Test
    @DisplayName("스코어보드 화면 선택 테스트")
    void keyPressedTest6() {
        model.setIndicator(3);
        KeyEvent key = new KeyEvent(startMenuView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(3, model.getIndicator());
    }
}