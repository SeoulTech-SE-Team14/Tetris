package Tetris.Controller.setting;

import Tetris.Model.setting.KeySettingModel;
import Tetris.Model.setting.SettingModel;

import Tetris.Util.JsonWriter;

import Tetris.View.setting.KeySettingView;
import Tetris.View.setting.SettingView;

import java.awt.event.*;
import java.util.HashMap;

public class KeySettingViewController implements KeyListener, ActionListener {
    private final KeySettingModel model;
    private final KeySettingView keySettingView;
    private HashMap<String, Integer> keyMap;

    public KeySettingViewController(KeySettingModel model, KeySettingView keySettingView) {
        this.model = model;
        this.keySettingView = keySettingView;
        this.keySettingView.setActionListener(this);
        this.keyMap = (HashMap<String, Integer>) model.getKeyMap();
    }

    private void changeKey(){
        keyMap.replace("right", Integer.parseInt(keySettingView.rightKeyField.getText()));
        keyMap.replace("left", Integer.parseInt(keySettingView.leftKeyField.getText()));
        keyMap.replace("down", Integer.parseInt(keySettingView.downKeyField.getText()));
        keyMap.replace("rotate", Integer.parseInt(keySettingView.rotateKeyField.getText()));
        keyMap.replace("fall", Integer.parseInt(keySettingView.fallKeyField.getText()));
        keyMap.replace("pause", Integer.parseInt(keySettingView.pauseKeyField.getText()));
        JsonWriter.setKey(keyMap);
    }
    private void navigatePreviousView() {
        SettingModel field = new SettingModel();
        SettingView view = new SettingView(keySettingView.getLocation().x, keySettingView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        keySettingView.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int indicator = model.getIndicator();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                indicator++;
                if(indicator == model.getButtonCount()) indicator = 0;
                model.setIndicator(indicator);
                break;
            case KeyEvent.VK_LEFT:
                indicator--;
                if(indicator == -1) indicator = model.getButtonCount() - 1;
                model.setIndicator(indicator);
                break;
            case KeyEvent.VK_ENTER:
                switch (indicator){
                    case 0:
                        changeKey();
                        break;
                    case 1:
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
        if(e.getSource().toString().contains("button_back.png")){
            navigatePreviousView();
        }
        else if(e.getSource().toString().contains("button_store_focused.png")){
            changeKey();
        }
    }
}
