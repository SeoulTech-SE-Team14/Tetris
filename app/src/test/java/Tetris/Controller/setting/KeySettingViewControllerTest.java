package Tetris.Controller.setting;

import Tetris.Model.setting.KeySettingModel;
import Tetris.View.setting.KeySettingView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class KeySettingViewControllerTest {
    private KeySettingModel model;
    private KeySettingView keySettingView;
    private KeySettingViewController controller;
    @BeforeEach
    void init(){
        model = new KeySettingModel();
        keySettingView = new KeySettingView(100, 100);
        controller = new KeySettingViewController(model, keySettingView);
    }
    @Test
    @DisplayName("경고창 보여주기 창 선택 테스트")
    void keyPressedTest() {
        KeyEvent key = new KeyEvent(keySettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertTrue(true);
    }
    @Test
    @DisplayName("right 키 입력 포커스 선택 테스트")
    void keyPressedTest1() {
        KeyEvent key = new KeyEvent(keySettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_1, 'a');
        key.getComponent().setName("rightInput");
        controller.keyPressed(key);
        assertEquals(49,model.getRight());
    }
    @Test
    @DisplayName("left 키 입력 포커스 선택 테스트")
    void keyPressedTest2() {
        KeyEvent key = new KeyEvent(keySettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_2, 'a');
        key.getComponent().setName("leftInput");
        controller.keyPressed(key);
        assertEquals(50, model.getLeft());
    }
    @Test
    @DisplayName("down 키 입력 포커스 선택 테스트")
    void keyPressedTest3() {
        KeyEvent key = new KeyEvent(keySettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_3, 'a');
        key.getComponent().setName("downInput");
        controller.keyPressed(key);
        assertEquals(51, model.getDown());
    }
    @Test
    @DisplayName("fall 키 입력 포커스 선택 테스트")
    void keyPressedTest4() {
        KeyEvent key = new KeyEvent(keySettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_4, 'a');
        key.getComponent().setName("fallInput");
        controller.keyPressed(key);
        assertEquals(52, model.getFall());
    }
    @Test
    @DisplayName("rotate 키 입력 포커스 선택 테스트")
    void keyPressedTest5() {
        KeyEvent key = new KeyEvent(keySettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_5, 'a');
        key.getComponent().setName("rotateInput");
        controller.keyPressed(key);
        assertEquals(53, model.getRotate());
    }
    @Test
    @DisplayName("pause 키 입력 포커스 선택 테스트")
    void keyPressedTest6() {
        KeyEvent key = new KeyEvent(keySettingView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_6, 'a');
        key.getComponent().setName("pauseInput");
        controller.keyPressed(key);
        assertEquals(54, model.getPause());
    }
}