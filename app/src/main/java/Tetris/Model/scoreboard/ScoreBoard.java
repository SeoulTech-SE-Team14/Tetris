package Tetris.Model.scoreboard;

import Tetris.Util.JsonReader;

import javax.swing.*;

public class ScoreBoard{
    private final int screenWidth = JsonReader.getWidth();
    private final int screenHeight = JsonReader.getHeight();

    private final ImageIcon backgroundImage350700 = new ImageIcon("app/src/main/resources/image/default_background_350_700.png");
    private final ImageIcon backgroundImage400800 = new ImageIcon("app/src/main/resources/image/default_background_400_800.png");
    private final ImageIcon backgroundImage300600 = new ImageIcon("app/src/main/resources/image/default_background_300_600.png");

    private final ImageIcon titleImage = new ImageIcon("app/src/main/resources/image/scoreboard/scoreboard_title.png");
    private final ImageIcon scoreboardBackground = new ImageIcon("app/src/main/resources/image/scoreboard/scoreboard_background.png");
    private final ImageIcon focusBackBtnImage = new ImageIcon("app/src/main/resources/image/button_back_focused.png");

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }

    public ImageIcon getFocusBackBtnImage() {
        return focusBackBtnImage;
    }
    public ImageIcon getTitleImage() {
        return titleImage;
    }
    public ImageIcon getScoreboardBackground() {
        return scoreboardBackground;
    }
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
}
