package Tetris.Controller.setting;

import Tetris.Controller.home.StartViewController;

import Tetris.Model.setting.KeySettingBoard;
import Tetris.Model.setting.SettingBoard;
import Tetris.Model.setting.SizeSettingBoard;
import Tetris.Model.home.StartBoard;

import Tetris.Util.JsonWriter;

import Tetris.View.setting.KeySettingView;
import Tetris.View.setting.SettingView;
import Tetris.View.setting.SizeSettingView;
import Tetris.View.home.StartView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingViewController implements KeyListener {
    private final SettingBoard model;
    private final SettingView settingView;

    public SettingViewController(SettingBoard model, SettingView view) {
        this.model = model;
        this.settingView = view;
    }

    public void resetSetting(){
        JsonWriter.setDefaultSetting();
        SettingBoard field = new SettingBoard();
        SettingView view = new SettingView(settingView.getLocation().x, settingView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        settingView.dispose();
    }
    public void resetScoreBoard(){
        // reset code
    }
    public void navigateSizeSettingView(){
        SizeSettingBoard field = new SizeSettingBoard();
        SizeSettingView view = new SizeSettingView(settingView.getLocation().x, settingView.getLocation().y);
        SizeSettingViewController controller = new SizeSettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        settingView.dispose();
    }
    public void navigationColorSettingView(){
        // navigation code
    }
    public void navigateKeySettingView(){
        KeySettingBoard field = new KeySettingBoard();
        KeySettingView view = new KeySettingView(settingView.getLocation().x, settingView.getLocation().y);
        KeySettingViewController controller = new KeySettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        settingView.dispose();
    }
    public void navigatePreviousView(){
        StartBoard field = new StartBoard();
        StartView view = new StartView(settingView.getLocation().x, settingView.getLocation().y);
        StartViewController controller = new StartViewController(field, view);
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
}