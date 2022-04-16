package Tetris.Controller;

import Tetris.Model.SettingBoard;
import Tetris.Model.SizeSettingBoard;
import Tetris.Model.StartBoard;
import Tetris.Util.JsonWriter;
import Tetris.View.SettingView;
import Tetris.View.SizeSettingView;
import Tetris.View.StartView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingViewController implements KeyListener {
    private SettingBoard model;
    private SettingView settingView;

    public SettingViewController(SettingBoard model, SettingView view) {
        this.model = model;
        this.settingView = view;
    }
    public void navigationSizeSettingView(){
        SizeSettingBoard field = new SizeSettingBoard();
        SizeSettingView view = new SizeSettingView(settingView.getLocation().x, settingView.getLocation().y);
        SizeSettingViewController controller = new SizeSettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        view.setVisible(true);
        settingView.dispose();
    }
    public void navigationColorSettingView(){
        // navigation code
    }
    public void resetSetting(){
        JsonWriter.setDefaultSetting();
        SettingBoard field = new SettingBoard();
        SettingView view = new SettingView(settingView.getLocation().x, settingView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        view.setVisible(true);
        settingView.dispose();
    }
    public void resetScoreBoard(){
        // reset code
    }
    public void navigationKeySettingView(){
        // navigation code
    }
    public void navigationPreviousView(){
        StartBoard field = new StartBoard();
        StartView startView = new StartView(settingView.getLocation().x, settingView.getLocation().y);
        StartViewController controller = new StartViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
        startView.setVisible(true);
        settingView.dispose();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int indicator = SettingBoard.getIndicator();
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
                        navigationSizeSettingView();
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
                        navigationKeySettingView();
                        break;
                    case 5:
                        navigationPreviousView();
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