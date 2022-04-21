package Tetris.Controller.game;

import Tetris.Model.game.PauseDialogModel;
import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Util.GameType;
import Tetris.View.game.PauseDialog;
import Tetris.View.game.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class PauseDialogControllerTest {
    PauseDialog dialog;
    PauseDialogController controller;
    PauseDialogModel model;
    GameView gameView;

    @BeforeEach
    void init() {
        model = new PauseDialogModel();
        model.setIndicator(0);
        GameStateModel gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        GameModel gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(100, 100, gameModel);
        dialog = new PauseDialog(gameView, 100, 100);
    }

    @Test
    void keyPressedTest1() {
        model.setIndicator(0);
        controller = new PauseDialogController(model, dialog, gameView, GameType.BASIC_MODE, GameType.NORMAL);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'a');
        controller.keyPressed(key);
        assertEquals(1, PauseDialogModel.getIndicator());
    }

    @Test
    void keyPressedTest2() {
        model.setIndicator(0);
        controller = new PauseDialogController(model, dialog, gameView, GameType.BASIC_MODE, GameType.NORMAL);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_UP, 'a');
        controller.keyPressed(key);
        assertEquals(2, PauseDialogModel.getIndicator());
    }
    @Test
    void keyPressedTest4() {
        model.setIndicator(1);
        controller = new PauseDialogController(model, dialog, gameView, GameType.BASIC_MODE, GameType.NORMAL);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(1, PauseDialogModel.getIndicator());
    }
    @Test
    void keyPressedTest5() {
        model.setIndicator(2);
        controller = new PauseDialogController(model, dialog, gameView, GameType.BASIC_MODE, GameType.NORMAL);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(2, PauseDialogModel.getIndicator());
    }
    @Test
    void keyPressedTest3() {
        model.setIndicator(0);
        controller = new PauseDialogController(model, dialog, gameView, GameType.BASIC_MODE, GameType.NORMAL);
        KeyEvent key = new KeyEvent(dialog, KeyEvent.KEY_PRESSED, System
                .currentTimeMillis(), 0, KeyEvent.VK_ENTER, 'a');
        controller.keyPressed(key);
        assertEquals(0, PauseDialogModel.getIndicator());
    }
}