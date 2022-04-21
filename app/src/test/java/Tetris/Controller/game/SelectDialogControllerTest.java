package Tetris.Controller.game;

import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Model.game.SelectDialogModel;
import Tetris.Util.GameType;
import Tetris.View.game.SelectDialog;
import Tetris.View.home.StartMenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class SelectDialogControllerTest {
    SelectDialog dialog;
    SelectDialogController controller;
    SelectDialogModel model;
    StartMenuView startMenuView;

    @BeforeEach
    void init() {
        model = new SelectDialogModel();
        model.setIndicator(0);
        GameStateModel gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        GameModel gameModel = new GameModel(gameStateModel, 20, 10);
        startMenuView = new StartMenuView(100, 100);
        dialog = new SelectDialog(startMenuView, 100, 100);
    }

    @Test
    void keyPressedTest1() {
        model.setIndicator(0);
        controller = new SelectDialogController(model, dialog, startMenuView);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        controller.keyPressed(key);
        assertEquals(1, SelectDialogModel.getIndicator());
    }

    @Test
    void keyPressedTest2() {
        model.setIndicator(0);
        controller = new SelectDialogController(model, dialog, startMenuView);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        controller.keyPressed(key);
        assertEquals(3, SelectDialogModel.getIndicator());
    }

    @Test
    void keyPressedTest3() {
        model.setIndicator(0);
        controller = new SelectDialogController(model, dialog, startMenuView);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(0, SelectDialogModel.getIndicator());
    }
    @Test
    void keyPressedTest4() {
        model.setIndicator(1);
        controller = new SelectDialogController(model, dialog, startMenuView);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(1, SelectDialogModel.getIndicator());
    }
    @Test
    void keyPressedTest5() {
        model.setIndicator(2);
        controller = new SelectDialogController(model, dialog, startMenuView);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(2, SelectDialogModel.getIndicator());
    }
}