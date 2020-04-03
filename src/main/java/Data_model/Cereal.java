package Data_model;

import java.time.LocalDateTime;

import Game_model.Game;

public class Cereal {

    // Variables //

    Game game;
    String name;
    LocalDateTime dt;


    //  Constructor  //
  

    public Cereal(Game game, String name, LocalDateTime dt) {
        this.game = game;
        this.name = name;
        this.dt = java.time.LocalDateTime.now();
    }


    //  Methods  //

    
    public String Serialize()
    {
        return " GAMESAVE: " + this.name + " @ " + this.dt;
    }

    public String toString()
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

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }
}