package Tetris.Model.scoreboard;

import Tetris.Util.GameType;
import Tetris.Util.JsonReader;
import Tetris.Util.ScoreboardJsonKeyType;

import javax.swing.*;
import java.util.List;

public class ScoreboardModel {
    private final int screenWidth = JsonReader.getWidth();
    private final int screenHeight = JsonReader.getHeight();
    private final ImageIcon backgroundImage350700 = new ImageIcon("app/src/main/resources/image/default_background_350_700.png");
    private final ImageIcon backgroundImage400800 = new ImageIcon("app/src/main/resources/image/default_background_400_800.png");
    private final ImageIcon backgroundImage300600 = new ImageIcon("app/src/main/resources/image/default_background_300_600.png");

    private final ImageIcon basicModeTitleImage = new ImageIcon("app/src/main/resources/image/scoreboard/basic_mode_scoreboard_title.png");
    private final ImageIcon itemModeTitleImage = new ImageIcon("app/src/main/resources/image/scoreboard/item_mode_scoreboard_title.png");
    private final ImageIcon scoreboardBackground = new ImageIcon("app/src/main/resources/image/scoreboard/scoreboard_background.png");
    private final ImageIcon backBtnImage = new ImageIcon("app/src/main/resources/image/scoreboard/button_back.png");

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public List<ScoreModel> getScoreboard(GameType type) {
        return JsonReader.getScoreBoard(type);
    }
    public ImageIcon getBackBtnImage() {
        return backBtnImage;
    }
    public ImageIcon getBasicModeTitleImage() {
        return basicModeTitleImage;
    }
    public ImageIcon getItemModeTitleImage() {
        return itemModeTitleImage;
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
