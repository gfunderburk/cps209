package Data_model;

import java.time.LocalDate;

public class Score {

    
    //  Variables  //


    int value;
    LocalDate dt;
    String name;

    
    //  Constructor  //


    public Score(String name, LocalDate dt, int value) {
        this.value = value;
        this.dt = dt;
        this.name = this.name.trim();
    }

    
    //  Methods  //

    
    public String Serialize()
    {
        return " SCORE: " + this.name + " @ " + this.dt + " = " + this.value;
    }

    public String ToString()
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

    public LocalDate getDt() {
        return dt;
    }

    public void setDt(LocalDate dt) {
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}