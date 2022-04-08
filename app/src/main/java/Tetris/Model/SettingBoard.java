package Tetris.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

public class SettingBoard extends Observable {
    private static int indicator = 0;
    private final int buttonCount = 6;
    private final int buttonWidth = 260;
    private final int buttonHeight = 60;

    private final ImageIcon backgroundImage350700 = new ImageIcon("app/src/main/resources/image/default_background_350_700.png");
    private final ImageIcon backgroundImage400800 = new ImageIcon("app/src/main/resources/image/default_background_400_800.png");
    private final ImageIcon backgroundImage300600 = new ImageIcon("app/src/main/resources/image/default_background_300_600.png");
    // default image
    private final ImageIcon settingSizeBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_setting_size.png");
    private final ImageIcon colorblindnessBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_colorblindness.png");
    private final ImageIcon resetSettingBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_reset_setting.png");
    private final ImageIcon resetScoreboardBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_reset_scoreboard.png");
    private final ImageIcon settingKeyBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_setting_key.png");
    private final ImageIcon defaultBackBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_back.png");

    // focus image
    private final ImageIcon focusSettingSizeBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_setting_size_focused.png");
    private final ImageIcon focusColorblindnessBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_colorblindness_focused.png");
    private final ImageIcon focusResetSettingBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_reset_setting_focused.png");
    private final ImageIcon focusResetScoreboardBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_reset_scoreboard_focused.png");
    private final ImageIcon focusSettingKeyBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_setting_key_focused.png");
    private final ImageIcon focusDefaultBackBtnImage = new ImageIcon("app/src/main/resources/image/SettingView/button_back_focused.png");


    public void setIndicator(int indicator) {
        SettingBoard.indicator = indicator;
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

    public ImageIcon getSettingSizeBtnImage() {
        return settingSizeBtnImage;
    }
    public ImageIcon getColorblindnessBtnImage() {
        return colorblindnessBtnImage;
    }
    public ImageIcon getResetSettingBtnImage() {
        return resetSettingBtnImage;
    }
    public ImageIcon getResetScoreboardBtnImage() {
        return resetScoreboardBtnImage;
    }
    public ImageIcon getSettingKeyBtnImagee() {
        return settingKeyBtnImage;
    }
    public ImageIcon getDefaultBackBtnImage() {
        return defaultBackBtnImage;
    }

    public ImageIcon getFocusSettingSizeBtnImage() {
        return focusSettingSizeBtnImage;
    }
    public ImageIcon getFocusColorblindnessBtnImage() {
        return focusColorblindnessBtnImage;
    }
    public ImageIcon getFocusResetSettingBtnImage() {
        return focusResetSettingBtnImage;
    }
    public ImageIcon getFocusResetScoreboardBtnImage() {
        return focusResetScoreboardBtnImage;
    }
    public ImageIcon getFocusSettingKeyBtnImage() {
        return focusSettingKeyBtnImage;
    }
    public ImageIcon getFocusDefaultBackBtnImage() {
        return focusDefaultBackBtnImage;
    }

    public int getButtonCount() {
        return buttonCount;
    }
}
