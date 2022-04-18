package Tetris.Model.game;

import Tetris.Model.block.Block;
import Tetris.Model.block.WeightBlock;
import Tetris.Util.JsonReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import static Tetris.Model.game.GameStateModel.updateDeletedLineNumber;

/**
 * 테트리스 게임 Model
 * @author 김영균
 */
public class GameModel extends Observable {
    private Block curr = null;
    private Block next = null;
    private GameStateModel gameState;

    private int[][] board;
    private int[][] visited;
    private final int height;
    private final int width;
    private final int screenWidth;
    private final int screenHeight;
    /**
     * Constructor
     */
    public GameModel(GameStateModel gameState, int row, int col) {
        this.gameState = gameState;
        this.height = row;
        this.width = col;
        this.screenWidth = JsonReader.getWidth();
        this.screenHeight = JsonReader.getHeight();

        board = new int[row][col];
        visited = new int[row][col];
        for(int y = 0; y < row; y++){
            Arrays.fill(visited[y], -1);
            Arrays.fill(board[y], -1);
        }
    }

    public int getFontSize() {
        if(screenWidth == 300 && screenHeight == 600) {
            return 15;
        }
        else if(screenWidth == 350 && screenHeight == 700) {
            return 18;
        }
        else{
            return 20;
        }
    }
    public int getScreenWidth() { return screenWidth; }
    public int getScreenHeight() { return screenHeight; }
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

    public GameStateModel getGameState() {
        return gameState;
    }

    /**
     * board 배열에 블럭 위치 표시하는 메서드.
     * @param x board x좌표
     * @param y board y좌표
     * @param bx block 배열의 x좌표
     * @param by block 배열의 y좌표
     */
    public void setBoard(int x, int y, int bx, int by){
        board[y][x] = curr.getShape(bx, by);
    }

    /**
     * 블럭 생성 가능 여부 확인 후 블럭 생성하는 메서드.
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
        if(roomExist(cur.getX(), cur.getY())) {
            gameState.setSpawnTime();
            GameStateModel.updateSpawnedBlockNumber();
            notice();
            return true;
        } else {
            notice();
            return false;
        }
    }

    /**
     * 다음 이동할 좌표에 공간이 있는지 체크하는 메서드.
     * @param nx 이동할 x좌표
     * @param ny 이동할 y 좌표
     * @return 공간이 있는지 여부
     */
    public boolean roomExist(int nx, int ny) {
        if(isOutsideBoundary(nx, ny)) return false;
        return !isNeighborExist(nx, ny);
    }
    public boolean isOutsideBoundary(int nx, int ny) {
        return (ny + curr.height() > height) || (nx < 0 || nx + curr.width() > width);
    }
    public boolean isNeighborExist(int nx, int ny){
        for(int row = ny, cy = 0; row < ny + curr.height(); row++, cy++) {
            for (int col = nx, cx = 0; col < nx + curr.width(); col++, cx++) {
                if(curr.getShape(cx, cy) != -1 && visited[row][col] != -1) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 배치할 블럭 방문 체크하는 메서드
     * 현재 블럭 배열이 1, 2이면 -> 색칠을 위해 블럭 넘버를 저장
     * 아이템일 경우 예시)
     * 라인삭제아이템이면 11(아이템 개수) * 아이템 넘버 (3) + 아이템의 블럭 넘버 (8)을 저장
     */
    public void visitBlock() {
        for(int row = curr.getY(), cy = 0; row < curr.getY() + curr.height(); row++, cy++) {
            for(int col = curr.getX(), cx = 0; col < curr.getX() + curr.width(); col++, cx++) {
                if(curr.getShape(cx, cy) == -1) continue;
                visited[row][col] = curr.getNumber();
            }
        }
    }
    /**
     * 옵저버 관련 메서드.
     */
    public void notice() {
        setChanged();
        notifyObservers();
    }
    /**
     * 보드 지우기
     */
    public void eraseCurr()  {
        for(int row = 0; row < height; row++){
            Arrays.fill(board[row], -1);
        }
    }
    /**
     * 가중치 아이템이 내려갈 때 경로에 있는 블럭 지우는 메서드
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
        for(int row = height - 1; row >= 0; row--){
            boolean finds = true;
            for(int col = 0; col < width; col++){
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
     * 줄 지우는 메서드.
     * 줄 지울 시 점수 +5점
     * @param line 지울 라인
     */
    public void eraseLine(int line) {
        for(int prevRow = line - 1; prevRow >= 0; prevRow--){
            System.arraycopy(visited[prevRow], 0, visited[prevRow + 1], 0, visited[prevRow + 1].length);
            System.arraycopy(board[prevRow], 0, board[prevRow + 1], 0, board[prevRow + 1].length);
        }
        Arrays.fill(visited[0], -1);
        Arrays.fill(board[0], -1);
        updateDeletedLineNumber();
        gameState.updateScore(5);
    }
    /**
     * 블럭 이동 메서드
     */
    public void moveDown() {
        eraseCurr();
        // 무게추 아이템일 때
        if(curr instanceof WeightBlock) {
            weightBlockErase();
            if (curr.getY() + curr.height() < height) {
                curr.setY(curr.getY() + 1);
            } else {
                curr = null;
            }
        }
        else {
            // 일반 다른 블럭일 때
            if(roomExist(curr.getX(), curr.getY() + 1)) {
                curr.setY(curr.getY() + 1);
            }
            else {
                visitBlock();
                eraseLine();
                gameState.updateScore(1 + gameState.getBonusScore());
                curr = null;
            }
        }
        notice();
    }
    public void moveRight() {
        // 게임 판 밖으로 이동 확인
        if(isOutsideBoundary(curr.getX() + 1, curr.getY())) return;
        // 무게추 아이템일 경우 이전에 다른 블럭을 만났는지 학인
        if(curr instanceof  WeightBlock && ((WeightBlock) curr).isOverlapped()) return ;
        eraseCurr();
        // 이동할 위치에 주변에 이웃한 블럭이 없으면 이동.
        if(!isNeighborExist(curr.getX() + 1, curr.getY())) {
            curr.setX((curr.getX() + 1));
        } else {
            // 이동을 못하는데 무게추 아이템이면 앞으로 못 움직이도록 바꿈
            if(curr instanceof WeightBlock){
                ((WeightBlock) curr).setOverlapped(true);
            }
        }
        notice();
    }
    public void moveLeft() {
        // 게임 판 밖으로 이동 확인
        if(isOutsideBoundary(curr.getX() - 1, curr.getY())) return;
        // 무게추 아이템일 경우 이전에 다른 블럭을 만났는지 학인
        if(curr instanceof  WeightBlock && ((WeightBlock) curr).isOverlapped()) return ;
        eraseCurr();
        // 이동할 위치에 주변에 이웃한 블럭이 없으면 이동.
        if(!isNeighborExist(curr.getX() - 1, curr.getY())) {
            curr.setX((curr.getX() - 1));
        } else {
            // 이동을 못하는데 무게추 아이템이면 앞으로 못 움직이도록 바꿈
            if(curr instanceof WeightBlock){
                ((WeightBlock) curr).setOverlapped(true);
            }
        }
        notice();
    }
    public void fall() {
        eraseCurr();
        while(roomExist(curr.getX(), curr.getY() + 1)) curr.setY(curr.getY() + 1);
        visitBlock();
        eraseLine();
        gameState.updateScore(1 + gameState.getBonusScore());
        curr = null;
        notice();
    }
    /**
     * @return 현재 위치에서 블럭을 돌릴 수 있는 여부
     */
    public boolean canRotate() {
        // 무게추 아이템이면 회전하지 못하게 한다.
        if(curr instanceof WeightBlock) return false;
        int dx = curr.height() - 1 - curr.getCy() - curr.getCx();
        int dy = curr.getCx() - curr.getCy();
        int afterX = curr.getX() - dx;
        int afterY = curr.getY() - dy;
        int afterWidth = curr.height();
        int afterHeight = curr.width();
        if(afterY < 0 || afterY + afterHeight - 1 >= height) return false;
        if(afterX < 0 || afterX + afterWidth - 1 >= width) return false;
        for(int row = afterY, cy = 0; row < afterY + afterHeight; row++){
            for(int col = afterX, cx = 0; col < afterX + afterWidth; col++){
                if(visited[row][col] != -1 && curr.getShape(cy,afterWidth - 1 - cx) != -1){
                    return false;
                }
            }
        }
        return true;
    }
}