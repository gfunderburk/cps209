package model;

//--------------------------------------------------------------------------
//File:   Game.java
//Desc:   This file is the Cheif class for the Model,   
//        It contains all the game's static variables.
//-------------------------------------------------------------------------- 

public class Game {
    
    public static enum CellType{one, two, three};

    public static boolean gameOver = false;
    public static boolean cheatMode = false;

    public Game() throws InterruptedException {
    }

}