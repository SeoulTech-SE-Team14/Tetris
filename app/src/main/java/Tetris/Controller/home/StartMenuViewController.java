package Tetris.Controller.home;

import Tetris.Controller.game.GameViewController;
import Tetris.Controller.game.SelectDialogController;
import Tetris.Controller.scoreboard.ScoreboardController;
import Tetris.Controller.setting.SettingViewController;

import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Model.game.SelectDialogModel;
import Tetris.Model.home.StartMenuModel;
import Tetris.Model.scoreboard.ScoreboardModel;
import Tetris.Model.setting.SettingModel;

import Tetris.Util.GameType;
import Tetris.View.game.SelectDialog;
import Tetris.View.home.StartMenuView;
import Tetris.View.scoreboard.ScoreboardView;
import Tetris.View.setting.SettingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartMenuViewController implements KeyListener, ActionListener {
    private final StartMenuModel model;
    private final StartMenuView startView;

    public StartMenuViewController(StartMenuModel model, StartMenuView view) {
        this.model = model;
        this.startView = view;
        this.startView.setActionListener(this);
    }
    public void showDifficultyDialog(){
        SelectDialogModel selectDialogModel = new SelectDialogModel();
        int x = startView.getLocation().x + (model.getScreenWidth() -  selectDialogModel.getWidth()) / 2;
        int y = startView.getLocation().y + (model.getScreenHeight() - selectDialogModel.getHeight()) / 2;
        SelectDialog dialog = new SelectDialog(startView, x, y);
        SelectDialogController selectDialogController = new SelectDialogController(selectDialogModel, dialog, startView);
        dialog.addKeyListener(selectDialogController);
        selectDialogModel.addObserver(dialog);
        dialog.setVisible(true);
    }
    public void playItemMode(){
        GameStateModel gameState = new GameStateModel(GameType.ITEM_MODE);
        GameModel field = new GameModel(gameState,20, 10);
        Tetris.View.game.GameView view = new Tetris.View.game.GameView(startView.getLocation().x, startView.getLocation().y, field);
        GameViewController controller = new GameViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        gameState.addObserver(view);
        view.setVisible(true);
        startView.dispose();
    }
    public void navigateSettingView(){
        SettingModel field = new SettingModel();
        SettingView view = new SettingView(startView.getLocation().x, startView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        startView.dispose();
    }
    public void navigateScoreboardView(){
        ScoreboardModel field = new ScoreboardModel();
        ScoreboardView view = new ScoreboardView(startView.getLocation().x, startView.getLocation().y);
        ScoreboardController controller = new ScoreboardController(field, view);
        view.addKeyListener(controller);
        startView.dispose();
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
                        showDifficultyDialog();
                        break;
                    case 1:
                        playItemMode();
                        break;
                    case 2:
                        navigateSettingView();
                        break;
                    case 3:
                        navigateScoreboardView();
                        break;
                    case 4:
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
        if(target.contains("button_game_start")) {
            showDifficultyDialog();
        } else if(target.contains("button_item_game")) {
            playItemMode();
        } else if(target.contains("button_setting")) {
            navigateSettingView();
        } else if(target.contains("button_scoreboard")) {
            navigateScoreboardView();
        } else if(target.contains("button_exit")) {
            System.exit(0);
        }
    }
}