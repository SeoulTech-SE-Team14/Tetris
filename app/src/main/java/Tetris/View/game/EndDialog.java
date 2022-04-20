package Tetris.View.game;

import Tetris.Model.game.EndDialogModel;
import Tetris.Model.game.GameModel;
import Tetris.Model.game.PauseDialogModel;
import Tetris.Model.game.Player;
import Tetris.Model.scoreboard.ScoreModel;
import Tetris.Util.GameType;
import Tetris.Util.ScoreboardJsonKeyType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class EndDialog extends JDialog implements Observer {
    private static final String BLANK_STRING = " ";
    private static final String ZERO_WIDTH_SPACE = "​";

    private final EndDialogModel model = new EndDialogModel();

    private final JButton backMenuBtn = new JButton(model.getBackMenuBtnImage());
    private final JButton exitBtn = new JButton(model.getExitBtnImage());
    private final int width = model.getWidth();
    private final int height = model.getHeight();

    private final GameModel gameModel;
    private final JPanel scoreboard;
    private final Player player;
    private List<ScoreModel> scoreboardList;
    public EndDialog(JFrame frame, GameModel gameModel, int x, int y) {
        super(frame, "게임종료", true);

        this.gameModel = gameModel;
        this.player = gameModel.getGameState().getPlayer();

        setSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setLocation(x, y);
        setLayout(null);

        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                g.drawImage(model.getBackgroundImage().getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        setContentPane(background);

        scoreboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                g.drawImage(model.getScoreboardBackground().getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        scoreboard.setLayout(new GridBagLayout());
        scoreboardList = model.getScoreboard(gameModel.getGameState().getGameMode());
        scoreboard.setPreferredSize(new Dimension(250, 350));
        try {
            for(int i = 0; i < scoreboardList.size(); i++){
                ScoreModel scoreInfo = scoreboardList.get(i);
                String difficulty = ZERO_WIDTH_SPACE;
                if(gameModel.getGameState().getGameMode() == GameType.BASIC_MODE) difficulty = scoreInfo.getDifficulty()+ "모드";
                String str = Integer.toString(i + 1) + "등" + "\t" + scoreInfo.getName() + "\t" + scoreInfo.getScore() + "\t" + difficulty;
                JLabel score = new JLabel(str);
                if(Objects.equals(player.getName(), scoreInfo.getName()) && player.getScore() == scoreInfo.getScore()){
                    if(player.getMode() == GameType.ITEM_MODE || Objects.equals(player.getDifficulty().getKey(), scoreInfo.getDifficulty())){
                        score.setFont(new Font(Font.SERIF, Font.BOLD, 11));
                    } else {
                        score.setFont(new Font(Font.SERIF, Font.PLAIN, 11));
                    }
                } else {
                    score.setFont(new Font(Font.SERIF, Font.PLAIN, 11));
                }
                scoreboard.add(score, gbc);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        background.add(scoreboard, gbc);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setLayout(new GridLayout(model.getButtonCount(), 1));

        backMenuBtn.setBorderPainted(false);
        backMenuBtn.setContentAreaFilled(false);
        backMenuBtn.setFocusPainted(false);
        backMenuBtn.setOpaque(false);
        buttons.add(backMenuBtn);

        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false);
        exitBtn.setOpaque(false);
        buttons.add(exitBtn);
        add(buttons, gbc);

        setResizable(false);
        setFocusable(true);
        requestFocus();
    }
    public void setActionListener(ActionListener listener){
        backMenuBtn.addActionListener(listener);
        exitBtn.addActionListener(listener);
    }
    @Override
    public void paint(Graphics g) {
        int indicator = model.getIndicator();
        if(indicator == 0){
            backMenuBtn.setIcon(model.getFocusBackMenuBtnImage());
        } else {
            backMenuBtn.setIcon(model.getBackMenuBtnImage());
        }
        if(indicator == 1){
            exitBtn.setIcon(model.getFocusExitBtnImage());
        } else {
            exitBtn.setIcon(model.getExitBtnImage());
        }
        pack();
        super.paint(g);
    }
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
