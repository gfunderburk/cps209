package Game_model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;

//--------------------------------------------------------------------------
//File:   Game.java
//Desc:   This file is the Cheif class for the Model,   
//        It contains all the game's static variables.
//-------------------------------------------------------------------------- 

public class Game implements GameSave {


    //  Variables  //

    
    public enum StateDifficulty{EASY, MEDIUM, HARD}
    public enum StateGame{RUNNING, PAUSED}
    public StateDifficulty stateDiff;
    public StateGame stateGame;
    public int hostilesLeft;
    public int score;
    public int time;
    public int gameLvl;
    public LocalDateTime dt;
    public String playerName;
    public ArrayList<Entity> entityList = new ArrayList<Entity>();
    public boolean gameOver = false;
    public boolean cheatMode = false;

    
    //  Singleton  //


    private Game() {
    }

    static private Game It = new Game();


    //  Methods  //

    
    public void cleanStart(String playerName, StateDifficulty difficultyMode){
        this.playerName = playerName;
        this.stateDiff = difficultyMode;
        entityList = new ArrayList<Entity>();
        hostilesLeft = 10;
        score = 0;
        time = 0;
        gameLvl = 1;
        gameOver = false;
        cheatMode = false;
    }

    public void cleanLoad(){
        entityList = new ArrayList<Entity>();
    }

    public void play(){
        // TODO update windows and such to start the game   
        stateGame = StateGame.RUNNING;    
    }

    public void pause(){
        // TODO update windows and such to pause the game   
        stateGame = StateGame.PAUSED;    
    }

    public void applyBonusPts(){
        // TODO update windows and such to pause the game         
    }

    @Override
    public String Serialize() {
        // TODO serialize self data
        return null;
    }


    @Override
    public void deSerialize(String data) {
        // TODO load data to self

    }


    //  Getters-Setters  //


    public static Game getIt() {
        return It;
    }

    public static void setIt(Game it) {
        It = it;
    }

}


