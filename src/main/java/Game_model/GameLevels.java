/* --------------------------------------------------------------------------------------------- 
File:   GameLevels.java
Desc.   This class contains any elements unique to each game level.
        Currently, this class contains a roster of power-ups 
        and total enemies that will spawn for each level.
Note:   This class is a singleton to companion the Game.java singleton.
--------------------------------------------------------------------------------------------- */


package Game_model;

import java.util.ArrayList;
import java.util.Arrays;
import Game_model.EK_Scenery.Type;

public class GameLevels {


    //  Variables  //


    //  Level 1 Objects
    ArrayList<EK_Scenery> lvl1_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        new EK_Scenery(Type.AMMO),
        new EK_Scenery(Type.HEALTH),
        new EK_Scenery(Type.POINTS),
        new EK_Scenery(Type.HEALTH),
        new EK_Scenery(Type.AMMO)
    ));

    //  the total number of enemy AI's for level 1
    int lvl1_AICnt = 10; 

    
    //  Level 2 Objects
    ArrayList<EK_Scenery> lvl2_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        new EK_Scenery(Type.HEALTH),
        new EK_Scenery(Type.AMMO),
        new EK_Scenery(Type.POINTS),
        new EK_Scenery(Type.AMMO),
        new EK_Scenery(Type.HEALTH),
        new EK_Scenery(Type.POINTS)
        
    ));

    //  the total number of enemy AI's for level 2
    int lvl2_AICnt = 15; 

    
    //  Level 3 Objects
    ArrayList<EK_Scenery> lvl3_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        new EK_Scenery(Type.POINTS),
        new EK_Scenery(Type.AMMO),
        new EK_Scenery(Type.HEALTH),
        new EK_Scenery(Type.AMMO),
        new EK_Scenery(Type.POINTS),
        new EK_Scenery(Type.HEALTH),
        new EK_Scenery(Type.POINTS)
        
    ));

    //  the total number of enemy AI's for level 3
    int lvl3_AICnt = 20; 


    //  Singleton  //


    private GameLevels() {
    }

    static private GameLevels It = new GameLevels();

    public static GameLevels getIt() {
        return It;
    }


    
    /** 
     * @param lvl the game level to load AICnt for
     * @return int
     */
    //  Methods  //


    public int getLvlAICnt(int lvl){
        switch(lvl){
            case 1:
                return this.lvl1_AICnt;

            case 2:
                return this.lvl2_AICnt;

            case 3:
                return this.lvl3_AICnt;

            default:
                return -1;
        }
    }

    
    /** 
     * @param lvl the game level to load the respective list of power-ups for
     * @return ArrayList<EK_Scenery>
     */
    public ArrayList<EK_Scenery> getLvlScenery(int lvl){
        switch(lvl){
            case 1:
                return this.lvl1_Scenery;

            case 2:
                return this.lvl2_Scenery;

            case 3:
                return this.lvl3_Scenery;

            default:
                return null;
        }
    }
}