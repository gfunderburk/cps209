package Data_model;

import java.time.LocalDateTime;

public class Score {

    
    //  Variables  //


    int value;
    LocalDateTime dt;
    String name;

    
    //  Constructor  //


    public Score(String name, LocalDateTime dt, int value) {
        this.value = value;
        this.dt = dt;
        this.name = name.trim();
    }

    
    //  Methods  //

    
    public String Serialize()
    {
        return " SCORE: " + this.name + " @ " + this.dt + " = " + this.value;
    }

    public String toString()
    {
        return this.Serialize();
    }

    
    //  Getters-Setters  //


    public int getScore() {
        return value;
    }

    public void setScore(int value) {
        this.value = value;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}