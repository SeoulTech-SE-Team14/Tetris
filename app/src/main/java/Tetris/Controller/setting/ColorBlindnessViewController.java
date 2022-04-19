package Tetris.Controller.setting;

import Tetris.Model.setting.SettingModel;
import Tetris.Model.setting.ColorBlindnessModel;

import Tetris.Util.JsonWriter;

import Tetris.View.setting.SettingView;
import Tetris.View.setting.ColorBlindnessView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ColorBlindnessViewController implements KeyListener {
    enum ColorBlindnessType { NORMAL, PROTAN, DEUTAN, TRITAN } // 정상, 적색맹, 녹색맹, 청색맹

    private final ColorBlindnessModel model;
    private final ColorBlindnessView colorBlindnessView;

    public ColorBlindnessViewController(ColorBlindnessModel model, ColorBlindnessView view) {
        this.model = model;
        this.colorBlindnessView = view;
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
    public void changeColor(){
        
    }

    // 각 모드에 따라 미리보기이미지 바꾸기
    public void changePreview(ColorBlindnessType type){
        switch(type){
            case NORMAL:
                //
                break;
            case PROTAN: 
                //
                break;
            case DEUTAN:
                //
                break;
            case TRITAN:
                //
                break;
        }

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
                        changePreview(ColorBlindnessType.NORMAL);
                        break;
                    case 1:
                        changePreview(ColorBlindnessType.PROTAN);
                        break;
                    case 2:
                        changePreview(ColorBlindnessType.DEUTAN);
                        break;
                    case 3:
                        changePreview(ColorBlindnessType.TRITAN);
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
}
