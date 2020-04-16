package Game_model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Util_model.myRandom;
import javafx.geometry.Point3D;

//--------------------------------------------------------------------------
//File:   Game.java
//Desc:   This file is the Cheif class for the Model,   
//        It contains all the game's static variables.
//-------------------------------------------------------------------------- 

public class Game implements GameSave {


    //  Variables  //

    
    public enum StateDifficulty{EASY, MEDIUM, HARD}
    public enum StateGame{RUNNING, PAUSED}
    private StateDifficulty stateDiff;
    private StateGame stateGame;
    private int LAI_Left, HAI_Left, FAI_Left;
    private int score;
    private int time;
    private int gameLvl;
    private int currentEnitity;
    private int newMobSpawnDelay, spawnDelayCount;
    private int gamePhysicsWidth = 104;
    private int gamePhysicsHeight = 65;
    private int gamePhysicsDepth = 10;
    private LocalDateTime dt;
    private String playerName;
    private ArrayList<Entity> entityList = new ArrayList<Entity>();
    private boolean gameOver = false;
    private boolean cheatMode = false;



    //  Singleton  //


    private Game() {
    }

    static private Game It = new Game();


    //  Methods  //

    
    public void spawnerAdmin(boolean forceFullPopulate){
        var test = new EH_LightAI();
        test.spawn();
        var test1 = new EH_LightAI();
        test1.spawn();
        var test2 = new EH_LightAI();
        test2.spawn();
    }

    public void startGame(String playerName, int difficultyLevel, int GameLevel){
        for (Entity scenery : GameLevels.getIt().getLvl1_Scenery()) {
            entityList.add(scenery);            
        }
        spawnerAdmin(true);
        stateGame = StateGame.RUNNING;
    }

    public void loadGame(){
        entityList = new ArrayList<Entity>();
    }

    public void play(){ 
        stateGame = StateGame.RUNNING;    
    }

    public void pause(){ 
        stateGame = StateGame.PAUSED;    
    }

    public void checkGameOver(){
        // TODO IF hostilesLeft = 0, show end-level screen to progress to next level.
        //   OR IF player health = 0, show end-game screen to offer to save player score.         
    }

	public void sortEntityList() {
        Comparator<Entity> compareByScore = (Entity o1, Entity o2) -> (int)o1.getLocation().getZ() - (int)o2.getLocation().getZ();
        Collections.sort(entityList, compareByScore.reversed()); //TODO: check for BUG .reversed());
    }

    public Point3D randomPoint3D(){
        return new Point3D(myRandom.genRandomInt(0, Game.getIt().getGamePhysicsWidth()), 
                            myRandom.genRandomInt(0, Game.getIt().getGamePhysicsHeight()), 
                            myRandom.genRandomInt(0, Game.getIt().getGamePhysicsDepth()));
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

    public int getCurrentEnitity() {
        return currentEnitity;
    }

    public void setCurrentEnitity(int currentEnitity) {
        this.currentEnitity = currentEnitity;
    }

    public int getGamePhysicsWidth() {
        return gamePhysicsWidth;
    }

    public void setGamePhysicsWidth(int gamePhysicsWidth) {
        this.gamePhysicsWidth = gamePhysicsWidth;
    }

    public int getGamePhysicsHeight() {
        return gamePhysicsHeight;
    }

    public void setGamePhysicsHeight(int gamePhysicsHeight) {
        this.gamePhysicsHeight = gamePhysicsHeight;
    }

    public int getGamePhysicsDepth() {
        return gamePhysicsDepth;
    }

    public void setGamePhysicsDepth(int gamePhysicsDepth) {
        this.gamePhysicsDepth = gamePhysicsDepth;
    }

    public int getLAI_Left() {
        return LAI_Left;
    }

    public void setLAI_Left(int lAI_Left) {
        LAI_Left = lAI_Left;
    }

    public int getHAI_Left() {
        return HAI_Left;
    }

    public void setHAI_Left(int hAI_Left) {
        HAI_Left = hAI_Left;
    }

    public int getFAI_Left() {
        return FAI_Left;
    }

    public void setFAI_Left(int fAI_Left) {
        FAI_Left = fAI_Left;
    }

    public int getNewMobSpawnDelay() {
        return newMobSpawnDelay;
    }

    public void setNewMobSpawnDelay(int newMobSpawnDelay) {
        this.newMobSpawnDelay = newMobSpawnDelay;
    }

    public int getSpawnDelayCount() {
        return spawnDelayCount;
    }

    public void setSpawnDelayCount(int spawnDelayCount) {
        this.spawnDelayCount = spawnDelayCount;
    }
}


