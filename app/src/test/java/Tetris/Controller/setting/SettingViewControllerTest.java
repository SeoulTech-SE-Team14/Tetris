package Tetris.Controller.setting;

import Tetris.Model.setting.SettingModel;
import Tetris.View.setting.SettingView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class SettingViewControllerTest {
    private SettingModel model;
    private SettingView settingView;
    private SettingViewController controller;
    @BeforeEach
    void init() {
        model = new SettingModel();
        model.setIndicator(0);
        settingView = new SettingView(100, 100);
        controller = new SettingViewController(model, settingView);
    }
    @Test
    @DisplayName("아래 키 테스트")
    void keyPressedTest1() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(settingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        controller.keyPressed(key);
        assertEquals(1, model.getIndicator());
    }

    @Test
    @DisplayName("위 키 테스트")
    void keyPressedTest2() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(settingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        controller.keyPressed(key);
        assertEquals(5, model.getIndicator());
    }
    @Test
    @DisplayName("사이즈설정 화면 선택 테스트")
    void keyPressedTest3() {
        model.setIndicator(0);
        KeyEvent key = new KeyEvent(settingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(0, model.getIndicator());
    }
    @Test
    @DisplayName("색맹모드 설정 화면 선택 테스트")
    void keyPressedTest4() {
        model.setIndicator(1);
        KeyEvent key = new KeyEvent(settingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(1, model.getIndicator());
    }
    @Test
    @DisplayName("이전 화면 선택 테스트")
    void keyPressedTest5() {
        model.setIndicator(5);
        KeyEvent key = new KeyEvent(settingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(5, model.getIndicator());
    }
//    @Test
//    @DisplayName("세팅 초기화 선택 테스트")
//    void keyPressedTest5() {
//        model.setIndicator(2);
//        KeyEvent key = new KeyEvent(settingView, KeyEvent.KEY_PRESSED, System
//                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
//        controller.keyPressed(key);
//        assertEquals(2, model.getIndicator());
//    }
//    @Test
//    @DisplayName("스코어보드 화면 선택 테스트")
//    void keyPressedTest6() {
//        model.setIndicator(3);
//        KeyEvent key = new KeyEvent(settingView, KeyEvent.KEY_PRESSED, System
//                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
//        controller.keyPressed(key);
//        assertEquals(3, model.getIndicator());
//    }
}