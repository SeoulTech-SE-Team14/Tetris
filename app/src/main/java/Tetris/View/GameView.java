package Tetris.View;

import Tetris.Model.Block;
import Tetris.Model.GameBoard;
import Tetris.Model.ColorTheme;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.*;

/**
 * 테트리스 게임 View
 * @author 김영균
 */
public class GameView extends JFrame implements Observer {
    public static final int GAME_HEIGHT = 20;
    public static final int GAME_WIDTH = 10;
    public static final String BORDER_STRING = "X";
    public static final String BLANK_STRING = " ";
    public static final String ZERO_WIDTH_SPACE = "​";
    private final ColorTheme colorTheme = new ColorTheme();

    private JTextPane gamePane;
    private JTextPane previewPane;
    private JTextPane scorePane;
    private SimpleAttributeSet blockStyleSet;
    private SimpleAttributeSet defaultStyleSet;
    private GameBoard currentGame;

    /**
     * Constructor
     */
    public GameView(int x, int y, GameBoard game) {
        super("SeoulTech SE Tetris");
        this.currentGame = game;

        setSize(new Dimension(currentGame.getScreenWidth(), currentGame.getScreenHeight()));
        setLocation(x, y);
        this.getContentPane().setBackground(Color.BLACK);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        CompoundBorder border2 = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 10),
                BorderFactory.createLineBorder(Color.BLACK, 5));

        GridBagConstraints[] gbc = new GridBagConstraints[3];
        this.getContentPane().setLayout(new GridBagLayout());
        for(int i = 0; i < 3; i++){ gbc[i] = new GridBagConstraints(); }

        // scoreView display setting.
        gbc[0].gridx = 0;
        gbc[0].gridy = 0;
        gbc[0].gridwidth = 2;
        gbc[0].fill = GridBagConstraints.BOTH;
        scorePane = new JTextPane();
        scorePane.setEditable(false);
        scorePane.setBackground(Color.BLACK);
        scorePane.setBorder(border2);
        this.getContentPane().add(scorePane, gbc[0]);

        // preview display setting
        gbc[1].gridx = 2;
        gbc[1].gridy = 0;
        gbc[1].gridwidth = 2;
        gbc[1].fill = GridBagConstraints.BOTH;
        previewPane = new JTextPane();
        previewPane.setEditable(false);
        previewPane.setBackground(Color.BLACK);
        previewPane.setBorder(border2);
        this.getContentPane().add(previewPane, gbc[1]);

        // Board display setting.
        gbc[2].gridx = 0;
        gbc[2].gridy = 2;
        gbc[2].gridwidth = 4;
        gbc[2].fill = GridBagConstraints.BOTH;
        gamePane = new JTextPane();
        gamePane.setEditable(false);
        gamePane.setBackground(Color.BLACK);
        gamePane.setBorder(border);
        this.getContentPane().add(gamePane, gbc[2]);

        // Document scoreView style.
        defaultStyleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(defaultStyleSet, currentGame.getFontSize());
        StyleConstants.setFontFamily(defaultStyleSet, "Courier");
        StyleConstants.setBold(defaultStyleSet, true);
        StyleConstants.setForeground(defaultStyleSet, Color.WHITE);
        StyleConstants.setAlignment(defaultStyleSet, StyleConstants.ALIGN_CENTER);

        blockStyleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(blockStyleSet, currentGame.getFontSize());
        StyleConstants.setFontFamily(blockStyleSet, "Courier");
        StyleConstants.setBold(blockStyleSet, true);
        StyleConstants.setAlignment(blockStyleSet, StyleConstants.ALIGN_CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        requestFocus();
    }

    /**
     * 움직이고 있는 블럭 위치 추적 메서드
     */
    public void placeBlock() {
        Block curr = currentGame.getCurr();
        if(curr == null) return;
        int x = curr.getX();
        int y = curr.getY();
        for(int row = y, cy = 0; row < y + curr.height(); row++, cy++){
            for(int col = x, cx = 0; col < x + curr.width(); col++, cx++){
                currentGame.setBoard(col, row, cx, cy);
            }
        }
    }
    /**
     * 스코어 뷰 그리는 메서드
     */
    public void drawScoreView() {
        String sb = "점수\n" + currentGame.getGameState().getScore();
        scorePane.setText(sb);
        StyledDocument doc = scorePane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), defaultStyleSet, false);
        scorePane.setStyledDocument(doc);
    }
    /**
     * 블럭 미리보기 뷰 그리는 메서드
     */
    public void drawPreview(){
        previewPane.setText(ZERO_WIDTH_SPACE);
        StyledDocument doc = previewPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "NEXT\n", defaultStyleSet);
            for(int y = 0; y < currentGame.getNext().height(); y++){
                for(int x = 0; x < currentGame.getNext().width(); x++){
                    if(currentGame.getNext().getShape(x, y) != -1){
                        StyleConstants.setForeground(blockStyleSet, colorTheme.getColor(currentGame.getNext().getNumber()));
                        doc.insertString(doc.getLength(), "O", blockStyleSet);
                    }
                    else {
                        doc.insertString(doc.getLength(), BLANK_STRING, defaultStyleSet);
                    }
                }
                doc.insertString(doc.getLength(), "\n", defaultStyleSet);
            }
            for(int y = 0; y < 3 - currentGame.getNext().height(); y++){
                doc.insertString(doc.getLength(), "\n", defaultStyleSet);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        previewPane.setStyledDocument(doc);
    }
    /**
     * 뷰 그리는 메서드
     * visited: 현재 배치된 블럭의 번호들을 저장
     */
    public void drawBoard() {
        int[][] board = currentGame.getBoard();
        int[][] visited = currentGame.getVisited();
        gamePane.setText(ZERO_WIDTH_SPACE);
        StyledDocument doc = gamePane.getStyledDocument();
        try {
            for(int t = 0; t < GAME_WIDTH + 2; t++) {
                doc.insertString(doc.getLength(), BORDER_STRING, defaultStyleSet);
            }
            doc.insertString(doc.getLength(), "\n", defaultStyleSet);
            for(int i = 0; i < board.length; i++) {
                doc.insertString(doc.getLength(), BORDER_STRING, defaultStyleSet);
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != -1 || visited[i][j] != -1) {
                        int blockNumber = Math.max(board[i][j], visited[i][j]);
                        StyleConstants.setForeground(blockStyleSet, colorTheme.getColor(blockNumber));
                        doc.insertString(doc.getLength(), "O", blockStyleSet);
                    } else {
                        doc.insertString(doc.getLength(), BLANK_STRING, defaultStyleSet);
                    }
                }
                doc.insertString(doc.getLength(), BORDER_STRING, defaultStyleSet);
                doc.insertString(doc.getLength(), "\n", defaultStyleSet);
            }
            for(int t = 0; t < GAME_WIDTH + 2; t++) {
                doc.insertString(doc.getLength(), BORDER_STRING, defaultStyleSet);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        gamePane.setStyledDocument(doc);
    }
    @Override
    public void update(Observable o, Object arg) {
        placeBlock();
        drawScoreView();
        drawPreview();
        drawBoard();
    }
}