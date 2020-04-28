/* --------------------------------------------------------------------------------------------- 
File:   .java
Desc.   
--------------------------------------------------------------------------------------------- */


package Data_model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreManager {


    //  Variables  //


    private ArrayList<Score> scoreList = new ArrayList<Score>();
    

    
    //  Singleton  //


    private ScoreManager() {
    }

    private static ScoreManager It = new ScoreManager();

    // Methods //

    public void loadScores() {
        try(BufferedReader rd = new BufferedReader( new FileReader("scores.dat")))
        {                       
            String line = rd.readLine();
            while (line != null) { 
                addScore(Score.deSerialize(line)); 
                line = rd.readLine(); 
            } 
            rd.close(); 
        } 
        catch (IOException e) 
        { 
            System.out.println("Problem loading scores.dat"); 
        }
    }

    public void saveScores() {
        try(var wr = new PrintWriter( new FileWriter("scores.dat")); ) 
        { 
            for (Score item : getList()) 
            {
                wr.println(item.Serialize());
            }            
            wr.close(); 
        } 
        catch(IOException e) 
        {
            System.out.println("Problem saving " + toString()); 
        }
    }

    public void addScore(Score newScore) {
        scoreList.add(newScore);
        sortScores();
    }

    public void deleteScore(Score score) {
        scoreList.remove(score);
    }

    public void sortScores() {

        Comparator<Score> compareByScore = (Score o1, Score o2) -> o1.value - o2.value;
        Collections.sort(scoreList, compareByScore.reversed());
    }

    // ---- //

    public String toString() {
        String result = "";
        for (Score score : scoreList) {
            result += score.Serialize();
        }
        return result;
    }

    // Getters-Setters //

    public ArrayList<Score> getList() {
        return scoreList;
    }

    public void setList(ArrayList<Score> scoreList) {
        ScoreManager.getIt().setList(scoreList);
    }

    public static ScoreManager getIt() {
        return It;
    }
}