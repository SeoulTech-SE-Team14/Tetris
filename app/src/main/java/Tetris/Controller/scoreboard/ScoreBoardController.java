package Tetris.Controller.scoreboard;

import Tetris.Controller.home.StartMenuViewController;

import Tetris.Model.home.StartMenuModel;
import Tetris.Model.scoreboard.ScoreModel;
import Tetris.Model.scoreboard.ScoreboardModel;

import Tetris.Util.GameType;
import Tetris.Util.JsonWriter;
import Tetris.Util.ScoreboardJsonKeyType;
import Tetris.View.home.StartMenuView;
import Tetris.View.scoreboard.ScoreboardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class ScoreboardController implements KeyListener, ActionListener {
    private final ScoreboardModel model;
    private final ScoreboardView scoreBoardView;

    public ScoreboardController(ScoreboardModel model, ScoreboardView view) {
        this.model = model;
        this.scoreBoardView = view;
        this.scoreBoardView.setActionListener(this);
    }

    public void navigatePreviousView(){
        StartMenuModel field = new StartMenuModel();
        StartMenuView startView = new StartMenuView(scoreBoardView.getLocation().x, scoreBoardView.getLocation().y);
        StartMenuViewController controller = new StartMenuViewController(field, startView);
        field.addObserver(startView);
        startView.addKeyListener(controller);
        scoreBoardView.dispose();
    }
    public void addScore(GameType type, int score, String name, GameType difficulty){
        List<ScoreModel> scoreboard = model.getScoreboard(type);
        scoreboard.add(new ScoreModel(score, name));
        scoreboard.sort(ScoreModel::compareTo);
        List<Map<String, String>> scoreboardJsonArray = new ArrayList<>();
        for(ScoreModel scoreObj : scoreboard) {
            Map<String, String> info = new HashMap<>();
            info.put(ScoreboardJsonKeyType.SCORE.getKey(), Integer.toString(scoreObj.getScore()));
            info.put(ScoreboardJsonKeyType.NAME.getKey(), scoreObj.getName());
            info.put(ScoreboardJsonKeyType.DIFFICULTY.getKey(), difficulty.getKey());
            scoreboardJsonArray.add(info);
        }
        JsonWriter.setScoreBoard(scoreboardJsonArray, type);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // default implementation ignored
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                navigatePreviousView();
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // default implementation ignored
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().toString().contains("button_back")){
            navigatePreviousView();
        }
    }
}
