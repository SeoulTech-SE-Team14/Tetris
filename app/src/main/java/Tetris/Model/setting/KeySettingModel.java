package Tetris.Model.setting;

import Tetris.Util.JsonReader;

import javax.swing.*;
import java.util.Map;
import java.util.Observable;

public class KeySettingModel extends Observable {
    private static int indicator = 0;

    private final int buttonCount = 2;
    private final int screenWidth = JsonReader.getWidth();
    private final int screenHeight = JsonReader.getHeight();

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

    private static final String KEY_IMAGE_URL = "app/src/main/resources/image/key_setting/key/";
    private final Map<String, Integer> keyMap;
    private static int right;
    private static int left;
    private static int down;
    private static int fall;
    private static int pause;
    private static int rotate;

    // focus image
    private final ImageIcon focusStoreBtnImage = new ImageIcon("app/src/main/resources/image/key_setting/button_store_focused.png");
    private final ImageIcon focusBackBtnImage = new ImageIcon("app/src/main/resources/image/key_setting/button_back_focused.png");

    public void setIndicator(int indicator) {
        KeySettingModel.indicator = indicator;
        setChanged();
        notifyObservers();
    }

    public KeySettingModel() {
        keyMap = JsonReader.getKeyMap();
        right = keyMap.get("right");
        left = keyMap.get("left");
        down = keyMap.get("down");
        fall = keyMap.get("fall");
        pause = keyMap.get("pause");
        rotate = keyMap.get("rotate");
    }

    public Map<String, Integer> getKeyMap() {
        return keyMap;
    }
    public int getIndicator() {
        return indicator;
    }

    public int getRight() {
        return right;
    }

    public int getLeft() {
        return left;
    }

    public int getDown() {
        return down;
    }

    public int getFall() {
        return fall;
    }

    public int getPause() {
        return pause;
    }

    public int getRotate() {
        return rotate;
    }

    public int getButtonCount() {
        return buttonCount;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
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
    public ImageIcon getKeyImage(int key){
        String url = KEY_IMAGE_URL + Integer.toString(key) + ".png";
        return new ImageIcon(url);
    }

    public ImageIcon getRightKeyInputImage() {
        System.out.println(right);
        return getKeyImage(right);
    }
    public ImageIcon getLeftKeyInputImage() {
        return getKeyImage(left);
    }

    public ImageIcon getDownKeyInputImage() {
        return getKeyImage(down);
    }

    public ImageIcon getFallKeyInputImage() {
        return getKeyImage(fall);
    }

    public ImageIcon getRotateKeyInputImage() {
        return getKeyImage(rotate);
    }

    public ImageIcon getPauseKeyInputImage() {
        return getKeyImage(pause);
    }

    public void setRight(int right) {
        this.right = right;
        setChanged();
        notifyObservers();

    }

    public void setLeft(int left) {
        this.left = left;
        setChanged();
        notifyObservers();
    }

    public void setDown(int down) {
        this.down = down;
        setChanged();
        notifyObservers();
    }

    public void setFall(int fall) {
        this.fall = fall;
        System.out.println(fall);
        setChanged();
        notifyObservers();
    }

    public void setPause(int pause) {
        this.pause = pause;
        setChanged();
        notifyObservers();
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
        setChanged();
        notifyObservers();
    }
}
