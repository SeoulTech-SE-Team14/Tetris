package Tetris.Model;

import javax.swing.*;
import java.util.Observable;

public class StartBoard extends Observable {
    private static int indicator = 0;

    private final int buttonCount = 4;
    private final int buttonWidth = 260;
    private final int buttonHeight = 60;

    // default image
    private final ImageIcon defaultStartBtnImage = new ImageIcon("app/src/main/java/Tetris/Assets/image/button_gamestart.png");
    private final ImageIcon defaultSettingBtnImage = new ImageIcon("app/src/main/java/Tetris/Assets/image/button_setting.png");
    private final ImageIcon defaultScoreboardBtnImage = new ImageIcon("app/src/main/java/Tetris/Assets/image/button_scoreboard.png");
    private final ImageIcon defaultExitBtnImage = new ImageIcon("app/src/main/java/Tetris/Assets/image/button_exit.png");

    // focus image
    private final ImageIcon focusStartBtnImage = new ImageIcon("app/src/main/java/Tetris/Assets/image/button_gamestart_focused.png");
    private final ImageIcon focusSettingBtnImage = new ImageIcon("app/src/main/java/Tetris/Assets/image/button_setting_focused.png");
    private final ImageIcon focusScoreboardBtnImage = new ImageIcon("app/src/main/java/Tetris/Assets/image/button_scoreboard_focused.png");
    private final ImageIcon focusExitBtnImage = new ImageIcon("app/src/main/java/Tetris/Assets/image/button_exit_focused.png");

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

    public ImageIcon getDefaultStartBtnImage() {
        return defaultStartBtnImage;
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

    public ImageIcon getFocusStartBtnImage() {
        return focusStartBtnImage;
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
