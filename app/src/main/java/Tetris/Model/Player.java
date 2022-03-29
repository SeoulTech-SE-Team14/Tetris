package Tetris.Model;

import java.util.Observable;

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