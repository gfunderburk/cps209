/* --------------------------------------------------------------------------------------------- 
File:   .java
Desc.   
--------------------------------------------------------------------------------------------- */


package Game_model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Game_model.E_Projectile.TypeRound;
import Game_model.EntityKillable.StateLife;
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
    private int AI_Left, AI_Left_ToSpawn, currentAIspawnCnt, maxAISpawnCnt;
    private int score;
    private int time;
    private int gameLvl;
    private int currentEnitity;
    private int newMobSpawnDelay, spawnDelayCount;
    private int gamePhysicsWidth = 104;
    private int gamePhysicsHeight = 65;
    private int gamePhysicsDepth = 10;
    private LocalDateTime dt;
    private String playerName, lvlBackground;
    private ArrayList<Entity> entityList = new ArrayList<Entity>();
    private ArrayList<Entity> deadEntityList = new ArrayList<Entity>();
    private boolean gameOver, playerWin;
    private boolean cheatMode = false;


    //  Singleton  //


    private Game() {
    }

    static private Game It = new Game();


    //  Methods  //

    
    public void startGame(String playerName, StateDifficulty difficultyLevel, int GameLevel){
        // reset();
        // resetGameSingleton();
        setDifficultySettings(difficultyLevel);
        setLevelSettings(GameLevel);
        spawnerAdmin(false);
        stateGame = StateGame.RUNNING;
    }

    public static void resetGameSingleton() {
        It = new Game();
    }


    public void spawnerAdmin(boolean forceFullPopulate){

        while(true)
        if( AI_Left > 0 & 
            AI_Left_ToSpawn > 0 &
            (currentAIspawnCnt < maxAISpawnCnt | forceFullPopulate) ) //maxAISpawnCnt
            {
                switch(gameLvl)
                {
                    case 1:
                        var test1 = new EH_LightAI();
                        test1.spawn();
                        break;
                    case 2:
                        var test2 = new EH_HeavyAI();
                        test2.spawn();
                        break;
                    case 3:
                        var test3 = new EH_FlyingAI();
                        test3.spawn();
                        break;
                    default:    
                }
        }
        else break;
    }


    public void play(){ 
        stateGame = StateGame.RUNNING;    
    }


    public void pause(){ 
        stateGame = StateGame.PAUSED;    
    }


    public Entity findEntityById(int Id){
        return entityList.stream().filter(it -> it.Id == Id).findFirst().get();
    }


    public void checkGameOver(){
        if(AI_Left <= 0){
            gameOver = true;
            playerWin = true;
        }        
        else if(! isCheatMode() & EH_Avatar.getIt().getStateLife() == StateLife.DEAD){
            gameOver = true;
            playerWin = false;
        }
        else{
            spawnerAdmin(false);
        }
    }


	public void sortEntityList() {
        Comparator<Entity> compareByScore = (Entity o1, Entity o2) -> (int)o1.getLocation().getZ() - (int)o2.getLocation().getZ();
        Collections.sort(entityList, compareByScore.reversed());
    }


    public Point3D randomPoint3D(){
        return new Point3D(myRandom.genRandomInt(0, Game.getIt().getGamePhysicsWidth()), 
                            myRandom.genRandomInt(0, Game.getIt().getGamePhysicsHeight()), 
                            myRandom.genRandomInt(0, Game.getIt().getGamePhysicsDepth()));
    }


    @Override
    public String Serialize() {
        String cereal="G,"+stateDiff+","+stateGame+","+AI_Left+","+score+","+time+","+gameLvl+","+currentEnitity+","+newMobSpawnDelay+","+spawnDelayCount+","+dt+","+playerName+","+gameOver+","+cheatMode;
        return cereal;
    }


    @Override
    public void deSerialize(String data) {
        String[] deCereal=data.split(",");
        if(deCereal[1].equals("EASY")){
            stateDiff= StateDifficulty.EASY;
        }
        if(deCereal[1].equals("MEDIUM")){
            stateDiff= StateDifficulty.MEDIUM;
        }
        if(deCereal[1].equals("HARD")){
            stateDiff= StateDifficulty.HARD;
        }
        stateGame=(deCereal[2].equals("RUNNING"))? StateGame.RUNNING : StateGame.PAUSED;
        
        
        AI_Left=Integer.parseInt(deCereal[3]);
        score=Integer.parseInt(deCereal[4]);
        time=Integer.parseInt(deCereal[5]);
        gameLvl=Integer.parseInt(deCereal[6]);
        currentEnitity=Integer.parseInt(deCereal[7]);
        newMobSpawnDelay=Integer.parseInt(deCereal[8]);
        spawnDelayCount=Integer.parseInt(deCereal[9]);
        //NOTE: [10] (DT) is skipped.
        playerName=deCereal[11];
        gameOver= (deCereal[12].equals("True")) ? true:false;
        cheatMode=(deCereal[13].equals("True"))?true:false;               
        
    }


    private void setDifficultySettings(StateDifficulty difficultyLevel){
        switch(difficultyLevel){
            case EASY:
                stateDiff = difficultyLevel;
                maxAISpawnCnt = 3;
                break;

            case MEDIUM:
                stateDiff = difficultyLevel;
                maxAISpawnCnt = 5;
                break;  

            case HARD:
                stateDiff = difficultyLevel;
                maxAISpawnCnt = 7;
                break;
            default:
        }
    }
    

    private void setLevelSettings(int GameLevel){

        switch(GameLevel){

            case 1:
                gameLvl = 1;
                for (Entity scenery : GameLevels.getIt().getLvlScenery(gameLvl)) {
                    entityList.add(scenery);            
                }
                AI_Left = GameLevels.getIt().getLvlAICnt(gameLvl);
                AI_Left_ToSpawn = GameLevels.getIt().getLvlAICnt(gameLvl);
                break;

            case 2:
                gameLvl = 2;
                for (Entity scenery : GameLevels.getIt().getLvlScenery(gameLvl)) {
                    entityList.add(scenery);            
                }
                AI_Left = GameLevels.getIt().getLvlAICnt(gameLvl);
                AI_Left_ToSpawn = GameLevels.getIt().getLvlAICnt(gameLvl);
                break;   

            case 3:
                gameLvl = 3;
                for (Entity scenery : GameLevels.getIt().getLvlScenery(gameLvl)) {
                    entityList.add(scenery);            
                }
                AI_Left = GameLevels.getIt().getLvlAICnt(gameLvl);
                AI_Left_ToSpawn = GameLevels.getIt().getLvlAICnt(gameLvl);

                Entity bigBoss = new EH_BossAI();
                bigBoss.spawn();
                break;
                default:
        }
    }


    public void toggleCheatMode(){
        cheatMode = cheatMode ? false : true;
        EH_Avatar.getIt().setTypeRound( cheatMode ? TypeRound.HEAVY_ROUND : TypeRound.LIGHT_ROUND );
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

    public int getAI_Left() {
        return AI_Left;
    }

    public void setAI_Left(int hAI_Left) {
        AI_Left = hAI_Left;
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

    public String getLvlBackground() {
        return lvlBackground;
    }

    public void setLvlBackground(String lvlBackground) {
        this.lvlBackground = lvlBackground;
    }

    public ArrayList<Entity> getDeadEntityList() {
        return deadEntityList;
    }

    public void setDeadEntityList(ArrayList<Entity> deadEntityList) {
        this.deadEntityList = deadEntityList;
    }

    public int getAI_Left_ToSpawn() {
        return AI_Left_ToSpawn;
    }

    public void setAI_Left_ToSpawn(int aI_Left_ToSpawn) {
        AI_Left_ToSpawn = aI_Left_ToSpawn;
    }

    public int getCurrentAIspawnCnt() {
        return currentAIspawnCnt;
    }

    public void setCurrentAIspawnCnt(int currentAIspawnCnt) {
        this.currentAIspawnCnt = currentAIspawnCnt;
    }

    public int getMaxAISpawnCnt() {
        return maxAISpawnCnt;
    }

    public void setMaxAISpawnCnt(int maxAISpawnCnt) {
        this.maxAISpawnCnt = maxAISpawnCnt;
    }


    public boolean isPlayerWinner() {
        return playerWin;
    }

    public void setPlayerWinner(boolean playerWin) {
        this.playerWin = playerWin;
    }

    public boolean isPlayerWin() {
        return playerWin;
    }

    public void setPlayerWin(boolean playerWin) {
        this.playerWin = playerWin;
    }

}

 