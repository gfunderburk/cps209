package Game_model;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLevels {


    //  Variables  //


    //  Level 1 Objects
    ArrayList<EK_Scenery> lvl1_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        // TODO: place entity scenery here like: new EK_Scenery(0, 0, 0, null, null), next scenery
    ));
    int lvl1_AICnt = 5;

    
    //  Level 2 Objects
    ArrayList<EK_Scenery> lvl2_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        
    ));
    int lvl2_AICnt = 7;

    
    //  Level 3 Objects
    ArrayList<EK_Scenery> lvl3_Scenery = new ArrayList<EK_Scenery>(Arrays.asList(
        
    ));
    int lvl3_AICnt = 9;


    //  Singleton  //


    private GameLevels() {
    }

    static private GameLevels It = new GameLevels();

    public static GameLevels getIt() {
        return It;
    }


    //  Methods  //


    public int getLvlAICnt(int lvl){
        switch(lvl){
            case 1:
                return lvl1_AICnt;

            case 2:
                return lvl2_AICnt;

            case 3:
                return lvl3_AICnt;

            default:
                return -1;
        }
    }

    public ArrayList<EK_Scenery> getLvlScenery(int lvl){
        switch(lvl){
            case 1:
                return lvl1_Scenery;

            case 2:
                return lvl2_Scenery;

            case 3:
                return lvl3_Scenery;

            default:
                return null;
        }
    }



    //  Getters-Setters  //


    public static void setIt(GameLevels it) {
        It = it;
    }

}