package Data_model;

import java.time.LocalDate;
import java.util.Date;

import Game_model.Game;

public class Cereal {

    // Variables //

    Game game;
    String name;
    LocalDate dt;


    //  Constructor  //
  

    public Cereal(Game game, String name, Date dt) {
        this.game = game;
        this.name = name;
        this.dt = java.time.LocalDate.now();
    }


    //  Methods  //

    
    public String Serialize()
    {
        return " GAMESAVE: " + this.name + " @ " + this.dt;
    }

    public String ToString()
    {
        return this.Serialize();
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

    public LocalDate getDt() {
        return dt;
    }

    public void setDt(LocalDate dt) {
        this.dt = dt;
    }
}