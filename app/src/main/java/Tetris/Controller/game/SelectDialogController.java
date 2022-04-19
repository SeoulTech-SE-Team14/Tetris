package Tetris.Controller.game;

import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Model.game.SelectDialogModel;
import Tetris.Util.GameType;
import Tetris.View.game.GameView;
import Tetris.View.game.SelectDialog;
import Tetris.View.home.StartMenuView;

import java.awt.event.*;

public class SelectDialogController extends WindowAdapter implements KeyListener, ActionListener {
    private SelectDialogModel model;
    private SelectDialog dialog;
    private StartMenuView startMenuView;

    public SelectDialogController(SelectDialogModel model, SelectDialog dialog, StartMenuView view) {
        this.model = model;
        this.dialog = dialog;
        this.startMenuView = view;
        this.dialog.setActionListener(this);
    }
    public void navigateGameView(GameType difficulty){
        GameStateModel gameState = new GameStateModel(GameType.BASIC_MODE, difficulty);
        GameModel field = new GameModel(gameState,20, 10);
        GameView view = new GameView(startMenuView.getLocation().x, startMenuView.getLocation().y, field);
        GameViewController controller = new GameViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        gameState.addObserver(view);
        startMenuView.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        super.windowClosed(e);
        dialog.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored.
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int indicator = SelectDialogModel.getIndicator();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                indicator++;
                if(indicator == model.getButtonCount()) indicator = 0;
                model.setIndicator(indicator);
                break;
            case KeyEvent.VK_UP:
                indicator--;
                if(indicator == -1) indicator = model.getButtonCount() - 1;
                model.setIndicator(indicator);
                break;
            case KeyEvent.VK_ENTER:
                switch (indicator){
                    case 0:
                        navigateGameView(GameType.EASY );
                        break;
                    case 1:
                        navigateGameView(GameType.NORMAL);
                        break;
                    case 2:
                        navigateGameView(GameType.HARD );
                        break;
                    case 3:
                        dialog.dispose();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // default implementation ignored.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String target = e.getSource().toString();
        System.out.println(target);
        if(target.contains("dialog_button_easy")) {
            navigateGameView(GameType.EASY);
        } else if(target.contains("dialog_button_normal")) {
            navigateGameView(GameType.NORMAL);
        } else if(target.contains("dialog_button_hard")) {
            navigateGameView(GameType.HARD);
        } else if(target.contains("dialog_button_back")) {
            dialog.dispose();
        }
    }
}
