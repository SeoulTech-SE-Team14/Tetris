package Tetris.Controller.setting;

import Tetris.Model.setting.ColorBlindnessModel;
import Tetris.View.setting.ColorBlindnessView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class ColorBlindnessViewControllerTest {
    private ColorBlindnessModel model;
    private ColorBlindnessView colorBlindnessView;
    private ColorBlindnessViewController controller;

    @BeforeEach
    void init() {
        model = new ColorBlindnessModel();
        model.setIndicator(0);
        colorBlindnessView = new ColorBlindnessView(100, 100);
        controller = new ColorBlindnessViewController(model, colorBlindnessView);
    }


    @Test
    @DisplayName("아래 키 테스트")
    void keyPressedTest1() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(colorBlindnessView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        controller.keyPressed(key);
        assertEquals(1, model.getIndicator());
    }

    @Test
    @DisplayName("위 키 테스트")
    void keyPressedTest2() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(colorBlindnessView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        controller.keyPressed(key);
        assertEquals(4, model.getIndicator());
    }
    @Test
    @DisplayName("일반 색맹모드 선택 테스트")
    void keyPressedTest3() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(colorBlindnessView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(0, model.getIndicator());
    }
//    @Test
//    @DisplayName("적색맹 모드 선택 테스트")
//    void keyPressedTest4() {
//        model.setIndicator(1);
//        KeyEvent key = new KeyEvent(colorBlindnessView, KeyEvent.KEY_PRESSED, System
//                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
//        controller.keyPressed(key);
//        assertEquals(1, model.getIndicator());
//    }
//    @Test
//    @DisplayName("녹색맹 화면 선택 테스트")
//    void keyPressedTest5() {
//        model.setIndicator(2);
//        KeyEvent key = new KeyEvent(colorBlindnessView, KeyEvent.KEY_PRESSED, System
//                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
//        controller.keyPressed(key);
//        assertEquals(2, model.getIndicator());
//    }
//    @Test
//    @DisplayName("청색맹 화면 선택 테스트")
//    void keyPressedTest6() {
//        model.setIndicator(3);
//        KeyEvent key = new KeyEvent(colorBlindnessView, KeyEvent.KEY_PRESSED, System
//                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
//        controller.keyPressed(key);
//        assertEquals(3, model.getIndicator());
//    }
//    @Test
//    @DisplayName("이전화면 화면 선택 테스트")
//    void keyPressedTest7() {
//        model.setIndicator(4);
//        KeyEvent key = new KeyEvent(colorBlindnessView, KeyEvent.KEY_PRESSED, System
//                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
//        controller.keyPressed(key);
//        assertEquals(4, model.getIndicator());
//    }
}