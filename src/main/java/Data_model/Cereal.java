package Data_model;

import java.time.LocalDateTime;
import Game_model.Game;

public class Cereal {

    // Variables //

    Game game;
    String name;
    LocalDateTime dt;


    //  Constructor  //
  

    public Cereal(Game game, LocalDateTime dt, String name) {
        this.game = game;
        this.name = name;
        this.dt = java.time.LocalDateTime.now();
    }


    //  Methods  //

    
    public String SerializeGame()
    {
        return this.dt + this.name + ".dat";
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
    
    public String toString()
    {
        return this.dt + "_" + this.name + ".dat";
    }


    //  Getters-Setters  //


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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