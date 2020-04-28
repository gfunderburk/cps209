/* --------------------------------------------------------------------------------------------- 
File:   Score.java
Desc.   Score is an object class that stores the details of each instance of a game score.
--------------------------------------------------------------------------------------------- */


package Data_model;

import java.time.LocalDateTime;

public class Score {

    
    //  Variables  //


    public int value;
    public LocalDateTime dt;
    public String name;

    
    //  Constructor  //


    public Score(String name, LocalDateTime dt, int value) {
        this.value = value;
        this.dt = dt;
        this.name = name.trim();
    }

    
    //  Methods  //

    
    public String Serialize()
    {
        return this.name + "," + this.dt + "," + this.value;
    }

    public String toString()
    {
        return this.Serialize();
    }

    public static Score deSerialize(String data) {
        
        String[] item = data.split(",");        
        var name = item[0].trim();
        var dt = LocalDateTime.parse(item[1].trim());
        var value = Integer.parseInt(item [2].trim());

        var newScore = new Score(name, dt, value);

        return newScore;
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