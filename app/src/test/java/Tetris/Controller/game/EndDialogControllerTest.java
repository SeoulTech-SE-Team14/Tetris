package Tetris.Controller.game;

import Tetris.Model.game.EndDialogModel;
import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Util.GameType;
import Tetris.View.game.EndDialog;
import Tetris.View.game.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class EndDialogControllerTest {
    private EndDialogModel model;
    private EndDialog dialog;
    private EndDialogController endDialogController;
    private GameView gameView;

    @BeforeEach
    void init() {
        model = new EndDialogModel();
        model.setIndicator(0);
        GameStateModel gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        GameModel gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(100, 100, gameModel);
        dialog = new EndDialog(gameView, gameModel, 100, 100);
    }

    @Test
    void keyPressedTest1() {
        endDialogController = new EndDialogController(model, dialog, gameView);
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        endDialogController.keyPressed(key);
        assertEquals(1, EndDialogModel.getIndicator());
    }

    @Test
    void keyPressedTest2() {
        endDialogController = new EndDialogController(model, dialog, gameView);
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        endDialogController.keyPressed(key);
        assertEquals(1, EndDialogModel.getIndicator());
    }

    @Test
    void keyPressedTest3() {
        model.setIndicator(0);
        endDialogController = new EndDialogController(model, dialog, gameView);
        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        endDialogController.keyPressed(key);
        assertEquals(0, EndDialogModel.getIndicator());
    }
//    @Test
//    void keyPressedTest4() {
//        model.setIndicator(1);
//        endDialogController = new EndDialogController(model, dialog, gameView);
//        KeyEvent key = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System
//                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
//        endDialogController.keyPressed(key);
//        assertEquals(1, EndDialogModel.getIndicator());
//    }
    @Test
    void actionPerformed() throws AWTException {
    }
}