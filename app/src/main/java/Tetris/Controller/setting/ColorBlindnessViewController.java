package Tetris.Controller.setting;

import Tetris.Model.setting.SettingModel;
import Tetris.Model.setting.ColorBlindnessModel;

import Tetris.Util.ColorBlindnessType;
import Tetris.Util.JsonWriter;

import Tetris.View.setting.SettingView;
import Tetris.View.setting.ColorBlindnessView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ColorBlindnessViewController implements KeyListener, ActionListener {
    private final ColorBlindnessModel model;
    private final ColorBlindnessView colorBlindnessView;

    public ColorBlindnessViewController(ColorBlindnessModel model, ColorBlindnessView view) {
        this.model = model;
        this.colorBlindnessView = view;
        this.colorBlindnessView.setActionListener(this);
    }
    
    public void navigatePreviousView(){
        SettingModel field = new SettingModel();
        SettingView view = new SettingView(colorBlindnessView.getLocation().x, colorBlindnessView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        colorBlindnessView.dispose();
    }

    // 각 버튼 클릭되면 블록 색상바꾸기
    public void changeColor(ColorBlindnessType type){
        JsonWriter.setColorMode(type.getKey());
        ColorBlindnessModel field = new ColorBlindnessModel();
        ColorBlindnessView view = new ColorBlindnessView(colorBlindnessView.getLocation().x, colorBlindnessView.getLocation().y);
        ColorBlindnessViewController controller = new ColorBlindnessViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        colorBlindnessView.dispose();
    }
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
                        changeColor(ColorBlindnessType.NORMAL);
                        break;
                    case 1:
                        changeColor(ColorBlindnessType.PROTAN);
                        break;
                    case 2:
                        changeColor(ColorBlindnessType.DEUTAN);
                        break;
                    case 3:
                        changeColor(ColorBlindnessType.TRITAN);
                        break;   
                    case 4:
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
        if(target.contains("button_colorblindness_default")) {
            changeColor(ColorBlindnessType.NORMAL);
        } else if(target.contains("button_colorblindness_blue")) {
            changeColor(ColorBlindnessType.TRITAN);
        } else if(target.contains("button_colorblindness_green")) {
            changeColor(ColorBlindnessType.DEUTAN);
        } else if(target.contains("button_colorblindness_red")) {
            changeColor(ColorBlindnessType.PROTAN);
        } else if(target.contains("button_back")) {
            navigatePreviousView();
        }
    }
}
