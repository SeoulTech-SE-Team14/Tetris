package Tetris.Model.game;

import javax.swing.*;
import java.util.Observable;

public class SelectDialogModel extends Observable {
    private static int indicator = 0;
    private final int buttonCount = 4;
    private final int width = 250;
    private final int height = 375;

    private final ImageIcon backgroundImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_background.png");
    private final ImageIcon emptyImage = new ImageIcon("app/src/main/resources/image/game/empty_image.png");
    private final ImageIcon defaultNormalModeBtnImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_button_normal.png");
    private final ImageIcon defaultEasyModeBtnImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_button_easy.png");
    private final ImageIcon defaultHardModeBtnImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_button_hard.png");
    private final ImageIcon defaultBackBtnImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_button_back.png");

    private final ImageIcon focusNormalModeBtnImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_button_normal_focused.png");
    private final ImageIcon focusEasyModeBtnImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_button_easy_focused.png");
    private final ImageIcon focusHardModeBtnImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_button_hard_focused.png");
    private final ImageIcon focusBackBtnImage = new ImageIcon("app/src/main/resources/image/game/game_dialog_button_back_focused.png");

    public void setIndicator(int indicator) {
        SelectDialogModel.indicator = indicator;
        setChanged();
        notifyObservers();
    }

    public static int getIndicator() {
        return indicator;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getButtonCount() {
        return buttonCount;
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public ImageIcon getEmptyImage() {
        return emptyImage;
    }

    public ImageIcon getDefaultNormalModeBtnImage() {
        return defaultNormalModeBtnImage;
    }

    public ImageIcon getDefaultEasyModeBtnImage() {
        return defaultEasyModeBtnImage;
    }

    public ImageIcon getDefaultHardModeBtnImage() {
        return defaultHardModeBtnImage;
    }

    public ImageIcon getDefaultBackBtnImage() {
        return defaultBackBtnImage;
    }

    public ImageIcon getFocusNormalModeBtnImage() {
        return focusNormalModeBtnImage;
    }

    public ImageIcon getFocusEasyModeBtnImage() {
        return focusEasyModeBtnImage;
    }

    public ImageIcon getFocusHardModeBtnImage() {
        return focusHardModeBtnImage;
    }

    public ImageIcon getFocusBackBtnImage() {
        return focusBackBtnImage;
    }
}
