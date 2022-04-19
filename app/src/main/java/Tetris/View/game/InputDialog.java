package Tetris.View.game;

import Tetris.Model.game.GameModel;
import Tetris.Model.game.Player;
import Tetris.Util.GameType;

import javax.swing.*;
import java.awt.*;

public class InputDialog extends JDialog {
    private JTextField nameField = new JTextField(10);
    private JButton okBtn =new JButton("입력");
    private GameModel gameModel;
    private String name;
    public InputDialog(Frame owner, GameModel gameModel, int x, int y) {
        super(owner, "새로운 기록!", true);
        this.gameModel = gameModel;

        setSize(200, 100);
        setLocation(x, y);
        setResizable(false);
        setLayout(new FlowLayout());
        nameField.addActionListener(e -> {
            name = nameField.getText();
            dispose();
        });
        add(nameField);
        okBtn.addActionListener(e -> {
            updateScore();
            dispose();
        });
        add(okBtn);
    }
    public void updateScore(){
        int score = gameModel.getGameState().getScore();
        switch (gameModel.getGameState().getGameMode()) {
            case BASIC_MODE:
                gameModel.getGameState().setPlayer(new Player(name, score, GameType.BASIC_MODE, gameModel.getGameState().getDifficulty()));
                break;
            case ITEM_MODE:
                gameModel.getGameState().setPlayer(new Player(name, score, GameType.BASIC_MODE));
                break;
            default:
                break;
        }
    }
}
