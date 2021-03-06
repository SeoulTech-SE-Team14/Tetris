package Tetris.Controller.setting;

import Tetris.Model.setting.SettingModel;
import Tetris.Model.setting.SizeSettingModel;

import Tetris.Util.JsonWriter;

import Tetris.View.setting.SettingView;
import Tetris.View.setting.SizeSettingView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SizeSettingViewController implements KeyListener {
    enum SizeType { SMALL, MEDIUM, LARGE }

    private final SizeSettingModel model;
    private final SizeSettingView sizeSettingView;

    public SizeSettingViewController(SizeSettingModel model, SizeSettingView view) {
        this.model = model;
        this.sizeSettingView = view;
    }

    public void navigatePreviousView(){
        SettingModel field = new SettingModel();
        SettingView view = new SettingView(sizeSettingView.getLocation().x, sizeSettingView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        sizeSettingView.dispose();
    }
    public void changeScreenSize(SizeType type){
        switch (type){
            case SMALL:
                JsonWriter.setSize(300, 600);
                break;
            case MEDIUM:
                JsonWriter.setSize(350, 700);
                break;
            case LARGE:
                JsonWriter.setSize(400, 800);
                break;
        }
        SizeSettingModel field = new SizeSettingModel();
        SizeSettingView view = new SizeSettingView(sizeSettingView.getLocation().x, sizeSettingView.getLocation().y);
        SizeSettingViewController controller = new SizeSettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        sizeSettingView.dispose();
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
                        changeScreenSize(SizeType.SMALL);
                        break;
                    case 1:
                        changeScreenSize(SizeType.MEDIUM);
                        break;
                    case 2:
                        changeScreenSize(SizeType.LARGE);
                        break;
                    case 3:
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
