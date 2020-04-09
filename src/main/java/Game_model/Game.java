package Game_model;

import java.time.LocalDateTime;
import java.util.ArrayList;

//--------------------------------------------------------------------------
//File:   Game.java
//Desc:   This file is the Cheif class for the Model,   
//        It contains all the game's static variables.
//-------------------------------------------------------------------------- 

public class Game implements GameSave {


    //  Variables  //

    
    public int hostilesLeft;
    public int score;
    public int time;
    public int gameLvl;
    public LocalDateTime dt;
    public String playerName;
    public enum Mode{Easy, Medium, Hard}
    public ArrayList<Entity> entityList = new ArrayList<Entity>();

    public boolean gameOver = false;
    public boolean cheatMode = false;

    
    //  Singleton  //


    private Game() {
    }

    static private Game It = new Game();


    //  Methods  //


    @Override
    public String Serialize() {
        String result = "";
        for (Entity item : entityList) {
            result += item.Serialize();
        }
        return result;
    }


    @Override
    public void deSerialize(String data) {
        // TODO Auto-generated method stub

    }


    //  Getters-Setters  //


    public static Game getIt() {
        return It;
    }

    public static void setIt(Game it) {
        It = it;
    }

}