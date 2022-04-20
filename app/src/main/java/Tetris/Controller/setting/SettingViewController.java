package Tetris.Controller.setting;

import Tetris.Controller.home.StartMenuViewController;
import Tetris.Model.setting.ColorBlindnessModel;
import Tetris.Model.setting.KeySettingModel;
import Tetris.Model.setting.SettingModel;
import Tetris.Model.setting.SizeSettingModel;
import Tetris.Model.home.StartMenuModel;

import Tetris.Util.JsonWriter;

import Tetris.Util.ScoreboardJsonKeyType;
import Tetris.View.setting.ColorBlindnessView;
import Tetris.View.setting.KeySettingView;
import Tetris.View.setting.SettingView;
import Tetris.View.setting.SizeSettingView;
import Tetris.View.home.StartMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingViewController implements KeyListener, ActionListener {
    private final SettingModel model;
    private final SettingView settingView;

    public SettingViewController(SettingModel model, SettingView view) {
        this.model = model;
        this.settingView = view;
        this.settingView.setActionListener(this);
    }

    public void resetSetting(){
        JsonWriter.setResetSetting();
        SettingModel field = new SettingModel();
        SettingView view = new SettingView(settingView.getLocation().x, settingView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        settingView.dispose();
    }
    public void resetScoreBoard(){
        JsonWriter.setResetScoreBoard(ScoreboardJsonKeyType.BASIC_MODE);
        JsonWriter.setResetScoreBoard(ScoreboardJsonKeyType.ITEM_MODE);
    }
    public void navigateSizeSettingView(){
        SizeSettingModel field = new SizeSettingModel();
        SizeSettingView view = new SizeSettingView(settingView.getLocation().x, settingView.getLocation().y);
        SizeSettingViewController controller = new SizeSettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        settingView.dispose();
    }
    public void navigationColorSettingView(){
        ColorBlindnessModel field = new ColorBlindnessModel();
        ColorBlindnessView view = new ColorBlindnessView(settingView. getLocation().x, settingView.getLocation().y);
        ColorBlindnessViewController controller = new ColorBlindnessViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        settingView.dispose();
    }
    public void navigateKeySettingView(){
        KeySettingModel field = new KeySettingModel();
        KeySettingView view = new KeySettingView(settingView.getLocation().x, settingView.getLocation().y);
        KeySettingViewController controller = new KeySettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        settingView.dispose();
    }
    public void navigatePreviousView(){
        StartMenuModel field = new StartMenuModel();
        StartMenuView view = new StartMenuView(settingView.getLocation().x, settingView.getLocation().y);
        StartMenuViewController controller = new StartMenuViewController(field, view);
        field.addObserver(view);
        view.addKeyListener(controller);
        settingView.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored
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
                        navigateSizeSettingView();
                        break;
                    case 1:
                        navigationColorSettingView();
                        break;
                    case 2:
                        resetSetting();
                        break;
                    case 3:
                        resetScoreBoard();
                        break;
                    case 4:
                        navigateKeySettingView();
                        break;
                    case 5:
                        navigatePreviousView();
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
        // default implementation ignored
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String target = e.getSource().toString();
        if(target.contains("button_setting_size")) {
            navigateSizeSettingView();
        } else if(target.contains("button_colorblindness")) {
            navigationColorSettingView();
        } else if(target.contains("button_reset_setting")) {
            resetSetting();
        } else if(target.contains("button_reset_scoreboard")) {
            resetScoreBoard();
        } else if(target.contains("button_setting_key")) {
            navigateKeySettingView();
        } else if(target.contains("button_back")) {
            navigatePreviousView();
        }
    }
}