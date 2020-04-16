package Game_model;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLevels {


    //  Variables  //


    //  Level 1 Objects
    ArrayList<EK_Scenery> lvl1_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        // TODO: place entity scenery here like: new EK_Scenery(0, 0, 0, null, null), next scenery
    ));
    int lvl1_lightAICnt = 5;
    int lvl1_heavyAICnt = 5;
    int lvl1_flyingAICnt = 5;

    
    //  Level 2 Objects
    ArrayList<EK_Scenery> lvl2_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        
    ));
    int lvl2_lightAICnt = 5;
    int lvl2_heavyAICnt = 5;
    int lvl2_flyingAICnt = 5;

    
    //  Level 3 Objects
    ArrayList<EK_Scenery> lvl3_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        
    ));
    int lvl3_lightAICnt = 5;
    int lvl3_heavyAICnt = 5;
    int lvl3_flyingAICnt = 5;


    //  Singleton  //


    private GameLevels() {
    }

    static private GameLevels It = new GameLevels();

    public static GameLevels getIt() {
        return It;
    }


    //  Getters-Setters  //


    public ArrayList<EK_Scenery> getLvl1_Scenery() {
        return lvl1_Scenery;
    }

    public void setLvl1_Scenery(ArrayList<EK_Scenery> lvl1_Scenery) {
        this.lvl1_Scenery = lvl1_Scenery;
    }

    public ArrayList<EK_Scenery> getLvl2_Scenery() {
        return lvl2_Scenery;
    }

    public void setLvl2_Scenery(ArrayList<EK_Scenery> lvl2_Scenery) {
        this.lvl2_Scenery = lvl2_Scenery;
    }

    public ArrayList<EK_Scenery> getLvl3_Scenery() {
        return lvl3_Scenery;
    }

    public void setLvl3_Scenery(ArrayList<EK_Scenery> lvl3_Scenery) {
        this.lvl3_Scenery = lvl3_Scenery;
    }

    public int getLvl1_lightAICnt() {
        return lvl1_lightAICnt;
    }

    public void setLvl1_lightAICnt(int lvl1_lightAICnt) {
        this.lvl1_lightAICnt = lvl1_lightAICnt;
    }

    public int getLvl1_heavyAICnt() {
        return lvl1_heavyAICnt;
    }

    public void setLvl1_heavyAICnt(int lvl1_heavyAICnt) {
        this.lvl1_heavyAICnt = lvl1_heavyAICnt;
    }

    public int getLvl1_flyingAICnt() {
        return lvl1_flyingAICnt;
    }

    public void setLvl1_flyingAICnt(int lvl1_flyingAICnt) {
        this.lvl1_flyingAICnt = lvl1_flyingAICnt;
    }

    public int getLvl2_lightAICnt() {
        return lvl2_lightAICnt;
    }

    public void setLvl2_lightAICnt(int lvl2_lightAICnt) {
        this.lvl2_lightAICnt = lvl2_lightAICnt;
    }

    public int getLvl2_heavyAICnt() {
        return lvl2_heavyAICnt;
    }

    public void setLvl2_heavyAICnt(int lvl2_heavyAICnt) {
        this.lvl2_heavyAICnt = lvl2_heavyAICnt;
    }

    public int getLvl2_flyingAICnt() {
        return lvl2_flyingAICnt;
    }

    public void setLvl2_flyingAICnt(int lvl2_flyingAICnt) {
        this.lvl2_flyingAICnt = lvl2_flyingAICnt;
    }

    public int getLvl3_lightAICnt() {
        return lvl3_lightAICnt;
    }

    public void setLvl3_lightAICnt(int lvl3_lightAICnt) {
        this.lvl3_lightAICnt = lvl3_lightAICnt;
    }

    public int getLvl3_heavyAICnt() {
        return lvl3_heavyAICnt;
    }

    public void setLvl3_heavyAICnt(int lvl3_heavyAICnt) {
        this.lvl3_heavyAICnt = lvl3_heavyAICnt;
    }

    public int getLvl3_flyingAICnt() {
        return lvl3_flyingAICnt;
    }

    public void setLvl3_flyingAICnt(int lvl3_flyingAICnt) {
        this.lvl3_flyingAICnt = lvl3_flyingAICnt;
    }

    public static void setIt(GameLevels it) {
        It = it;
    }
}