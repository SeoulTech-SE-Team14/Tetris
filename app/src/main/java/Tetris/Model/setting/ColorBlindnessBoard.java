package Tetris.Model.setting;

import Tetris.Util.JsonReader;

import javax.swing.*;
import java.util.Observable;

public class ColorBlindnessBoard extends Observable {
    private static int indicator = 0;
    private final int buttonCount = 5;
    private final int buttonWidth = 260;
    private final int buttonHeight = 60;
    private final int screenWidth = JsonReader.getWidth();
    private final int screenHeight = JsonReader.getHeight();

    private final ImageIcon backgroundImage350700 = new ImageIcon("app/src/main/resources/image/default_background_350_700.png");
    private final ImageIcon backgroundImage400800 = new ImageIcon("app/src/main/resources/image/default_background_400_800.png");
    private final ImageIcon backgroundImage300600 = new ImageIcon("app/src/main/resources/image/default_background_300_600.png");

    // default image
    private final ImageIcon colorDefaultBtnImage = new ImageIcon("app/src/main/resources/image/colorblindness/button_colorblindness_default.png");
    private final ImageIcon colorRedBtnImage = new ImageIcon("app/src/main/resources/image/colorblindness/button_colorblindness_red.png");
    private final ImageIcon colorGreenBtnImage = new ImageIcon("app/src/main/resources/image/colorblindness/button_colorblindness_green.png");
    private final ImageIcon colorBlueBtnImage = new ImageIcon("app/src/main/resources/image/colorblindness/button_colorblindness_blue.png");
    private final ImageIcon defaultBackBtnImage = new ImageIcon("app/src/main/resources/image/button_back.png");
    
    // focus image
    private final ImageIcon focusColorDefaultBtnImage = new ImageIcon("app/src/main/resources/image/colorblindness/button_colorblindness_default_focused.png");
    private final ImageIcon focusColorRedBtnImage = new ImageIcon("app/src/main/resources/image/colorblindness/button_colorblindness_red_focused.png");
    private final ImageIcon focusColorGreenBtnImage = new ImageIcon("app/src/main/resources/image/colorblindness/button_colorblindness_green_focused.png");
    private final ImageIcon focusColorBlueBtnImage = new ImageIcon("app/src/main/resources/image/colorblindness/button_colorblindness_blue_focused.png");
    private final ImageIcon focusDefaultBackBtnImage = new ImageIcon("app/src/main/resources/image/button_back_focused.png");

    // preview image
    private final ImageIcon previewDefaultImage = new ImageIcon("app/src/main/resources/image/colorblindness/preview_colorblindness_default.png");
    private final ImageIcon previewRedImage = new ImageIcon("app/src/main/resources/image/colorblindness/preview_colorblindness_red.png");
    private final ImageIcon previewGreenImage = new ImageIcon("app/src/main/resources/image/colorblindness/preview_colorblindness_green.png");
    private final ImageIcon previewBlueImage = new ImageIcon("app/src/main/resources/image/colorblindness/preview_colorblindness_blue.png");

    public void setIndicator(int indicator) {
        ColorBlindnessBoard.indicator = indicator;
        setChanged();
        notifyObservers();
    }

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getButtonWidth() {
        return buttonWidth;
    }
    public int getButtonHeight() {
        return buttonHeight;
    }
    public int getIndicator() { return indicator; }

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

    public ImageIcon getColorDefaultBtnImage() {
        return colorDefaultBtnImage;
    }
    public ImageIcon getColorRedBtnImage() {
        return colorRedBtnImage;
    }
    public ImageIcon getColorGreenBtnImage() {
        return colorGreenBtnImage;
    }
    public ImageIcon getColorBlueBtnImage() {
        return colorBlueBtnImage;
    }
    public ImageIcon getDefaultBackBtnImage() {
        return defaultBackBtnImage;
    }

    public ImageIcon getFocusColorDefaultBtnImage() {
        return focusColorDefaultBtnImage;
    }
    public ImageIcon getFocusColorRedBtnImage() {
        return focusColorRedBtnImage;
    }
    public ImageIcon getFocusColorGreenBtnImage() {
        return focusColorGreenBtnImage;
    }
    public ImageIcon getFocusColorBlueBtnImage() {
        return focusColorBlueBtnImage;
    }
    public ImageIcon getFocusDefaultBackBtnImage() {
        return focusDefaultBackBtnImage;
    }

    public ImageIcon getPreviewDefaultImage() {
        return previewDefaultImage;
    }
    public ImageIcon getPreviewRedImage() {
        return previewRedImage;
    }
    public ImageIcon getPreviewGreenImage() {
        return previewGreenImage;
    }
    public ImageIcon getPreviewBlueImage() {
        return previewBlueImage;
    }

    public int getButtonCount() {
        return buttonCount;
    }
}