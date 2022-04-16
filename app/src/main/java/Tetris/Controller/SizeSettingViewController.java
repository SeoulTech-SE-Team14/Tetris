package Tetris.Controller;

import Tetris.Model.SettingBoard;
import Tetris.Model.SizeSettingBoard;
import Tetris.Util.JsonWriter;
import Tetris.View.SettingView;
import Tetris.View.SizeSettingView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SizeSettingViewController implements KeyListener {
    enum SizeType { SMALL, MEDIUM, LARGE }

    private SizeSettingBoard model;
    private SizeSettingView sizeSettingView;

    public SizeSettingViewController(SizeSettingBoard model, SizeSettingView view) {
        this.model = model;
        this.sizeSettingView = view;
    }

    public void navigationPreviousView(){
        SettingBoard field = new SettingBoard();
        SettingView view = new SettingView(sizeSettingView.getLocation().x, sizeSettingView.getLocation().y);
        SettingViewController controller = new SettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        view.setVisible(true);
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
        SizeSettingBoard field = new SizeSettingBoard();
        SizeSettingView view = new SizeSettingView(sizeSettingView.getLocation().x, sizeSettingView.getLocation().y);
        SizeSettingViewController controller = new SizeSettingViewController(field, view);
        view.addKeyListener(controller);
        field.addObserver(view);
        view.setVisible(true);
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
