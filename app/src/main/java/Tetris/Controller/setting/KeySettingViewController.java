package Tetris.Controller.setting;

import Tetris.Model.setting.KeySettingModel;
import Tetris.Model.setting.SettingModel;

import Tetris.Util.JsonWriter;

import Tetris.View.setting.KeySettingView;
import Tetris.View.setting.SettingView;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeySettingViewController implements KeyListener, ActionListener {
    private final KeySettingModel model;
    private final KeySettingView keySettingView;

    public KeySettingViewController(KeySettingModel model, KeySettingView keySettingView) {
        this.model = model;
        this.keySettingView = keySettingView;
        this.keySettingView.setKeyListener(this);
        this.keySettingView.setActionListener(this);
    }
    private boolean isPossibleStore(Map<String, Integer> keyMap){
        Set<Integer> set = new HashSet<>();
        for(String key : keyMap.keySet()) {
            set.add(keyMap.get(key));
        }
        return set.size() == 6;
    }
    private boolean isPossibleKey(int keyCode) {
        return keyCode == 32 || (37 <= keyCode && keyCode <= 57) || (65 <= keyCode && keyCode <= 90);
    }
    public void showWarning(String message){
        JOptionPane.showMessageDialog(keySettingView, message,null,JOptionPane.PLAIN_MESSAGE);
    }
    public void storeKeyMap(){
        Map<String, Integer> keyMap = new HashMap<>();
        keyMap.put("right", model.getRight());
        keyMap.put("left", model.getLeft());
        keyMap.put("down", model.getDown());
        keyMap.put("rotate", model.getRotate());
        keyMap.put("fall", model.getFall());
        keyMap.put("pause", model.getPause());
        if(!isPossibleStore(keyMap)) {
            showWarning("키가 중복됩니다.");
            return ;
        }
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
    public void actionPerformed(ActionEvent e) {
        String target = e.getSource().toString();
        if(target.contains("button_store")) {
            storeKeyMap();
        } else if(target.contains("button_back")) {
            navigatePreviousView();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(!isPossibleKey(e.getKeyCode())) {
            showWarning("숫자, 알파벳, 방향키만 가능합니다.");
            return ;
        }
        if(e.getComponent().getName().equals("rightInput")){
            model.setRight(e.getKeyCode());
        }
        if(e.getComponent().getName().equals("leftInput")){
            model.setLeft(e.getKeyCode());
        }
        if(e.getComponent().getName().equals("downInput")){
            model.setDown(e.getKeyCode());
        }
        if(e.getComponent().getName().equals("fallInput")){
            model.setFall(e.getKeyCode());
        }
        if(e.getComponent().getName().equals("rotateInput")){
            model.setRotate(e.getKeyCode());
        }
        if(e.getComponent().getName().equals("pauseInput")){
            model.setPause(e.getKeyCode());
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // default implementation ignored
    }

}
