package Tetris.Model.game;

import java.util.Observable;

/**
 * 플레이어 현재 정보 클래스
 * @author 김영균
 */
public class Player extends Observable {
    private String name;
    public Player(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
}