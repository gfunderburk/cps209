package Data_model;

import java.time.LocalDateTime;
import Game_model.GameSave;

public class Cereal {

    // Variables //

    GameSave game;
    String name;
    LocalDateTime dt;


    //  Constructor  //
  

    public Cereal(GameSave game, LocalDateTime dt, String name) {
        this.game = game;
        this.name = name;
        this.dt = java.time.LocalDateTime.now();
    }


    //  Methods  //

    
    public String toString()
    {
        return this.dt + "_" + this.name + ".dat";
    }


    public String SerializeGame()
    {
        //TODO: Serialize objects in game.
        return null;
    }


	public void deSerialize(String line) {

        String[] attrList = line.split(",");
        switch(attrList[0]){

            case "G":
                //TODO: De-Cereal G
                break;
                
            case "U":
                //TODO: De-Cereal U
                break;

            case "H":
                //TODO: De-Cereal H
                break;

            case "S":
                //TODO: De-Cereal S
                break;
            
            case "P":
                //TODO: De-Cereal P
                break;
        }
	}


    //  Getters-Setters  //


    public GameSave getGame() {
        return game;
    }

    public void setGame(GameSave game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }
}
