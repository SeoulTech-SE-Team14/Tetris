package Tetris.Model.setting;

import Tetris.Util.JsonReader;

import javax.swing.*;
import java.util.Observable;

public class SizeSettingModel extends Observable {
    private static int indicator = 0;
    private final int buttonCount = 4;
    private final int screenWidth = JsonReader.getWidth();
    private final int screenHeight = JsonReader.getHeight();

    private final ImageIcon backgroundImage350700 = new ImageIcon("app/src/main/resources/image/default_background_350_700.png");
    private final ImageIcon backgroundImage400800 = new ImageIcon("app/src/main/resources/image/default_background_400_800.png");
    private final ImageIcon backgroundImage300600 = new ImageIcon("app/src/main/resources/image/default_background_300_600.png");

    // default image
    private final ImageIcon size300600BtnImage = new ImageIcon("app/src/main/resources/image/size_setting/button_300_600.png");
    private final ImageIcon size350700BtnImage = new ImageIcon("app/src/main/resources/image/size_setting/button_350_700.png");
    private final ImageIcon size400800BtnImage = new ImageIcon("app/src/main/resources/image/size_setting/button_400_800.png");
    private final ImageIcon backBtnImage = new ImageIcon("app/src/main/resources/image/button_back.png");

    // focus image
    private final ImageIcon focusSize300600BtnImage = new ImageIcon("app/src/main/resources/image/size_setting/button_300_600_focused.png");
    private final ImageIcon focusSize350700BtnImage = new ImageIcon("app/src/main/resources/image/size_setting/button_350_700_focused.png");
    private final ImageIcon focusSize400800BtnImage = new ImageIcon("app/src/main/resources/image/size_setting/button_400_800_focused.png");
    private final ImageIcon focusBackBtnImage = new ImageIcon("app/src/main/resources/image/button_back_focused.png");

    public void setIndicator(int indicator) {
        SizeSettingModel.indicator = indicator;
        setChanged();
        notifyObservers();
    }

    public int getIndicator() { return indicator; }
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
    public ImageIcon getSize300600BtnImage() {
        return size300600BtnImage;
    }
    public ImageIcon getSize350700BtnImage() {
        return size350700BtnImage;
    }
    public ImageIcon getSize400800BtnImage() {
        return size400800BtnImage;
    }
    public ImageIcon getBackBtnImage() {
        return backBtnImage;
    }

    public ImageIcon getFocusSize300600BtnImage() {
        return focusSize300600BtnImage;
    }
    public ImageIcon getFocusSize350700BtnImage() {
        return focusSize350700BtnImage;
    }
    public ImageIcon getFocusSize400800BtnImage() {
        return focusSize400800BtnImage;
    }
    public ImageIcon getFocusBackBtnImage() {
        return focusBackBtnImage;
    }
}
