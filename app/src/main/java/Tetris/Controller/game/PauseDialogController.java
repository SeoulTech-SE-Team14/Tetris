package Tetris.Controller.game;

import Tetris.Controller.home.StartMenuViewController;
import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Model.game.PauseDialogModel;
import Tetris.Model.home.StartMenuModel;
import Tetris.Util.GameType;
import Tetris.View.game.GameView;
import Tetris.View.game.PauseDialog;
import Tetris.View.home.StartMenuView;

import java.awt.event.*;

public class PauseDialogController extends WindowAdapter implements KeyListener, ActionListener {
    private PauseDialogModel model;
    private PauseDialog dialog;
    private GameView gameView;
    private GameType type;
    private GameType difficulty;

    public PauseDialogController(PauseDialogModel model, PauseDialog dialog, GameView view, GameType type, GameType difficulty) {
        this.model = model;
        this.dialog = dialog;
        this.gameView = view;
        this.type = type;
        this.difficulty = difficulty;
        this.dialog.setActionListener(this);
    }
    public void restartGame(){
        GameStateModel restartGameStateModel = new GameStateModel(type, difficulty);
        GameModel restartGameModel = new GameModel(restartGameStateModel,20, 10);
        GameView restartGameView = new GameView(gameView.getLocation().x, gameView.getLocation().y, restartGameModel);
        GameViewController restartGameViewController = new GameViewController(restartGameModel, restartGameView);
        restartGameView.addKeyListener(restartGameViewController);
        restartGameModel.addObserver(restartGameView);
        restartGameStateModel.addObserver(restartGameView);
        restartGameView.setVisible(true);
        gameView.dispose();
        dialog.dispose();
    }
    public void navigateMenuView(){
        StartMenuModel field = new StartMenuModel();
        StartMenuView startView = new StartMenuView(gameView.getLocation().x, gameView.getLocation().y);
        StartMenuViewController controller = new StartMenuViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
        startView.setVisible(true);
        gameView.dispose();
        dialog.dispose();

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
        int indicator = PauseDialogModel.getIndicator();
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
                        dialog.dispose();
                        break;
                    case 1:
                        restartGame();
                        break;
                    case 2:
                        navigateMenuView();
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
        if(target.contains("dialog_button_resume")) {
            restartGame();
        } else if(target.contains("dialog_button_restart")) {
            dialog.dispose();
        } else if(target.contains("dialog_button_back_menu")) {
            navigateMenuView();
        }
    }
}
