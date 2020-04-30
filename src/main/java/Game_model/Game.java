/* --------------------------------------------------------------------------------------------- 
File:   Game.java
Desc.   This class is the cheif class for the Game_Model.
        It contains and administrates all core functions of the in-game like:
        starting + pausing the in-game state, gameOver conditions, 
        setting the bounds of the game, and spawning + despawning enemies.
Note:   This class is equally foundational to the in-game state as the Entity class.
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


public class Game implements GameSave {


    //  Variables  //

    
    public enum StateDifficulty{EASY, MEDIUM, HARD}
    public enum StateGame{RUNNING, PAUSED}
    private StateDifficulty stateDiff;          //  the difficulty of the in-game state      
    private StateGame stateGame;                //  the activity of the in-game state
    private int currentAIspawnCnt;              //  the current count of enemy AI's spawned in the current in-game state
    private int AI_Left_ToSpawn;                //  the current number of enemy AI's left to spawn for the current in-game level
    private int maxAISpawnCnt;                  //  the maximum limit of enemy AI's allowed to be spawned at one time
    private int AI_Left;                        //  the current number of enemy AI's left to kill for the current in-game level
    private int score;                          //  the current score of in-game
    private int gameLvl;                        //  the current level of the in-game
    private int currentEnitity;                 //  the current highest ID number of the spawned entitys in the in-game
    private int gamePhysicsWidth =  104;        //  the width of the physical world (the 3D world of the Model)
    private int gamePhysicsHeight = 65;         //  the height of the physical world (the 3D world of the Model)
    private int gamePhysicsDepth =  10;         //  the depth of the physical world (the 3D world of the Model)
    private LocalDateTime dt;                   //  the localDateTime that the current in-game was started at
    private String playerName;                  //  the player name of the user of this current in-game
    private String lvlBackground;               //  the image URL of the current in-game background image
    private boolean gameOver;                   //  whether or not the in-game level is over
    private boolean playerWin;                  //  whether or not the player successfully completed the level or not
    private boolean cheatMode;                  //  whether or not the in-game has cheatMode active or not  
    private ArrayList<Entity> entityList =      new ArrayList<Entity>();    //  the list of living active entities in the in-game
    private ArrayList<Entity> deadEntityList =  new ArrayList<Entity>();    //  the list of dead inactive entitities in the in-game. 
                                                                            //      these entities are temporarily stored here so that 
                                                                            //      their images can be deleted from the View's pane.
    //  Singleton  //


    private Game() {}

    static private Game It = new Game();

    public static void resetGameSingleton() {
        It = new Game();
    }


    
    //  Methods  //


    public void play(){ 
        stateGame = StateGame.RUNNING;    
    }


    public void pause(){ 
        stateGame = StateGame.PAUSED;    
    }


    /** 
     * toggles the in-game's cheatmode.
     * IF cheatmode, player uses heavyRounds, ELSE player uses lightRounds.
     */
    public void toggleCheatMode(){
        cheatMode = cheatMode ? false : true;
        EH_Avatar.getIt().setTypeRound( cheatMode ? TypeRound.HEAVY_ROUND : TypeRound.LIGHT_ROUND );
    }


    /** 
     * @param difficultyLevel
     * @param GameLevel
     * initializes the in-game state according to the input parameters
     */
    public void startGame(StateDifficulty difficultyLevel, int GameLevel){
        setDifficultySettings(difficultyLevel);
        setLevelSettings(GameLevel);
        spawnerAdmin(false);
        stateGame = StateGame.RUNNING;
    }

    
    /** 
     * @param forceFullPopulate whether or not to spawn all of the enemies at once. Intended for debugging.
     * spawns enemy AI's up to the enemy AI spawn limit count.
     */
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

    /*
    *   Checks in-game state for gameOver parameters. If not gameOver, runs the enemy AI spawner method.
    */
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

    /*
    *   sorts Game.java's entityList according to the Entitys' Z-depth
    */
	public void sortEntityList() {
        Comparator<Entity> compareByScore = (Entity o1, Entity o2) -> (int)o1.getLocation().getZ() - (int)o2.getLocation().getZ();
        Collections.sort(entityList, compareByScore.reversed());
    }
    

    /** 
     * @param Id
     * @return Entity
     */
    public Entity findEntityById(int Id){
        return entityList.stream().filter(it -> it.Id == Id).findFirst().get();
    }

    
    /** 
     * @return random Point3D within the physical world boundaries.
     */
    public Point3D randomPoint3D(){
        return new Point3D(myRandom.genRandomInt(0, Game.getIt().getGamePhysicsWidth()), 
                            myRandom.genRandomInt(0, Game.getIt().getGamePhysicsHeight()), 
                            myRandom.genRandomInt(0, Game.getIt().getGamePhysicsDepth()));
    }

    
    /** 
     * @return String form of the current in-game state.
     */
    @Override
    public String Serialize() {
        String cereal="G,"+stateDiff+","+stateGame+","+AI_Left+","+score+","+"time"+","+gameLvl+","+currentEnitity+","+"newMobSpawnDelay"+","+"spawnDelayCount"+","+dt+","+playerName+","+gameOver+","+cheatMode;
        return cereal;
    }

    
    /** 
     * @param data the String form of the previously saved in-game state.
     * convert input String data to the current in-game state
     */
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
        gameLvl=Integer.parseInt(deCereal[6]);
        currentEnitity=Integer.parseInt(deCereal[7]);
        playerName=deCereal[11];
        gameOver= (deCereal[12].equals("True")) ? true:false;
        cheatMode=(deCereal[13].equals("True"))?true:false;               
    }

    
    /** 
     * @param difficultyLevel
     * sets the current in-game's enemy AI spawn limit, based off of the input difficulty level.
     */
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
    

    
    /** 
     * @param GameLevel
     * sets the current in-game's settings, based off of the input level number.
     */
    private void setLevelSettings(int GameLevel){

        switch(GameLevel)
        {
            case 1:
                gameLvl = 1;
                for (Entity scenery : GameLevels.getIt().getLvlScenery(gameLvl)) 
                {
                    entityList.add(scenery);            
                }
                AI_Left = GameLevels.getIt().getLvlAICnt(gameLvl);
                AI_Left_ToSpawn = GameLevels.getIt().getLvlAICnt(gameLvl);
                break;

            case 2:
                gameLvl = 2;
                for (Entity scenery : GameLevels.getIt().getLvlScenery(gameLvl)) 
                {
                    entityList.add(scenery);            
                }
                AI_Left = GameLevels.getIt().getLvlAICnt(gameLvl);
                AI_Left_ToSpawn = GameLevels.getIt().getLvlAICnt(gameLvl);
                break;   

            case 3:
                gameLvl = 3;
                for (Entity scenery : GameLevels.getIt().getLvlScenery(gameLvl)) 
                {
                    entityList.add(scenery);            
                }
                AI_Left = GameLevels.getIt().getLvlAICnt(gameLvl);
                AI_Left_ToSpawn = GameLevels.getIt().getLvlAICnt(gameLvl);

                Entity bigBoss = new EH_BossAI();
                bigBoss.spawn();
                default:
        }
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

 