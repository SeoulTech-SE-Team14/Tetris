package Tetris.Controller;

import Tetris.Model.SettingBoard;
import Tetris.Model.StartBoard;
import Tetris.View.SettingView;
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
        // navigation code
        //settingView.setVisible(false);
    }
    public void navigationColorSettingView(){
        // navigation code
        //settingView.setVisible(false);
    }
    public void resetSetting(){
        // reset code
    }
    public void resetScoreBoard(){
        // reset code
    }
    public void navigationKeySettingView(){
        // navigation code
        //settingView.setVisible(false);
    }
    public void navigationPreviousView(){
        StartBoard field = new StartBoard();
        StartView startView = new StartView(settingView.getLocation().x, settingView.getLocation().y);
        StartViewController controller = new StartViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
        startView.setVisible(true);
        settingView.setVisible(false);
    }
    @Override
    public void keyTyped(KeyEvent e) { }

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
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}