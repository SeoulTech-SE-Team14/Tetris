package Tetris.Controller.setting;

import Tetris.Model.setting.KeySettingBoard;
import Tetris.Model.setting.SettingBoard;
import Tetris.Util.JsonWriter;
import Tetris.View.setting.KeySettingView;
import Tetris.View.setting.SettingView;

import java.awt.event.*;
import java.util.HashMap;

public class KeySettingViewController implements KeyListener, ActionListener {
    private final KeySettingBoard model;
    private final KeySettingView keySettingView;
    private HashMap<String, Integer> keyMap;

    public KeySettingViewController(KeySettingBoard model, KeySettingView keySettingView) {
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
    private void navigationPreviousView() {
        SettingBoard field = new SettingBoard();
        SettingView view = new SettingView(keySettingView.getLocation().x, keySettingView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        view.setVisible(true);
        keySettingView.dispose();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int indicator = KeySettingBoard.getIndicator();
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().toString().contains("backBtn")){
            navigationPreviousView();
            System.out.println("back");
        }
        else if(e.getSource().toString().contains("storeBtn")){
            changeKey();
            System.out.println("store");
        }
    }

}
