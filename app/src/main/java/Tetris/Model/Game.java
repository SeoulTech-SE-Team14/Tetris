package Tetris.Model;

import Tetris.Manager.GameManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * 테트리스 게임 Model
 * @author 김영균
 */
public class Game extends Observable {
    private final int HEIGHT = 20;
    private final int WIDTH = 10;

    private Block curr = null;
    private Block next = null;

    private int[][] board;
    private int[][] visited;
    private int score = 0;

    GameManager manager;

    /**
     * Constructor
     */
    public Game(int row, int col) {
        manager = new GameManager();
        board = new int[row][col];
        visited = new int[row][col];
        for(int y = 0; y < row; y++){
            Arrays.fill(visited[y], -1);
            Arrays.fill(board[y], -1);
        }
    }

    /**
     * Getter Method
     */
    public int getScore() {
        return score;
    }
    public int[][] getVisited() {
        return visited;
    }
    public int[][] getBoard() {
        return board;
    }
    public Block getCurr() {
        return curr;
    }
    public Block getNext() {
        return next;
    }

    /**
     * Setter Method
     */
    public void setBoard(int x, int y, int cx, int cy){
        board[y][x] = curr.getShape(cx, cy);
    }

    /**
     * 블럭 생성 가능 확인
     * @param cur 셍성된 블럭 종류
     * @return 블럭을 생성할 수 있는지 없는지
     */
    public boolean spawn(Block cur){
        if(next == null){
            next = cur;
            notice();
            return true;
        }
        this.curr = next;
        this.next = cur;
        if(roomExist(cur.x, cur.y)) {
            manager.setSpawnTime();
            manager.updateSpawnedBlockNumber();
            notice();
            return true;
        } else {
            notice();
            return false;
        }
    }

    /**
     * 다음 이동할 좌표에 공간이 있는지 체크하는 메서드
     * @param nx 이동할 x좌표
     * @param ny 이동할 y 좌표
     * @return 공간이 있는지 여부
     */
    private boolean roomExist(int nx, int ny) {
        if(ny + curr.height() > HEIGHT) return false;
        if(nx < 0 || nx + curr.width() > WIDTH) return false;
        for(int row = ny, cy = 0; row < ny + curr.height(); row++, cy++) {
            for (int col = nx, cx = 0; col < nx + curr.width(); col++, cx++) {
                if(curr.getShape(cx, cy) != -1 && visited[row][col] != -1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 배치할 블럭 방문 체크하는 메서드
     */
    private void visitBlock() {
        for(int row = curr.y, cy = 0; row < curr.y + curr.height(); row++, cy++) {
            for(int col = curr.x, cx = 0; col < curr.x + curr.width(); col++, cx++) {
                if(curr.getShape(cx, cy) == -1) continue;
                visited[row][col] = curr.getNumber();
            }
        }
    }

    /**
     * observer pattern method
     */
    private void notice() {
        setChanged();
        notifyObservers();
    }

    /**
     * 현재 위치 보드 지우기
     */
    public void eraseCurr()  {
        for(int row = 0; row < HEIGHT; row++){
            Arrays.fill(board[row], -1);
        }
    }

    /**
     * 가중치 아이템 블럭이 내려갈 때 모두 지우는 메서드
     */
    public void weightBlockErase() {
        for(int row = curr.getY(); row < curr.getY() + curr.height(); row++) {
            for(int col = curr.getX(); col < curr.getX() + curr.width(); col++) {
                visited[row][col] = -1;
            }
        }
    }

    /**
     * 지워야하는 줄 찾는 메서드
     * @return 지워야하는 줄의 y좌표 배열
     */
    public List<Integer> findEraseLine(){
        List<Integer> pos = new ArrayList<>();
        for(int row = HEIGHT - 1; row >= 0; row--){
            boolean finds = true;
            for(int col = 0; col < WIDTH; col++){
                if(visited[row][col] == -1){
                    finds = false;
                    break;
                }
            }
            if(finds) pos.add(row);
        }
        return pos;
    }

    /**
     * 줄 지우기 메서드
     */
    public void eraseLine(){
        List<Integer> lines = findEraseLine();
        for(int i = 0; i < lines.size(); i++){
            // 지울 수록 한 줄씩 내려가니까 + i 해주어야 바뀐 라인.
            eraseLine(lines.get(i) + i);
        }
    }

    /**
     * 라인 지우는 함수, +5점.
     * @param line 지울 라인
     */
    public void eraseLine(int line) {
        for(int prevRow = line - 1; prevRow >= 0; prevRow--){
            System.arraycopy(visited[prevRow], 0, visited[prevRow + 1], 0, visited[prevRow + 1].length);
            System.arraycopy(board[prevRow], 0, board[prevRow + 1], 0, board[prevRow + 1].length);
        }
        Arrays.fill(visited[0], -1);
        Arrays.fill(board[0], -1);
        manager.updateDeletedLineNumber();
        score += 5;
    }

    /**
     * Weight Block 떨어질 때 호출되는 함수
     */
    public void weightBlockDown(){
        eraseCurr();
        weightBlockErase();
        if(curr.y + curr.height() < HEIGHT ) {
            curr.y++;
            notice();
        }
        else {
            curr = null;
        }
    }

    /**
     * 블럭 이동 메서드
     */
    public void moveDown() {
        eraseCurr();
        if(roomExist(curr.x, curr.y + 1)) {
            curr.y++;
            notice();
        }
        else {
            visitBlock();
            eraseLine();
            score += (1 + manager.getBonusScore());
            notice();
            curr = null;

        }
    }
    public void moveRight() {
        eraseCurr();
        if(roomExist(curr.x + 1, curr.y)) curr.x++;
        notice();
    }
    public void moveLeft() {
        eraseCurr();
        if(roomExist(curr.x - 1, curr.y)) curr.x--;
        notice();
    }
    public void fall() {
        eraseCurr();
        while(roomExist(curr.x, curr.y + 1)) curr.y++;
        visitBlock();
        eraseLine();
        score += (1 + manager.getBonusScore());
        notice();
        curr = null;
    }

    /**
     * @return 돌렸을 때 오른쪽 경계선을 넘어가는지 아닌지
     */
    public boolean canRotate() {
        int dx = curr.height() - 1 - curr.getCy() - curr.getCx();
        int dy = curr.getCx() - curr.getCy();
        int afterX = curr.getX() - dx;
        int afterY = curr.getY() - dy;
        int afterWidth = curr.height();
        int afterHeight = curr.width();
        if(afterY < 0 || afterY + afterHeight > HEIGHT) return false;
        if(afterX < 0 || afterX + afterWidth > WIDTH) return false;
        for(int row = 0, cy = 0; row < curr.height(); row++, cy++){
            for(int col = 0, cx = 0; col < curr.width(); col++, cx++){
                if(curr.getShape(cx, cy) != -1 && visited[col + afterY][curr.height() - 1- row + afterX] != -1){
                    return false;
                }
            }
        }
        return true;
    }
    public void rotateBlock() { curr.rotate(); }
}