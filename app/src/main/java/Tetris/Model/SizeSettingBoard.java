package Tetris.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

public class SizeSettingBoard extends Observable {
    private static int indicator = 0;
    private final int buttonCount = 4;
    private final int buttonWidth = 260;
    private final int buttonHeight = 60;

    private final ImageIcon backgroundImage350700 = new ImageIcon("app/src/main/resources/image/SizeSettingView/default_background_350_700.png");
    private final ImageIcon backgroundImage400800 = new ImageIcon("app/src/main/resources/image/SizeSettingView/default_background_400_800.png");
    private final ImageIcon backgroundImage300600 = new ImageIcon("app/src/main/resources/image/SizeSettingView/default_background_300_600.png");
    // default image
    private final ImageIcon size300600BtnImage = new ImageIcon("app/src/main/resources/image/SizeSettingView/button_300_600.png");
    private final ImageIcon size350700BtnImage = new ImageIcon("app/src/main/resources/image/SizeSettingView/button_350_700.png");
    private final ImageIcon size400800BtnImage = new ImageIcon("app/src/main/resources/image/SizeSettingView/button_400_800.png");
    private final ImageIcon defaultBackBtnImage = new ImageIcon("app/src/main/resources/image/SizeSettingView/button_back.png");

    // focus image
    private final ImageIcon focusSize300600BtnImage = new ImageIcon("app/src/main/resources/image/SizeSettingView/button_300_600_focused.png");
    private final ImageIcon focusSize350700BtnImage = new ImageIcon("app/src/main/resources/image/SizeSettingView/button_350_700_focused.png");
    private final ImageIcon focusSize400800BtnImage = new ImageIcon("app/src/main/resources/image/SizeSettingView/button_400_800_focused.png");
    private final ImageIcon focusDefaultBackBtnImage = new ImageIcon("app/src/main/resources/image/SizeSettingView/button_back_focused.png");


    public void setIndicator(int indicator) {
        SizeSettingBoard.indicator = indicator;
        setChanged();
        notifyObservers();
    }

    public int getButtonWidth() {
        return buttonWidth;
    }
    public int getButtonHeight() {
        return buttonHeight;
    }

    public int getIndicator() { return indicator; }

    public ImageIcon getBackgroundImage() {
        // setting에 따라 분기해야함.

        return backgroundImage350700;
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
    public ImageIcon getDefaultBackBtnImage() {
        return defaultBackBtnImage;
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
    public ImageIcon getFocusDefaultBackBtnImage() {
        return focusDefaultBackBtnImage;
    }

    public int getButtonCount() {
        return buttonCount;
    }
}
