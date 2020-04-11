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

    public void checkGameOver(){
        // TODO IF hostilesLeft = 0, show end-level screen to progress to next level.
        //   OR IF player health = 0, show end-game screen to offer to save player score.         
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

    public StateDifficulty getStateDiff() {
        return stateDiff;
    }

    public void setStateDiff(StateDifficulty stateDiff) {
        this.stateDiff = stateDiff;
    }

    public StateGame getStateGame() {
        return stateGame;
    }

    public void setStateGame(StateGame stateGame) {
        this.stateGame = stateGame;
    }

    public int getHostilesLeft() {
        return hostilesLeft;
    }

    public void setHostilesLeft(int hostilesLeft) {
        this.hostilesLeft = hostilesLeft;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGameLvl() {
        return gameLvl;
    }

    public void setGameLvl(int gameLvl) {
        this.gameLvl = gameLvl;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(ArrayList<Entity> entityList) {
        this.entityList = entityList;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isCheatMode() {
        return cheatMode;
    }

    public void setCheatMode(boolean cheatMode) {
        this.cheatMode = cheatMode;
    }

}


