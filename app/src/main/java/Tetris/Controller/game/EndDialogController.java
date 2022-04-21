package Tetris.Controller.game;

import Tetris.Controller.home.StartMenuViewController;
import Tetris.Model.game.EndDialogModel;
import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Model.game.PauseDialogModel;
import Tetris.Model.home.StartMenuModel;
import Tetris.Util.GameType;
import Tetris.View.game.EndDialog;
import Tetris.View.game.GameView;
import Tetris.View.game.PauseDialog;
import Tetris.View.home.StartMenuView;

import java.awt.event.*;

public class EndDialogController extends WindowAdapter implements KeyListener, ActionListener {
    private EndDialogModel model;
    private EndDialog dialog;
    private GameView gameView;

    public EndDialogController(EndDialogModel model, EndDialog dialog, GameView gameView) {
        this.model = model;
        this.dialog = dialog;
        this.gameView = gameView;
        this.dialog.setActionListener(this);
    }
    public void navigateMenuView(){
        StartMenuModel field = new StartMenuModel();
        StartMenuView startView = new StartMenuView(gameView.getLocation().x, gameView.getLocation().y);
        StartMenuViewController controller = new StartMenuViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
        startView.setVisible(true);
        gameView.dispose();
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
        int indicator = model.getIndicator();
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
                        navigateMenuView();
                        break;
                    case 1:
                        System.exit(0);
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
        if(target.contains("dialog_button_back_menu")) {
            navigateMenuView();
        } else if(target.contains("dialog_button_exit")) {
            System.exit(0);
        }
    }
}
