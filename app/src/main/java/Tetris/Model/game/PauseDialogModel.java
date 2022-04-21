package Tetris.Model.game;

import Tetris.Model.setting.SettingModel;

import javax.swing.*;
import java.util.Observable;

public class PauseDialogModel extends Observable {
    private static int indicator = 0;
    private final int buttonCount = 3;
    private final int width = 250;
    private final int height = 275;

    private final ImageIcon backgroundImage = new ImageIcon("app/src/main/resources/image/game/pause_dialog_background.png");
    private final ImageIcon emptyImage = new ImageIcon("app/src/main/resources/image/game/empty_image.png");
    private final ImageIcon restartBtnImage = new ImageIcon("app/src/main/resources/image/game/pause_dialog_button_restart.png");
    private final ImageIcon resumeBtnImage = new ImageIcon("app/src/main/resources/image/game/pause_dialog_button_resume.png");
    private final ImageIcon backMenuBtnImage = new ImageIcon("app/src/main/resources/image/game/pause_dialog_button_back_menu.png");

    private final ImageIcon focusRestartBtnImage = new ImageIcon("app/src/main/resources/image/game/pause_dialog_button_restart_focused.png");
    private final ImageIcon focusResumeBtnImage = new ImageIcon("app/src/main/resources/image/game/pause_dialog_button_resume_focused.png");
    private final ImageIcon focusBackMenuBtnImage = new ImageIcon("app/src/main/resources/image/game/pause_dialog_button_back_menu_focused.png");

    public void setIndicator(int indicator) {
        PauseDialogModel.indicator = indicator;
        setChanged();
        notifyObservers();
    }

    public static int getIndicator() {
        return indicator;
    }

    public int getButtonCount() {
        return buttonCount;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public ImageIcon getEmptyImage() {
        return emptyImage;
    }

    public ImageIcon getRestartBtnImage() {
        return restartBtnImage;
    }

    public ImageIcon getResumeBtnImage() {
        return resumeBtnImage;
    }

    public ImageIcon getBackMenuBtnImage() {
        return backMenuBtnImage;
    }

    public ImageIcon getFocusRestartBtnImage() {
        return focusRestartBtnImage;
    }

    public ImageIcon getFocusResumeBtnImage() {
        return focusResumeBtnImage;
    }

    public ImageIcon getFocusBackMenuBtnImage() {
        return focusBackMenuBtnImage;
    }
}

