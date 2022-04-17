package Tetris.Model.home;

import Tetris.Util.JsonReader;

import javax.swing.*;
import java.util.Observable;

public class StartBoard extends Observable {
    private static int indicator = 0;
    private final int buttonCount = 5;
    private final int buttonWidth = 260;
    private final int buttonHeight = 60;
    private final int screenWidth = JsonReader.getWidth();
    private final int screenHeight = JsonReader.getHeight();

    private final ImageIcon backgroundImage350700 = new ImageIcon("app/src/main/resources/image/start_background_350_700.png");
    private final ImageIcon backgroundImage400800 = new ImageIcon("app/src/main/resources/image/start_background_400_800.png");
    private final ImageIcon backgroundImage300600 = new ImageIcon("app/src/main/resources/image/start_background_300_600.png");
    // default image
    private final ImageIcon defaultStartBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_game_start.png");
    private final ImageIcon defaultSettingBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_setting.png");
    private final ImageIcon defaultScoreboardBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_scoreboard.png");
    private final ImageIcon defaultExitBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_exit.png");
    private final ImageIcon defaultStartItemBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_item_game_start.png");
    private final ImageIcon keyDescribeLabelImage = new ImageIcon("app/src/main/resources/image/menu/key_describe_background.png");
    // focus image
    private final ImageIcon focusStartBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_game_start_focused.png");
    private final ImageIcon focusSettingBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_setting_focused.png");
    private final ImageIcon focusScoreboardBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_scoreboard_focused.png");
    private final ImageIcon focusExitBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_exit_focused.png");
    private final ImageIcon focusStartItemBtnImage = new ImageIcon("app/src/main/resources/image/menu/button_item_game_start_focused.png");

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getFontSize() {
        if(screenWidth == 300 && screenHeight == 600) {
            return 14;
        }
        else if(screenWidth == 350 && screenHeight == 700) {
            return 16;
        }
        else{
            return 18;
        }
    }

    public void setIndicator(int indicator) {
        StartBoard.indicator = indicator;
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
        if(JsonReader.getWidth() == 300 && JsonReader.getHeight() == 600) {
            return backgroundImage300600;
        }
        else if(JsonReader.getWidth() == 350 && JsonReader.getHeight() == 700) {
            return backgroundImage350700;
        }
        else{
            return backgroundImage400800;
        }
    }

    public ImageIcon getDefaultStartBtnImage() {
        return defaultStartBtnImage;
    }

    public ImageIcon getDefaultStartItemBtnImage() {
        return defaultStartItemBtnImage;
    }

    public ImageIcon getDefaultSettingBtnImage() {
        return defaultSettingBtnImage;
    }

    public ImageIcon getDefaultScoreboardBtnImage() {
        return defaultScoreboardBtnImage;
    }

    public ImageIcon getDefaultExitBtnImage() {
        return defaultExitBtnImage;
    }

    public ImageIcon getKeyDescribeLabelImage() {
        return keyDescribeLabelImage;
    }

    public ImageIcon getFocusStartBtnImage() {
        return focusStartBtnImage;
    }

    public ImageIcon getFocusStartItemBtnImage() {
        return focusStartItemBtnImage;
    }

    public ImageIcon getFocusSettingBtnImage() {
        return focusSettingBtnImage;
    }

    public ImageIcon getFocusScoreboardBtnImage() {
        return focusScoreboardBtnImage;
    }

    public ImageIcon getFocusExitBtnImage() {
        return focusExitBtnImage;
    }

    public int getButtonCount() {
        return buttonCount;
    }
}
