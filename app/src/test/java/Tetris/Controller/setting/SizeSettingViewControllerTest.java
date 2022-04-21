package Tetris.Controller.setting;

import Tetris.Model.setting.SizeSettingModel;
import Tetris.View.setting.SizeSettingView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class SizeSettingViewControllerTest {
    private SizeSettingModel model;
    private SizeSettingView sizeSettingView;
    private SizeSettingViewController controller;

    @BeforeEach
    void init() {
        model = new SizeSettingModel();
        sizeSettingView = new SizeSettingView(100, 100);
        controller = new SizeSettingViewController(model, sizeSettingView);
    }
    @Test
    @DisplayName("아래 키 테스트")
    void keyPressedTest1() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(sizeSettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        controller.keyPressed(key);
        assertEquals(1, model.getIndicator());
    }

    @Test
    @DisplayName("위 키 테스트")
    void keyPressedTest2() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(sizeSettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        controller.keyPressed(key);
        assertEquals(3, model.getIndicator());
    }
    @Test
    @DisplayName("300X600 화면 바꾸기 선택 테스트")
    void keyPressedTest3() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(sizeSettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(0, model.getIndicator());
    }
    @Test
    @DisplayName("350X700 화면 바꾸기 선택 테스트")
    void keyPressedTest4() {
        model.setIndicator(1);
        KeyEvent key = new KeyEvent(sizeSettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(1, model.getIndicator());
    }
    @Test
    @DisplayName("400X800 화면 바꾸기 선택 테스트")
    void keyPressedTest5() {
        model.setIndicator(2);
        KeyEvent key = new KeyEvent(sizeSettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(2, model.getIndicator());
    }
//    @Test
//    @DisplayName("이전 화면 선택 테스트")
//    void keyPressedTest6() {
//        model.setIndicator(3);
//        KeyEvent key = new KeyEvent(sizeSettingView, KeyEvent.KEY_PRESSED, System
//                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
//        controller.keyPressed(key);
//        assertEquals(3, model.getIndicator());
//    }
}