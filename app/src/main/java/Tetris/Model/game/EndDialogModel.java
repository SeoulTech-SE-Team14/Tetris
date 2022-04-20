package Tetris.Model.game;

import Tetris.Model.scoreboard.ScoreModel;
import Tetris.Util.GameType;
import Tetris.Util.JsonReader;
import Tetris.Util.ScoreboardJsonKeyType;

import javax.swing.*;
import java.util.List;
import java.util.Observable;

public class EndDialogModel extends Observable {
    private static int indicator = 0;
    private final int width = 300;
    private final int height = 475;
    private final int buttonCount = 2;

    private final ImageIcon backgroundImage = new ImageIcon("app/src/main/resources/image/game/end_dialog_background.png");
    private final ImageIcon scoreboardBackground = new ImageIcon("app/src/main/resources/image/game/dialog_background_250350.png");
    private final ImageIcon backMenuBtnImage = new ImageIcon("app/src/main/resources/image/game/end_dialog_button_back_menu.png");
    private final ImageIcon exitBtnImage = new ImageIcon("app/src/main/resources/image/game/end_dialog_button_exit.png");

    private final ImageIcon focusBackMenuBtnImage = new ImageIcon("app/src/main/resources/image/game/end_dialog_button_back_menu_focused.png");
    private final ImageIcon focusExitBtnImage = new ImageIcon("app/src/main/resources/image/game/end_dialog_button_exit_focused.png");

    public void setIndicator(int indicator) {
        EndDialogModel.indicator = indicator;
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

    public List<ScoreModel> getScoreboard(GameType type) {
        return JsonReader.getScoreBoard(type);
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public ImageIcon getScoreboardBackground() {
        return scoreboardBackground;
    }

    public ImageIcon getBackMenuBtnImage() {
        return backMenuBtnImage;
    }

    public ImageIcon getExitBtnImage() {
        return exitBtnImage;
    }

    public ImageIcon getFocusBackMenuBtnImage() {
        return focusBackMenuBtnImage;
    }

    public ImageIcon getFocusExitBtnImage() {
        return focusExitBtnImage;
    }
}
