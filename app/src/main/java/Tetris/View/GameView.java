package Tetris.View;

import Tetris.Model.Block;
import Tetris.Model.GameBoard;
import Tetris.Util.ColorTheme;

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
    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;
    public static final String BORDER_STRING = "X";

    private JTextPane gameView;
    private JTextPane preview;
    private JTextPane scoreView;
    private SimpleAttributeSet blockStyleSet;
    private SimpleAttributeSet defaultStyleSet;
    private GameBoard currentGame;
    private ColorTheme colorTheme = new ColorTheme();

    private int width = 350;
    private int height = 700;
    /**
     * Constructor
     */
    public GameView(int x, int y, GameBoard game) {
        super("SeoulTech SE Tetris");
        setSize(new Dimension(width, height));
        setLocation(x, y);
        this.currentGame = game;
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
        scoreView = new JTextPane();
        scoreView.setEditable(false);
        scoreView.setBackground(Color.BLACK);
        scoreView.setBorder(border2);
        this.getContentPane().add(scoreView, gbc[0]);

        // preview display setting
        gbc[1].gridx = 2;
        gbc[1].gridy = 0;
        gbc[1].gridwidth = 2;
        gbc[1].fill = GridBagConstraints.BOTH;
        preview = new JTextPane();
        preview.setEditable(false);
        preview.setBackground(Color.BLACK);
        preview.setBorder(border2);
        this.getContentPane().add(preview, gbc[1]);

        // Board display setting.
        gbc[2].gridx = 0;
        gbc[2].gridy = 2;
        gbc[2].gridwidth = 4;
        gbc[2].fill = GridBagConstraints.BOTH;
        gameView = new JTextPane();
        gameView.setEditable(false);
        gameView.setBackground(Color.BLACK);
        gameView.setBorder(border);
        this.getContentPane().add(gameView, gbc[2]);

        // Document scoreView style.
        defaultStyleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(defaultStyleSet, 16);
        StyleConstants.setFontFamily(defaultStyleSet, "Courier");
        StyleConstants.setBold(defaultStyleSet, true);
        StyleConstants.setForeground(defaultStyleSet, Color.WHITE);
        StyleConstants.setAlignment(defaultStyleSet, StyleConstants.ALIGN_CENTER);

        blockStyleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(blockStyleSet, 16);
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
        scoreView.setText(sb);
        StyledDocument doc = scoreView.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), defaultStyleSet, false);
        scoreView.setStyledDocument(doc);
    }
    /**
     * 블럭 미리보기 뷰 그리는 메서드
     */
    public void drawPreview(){
        preview.setText("");
        StyledDocument doc = preview.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "NEXT\n", defaultStyleSet);
            for(int y = 0; y < currentGame.getNext().height(); y++){
                for(int x = 0; x < currentGame.getNext().width(); x++){
                    if(currentGame.getNext().getShape(x, y) != -1){
                        StyleConstants.setForeground(blockStyleSet, colorTheme.getColor(currentGame.getNext().getNumber()));
                        doc.insertString(doc.getLength(), "O", blockStyleSet);
                    }
                    else {
                        doc.insertString(doc.getLength(), " ", defaultStyleSet);
                    }
                }
                doc.insertString(doc.getLength(), "\n", defaultStyleSet);
            }
            for(int y = 0; y < 3 - currentGame.getNext().height(); y++){
                doc.insertString(doc.getLength(), "\n", defaultStyleSet);
            }
        } catch (BadLocationException e) { }
        preview.setStyledDocument(doc);
    }
    /**
     * 뷰 그리는 메서드
     * visited: 현재 배치된 블럭의 번호들을 저장
     */
    public void drawBoard() {
        int[][] board = currentGame.getBoard();
        int[][] visited = currentGame.getVisited();
        gameView.setText("");
        StyledDocument doc = gameView.getStyledDocument();
        try {
            for(int t = 0; t < WIDTH + 2; t++) {
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
                        doc.insertString(doc.getLength(), " ", defaultStyleSet);
                    }
                }
                doc.insertString(doc.getLength(), BORDER_STRING, defaultStyleSet);
                doc.insertString(doc.getLength(), "\n", defaultStyleSet);
            }
            for(int t = 0; t < WIDTH + 2; t++) {
                doc.insertString(doc.getLength(), BORDER_STRING, defaultStyleSet);
            }
        } catch (BadLocationException e) { }
        gameView.setStyledDocument(doc);
    }
    /**
     * Observer Pattern Method
     */
    @Override
    public void update(Observable o, Object arg) {
        placeBlock();
        drawScoreView();
        drawPreview();
        drawBoard();
    }
}