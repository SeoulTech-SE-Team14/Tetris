package Tetris.Model.setting;

import Tetris.Util.JsonReader;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class KeySettingBoard extends Observable {
    private static int indicator = 0;
    private final int buttonCount = 2;
    private final int buttonWidth = 130;
    private final int buttonHeight = 60;
    private final int screenWidth = JsonReader.getWidth();
    private final int screenHeight = JsonReader.getHeight();
    private final HashMap<String, Integer> keyMap = JsonReader.getKeyMap();

    private final ImageIcon backgroundImage350700 = new ImageIcon("app/src/main/resources/image/default_background_350_700.png");
    private final ImageIcon backgroundImage400800 = new ImageIcon("app/src/main/resources/image/default_background_400_800.png");
    private final ImageIcon backgroundImage300600 = new ImageIcon("app/src/main/resources/image/default_background_300_600.png");
    // default image
    private final ImageIcon rightKeyImage = new ImageIcon("app/src/main/resources/image/key_setting/key_right.png");
    private final ImageIcon leftKeyImage = new ImageIcon("app/src/main/resources/image/key_setting/key_left.png");
    private final ImageIcon downKeyImage = new ImageIcon("app/src/main/resources/image/key_setting/key_down.png");
    private final ImageIcon fallKeyImage = new ImageIcon("app/src/main/resources/image/key_setting/key_fall.png");
    private final ImageIcon rotateKeyImage = new ImageIcon("app/src/main/resources/image/key_setting/key_rotate.png");
    private final ImageIcon pauseKeyImage = new ImageIcon("app/src/main/resources/image/key_setting/key_pause.png");
    private final ImageIcon storeBtnImage = new ImageIcon("app/src/main/resources/image/key_setting/button_store.png");
    private final ImageIcon backBtnImage = new ImageIcon("app/src/main/resources/image/key_setting/button_back.png");
    private final ImageIcon keyFieldImage = new ImageIcon("app/src/main/resources/image/key_setting/text_field.png");
    // focus image
    private final ImageIcon focusStoreBtnImage = new ImageIcon("app/src/main/resources/image/key_setting/button_store_focused.png");
    private final ImageIcon focusBackBtnImage = new ImageIcon("app/src/main/resources/image/key_setting/button_back_focused.png");

    public void setIndicator(int indicator) {
        KeySettingBoard.indicator = indicator;
        setChanged();
        notifyObservers();
    }
    public ImageIcon getBackgroundImage() {
        int width = JsonReader.getWidth();
        int height = JsonReader.getHeight();
        if(width == 300 && height == 600) {
            return backgroundImage300600;
        }
        else if(width == 350 && height == 700) {
            return backgroundImage350700;
        }
        else{
            return backgroundImage400800;
        }
    }

    public Map<String, Integer> getKeyMap() {
        return keyMap;
    }

    public static int getIndicator() {
        return indicator;
    }

    public int getButtonCount() {
        return buttonCount;
    }

    public int getButtonWidth() {
        return buttonWidth;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public ImageIcon getRightKeyImage() {
        return rightKeyImage;
    }

    public ImageIcon getLeftKeyImage() {
        return leftKeyImage;
    }

    public ImageIcon getDownKeyImage() {
        return downKeyImage;
    }

    public ImageIcon getFallKeyImage() {
        return fallKeyImage;
    }

    public ImageIcon getRotateKeyImage() {
        return rotateKeyImage;
    }

    public ImageIcon getPauseKeyImage() {
        return pauseKeyImage;
    }

    public ImageIcon getStoreBtnImage() {
        return storeBtnImage;
    }

    public ImageIcon getBackBtnImage() {
        return backBtnImage;
    }

    public ImageIcon getKeyFieldImage() {
        return keyFieldImage;
    }

    public ImageIcon getFocusStoreBtnImage() {
        return focusStoreBtnImage;
    }

    public ImageIcon getFocusBackBtnImage() {
        return focusBackBtnImage;
    }
}
