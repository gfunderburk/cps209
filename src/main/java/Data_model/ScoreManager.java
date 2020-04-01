package Data_model;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {


    //  Variables  //


    static private ArrayList<Score> scoreList = new ArrayList<Score>();
    
    
    //  Singleton  //


    private ScoreManager() {
    }

    static private ScoreManager It = new ScoreManager();


    //  Methods  //


    static void loadScores(){
    }

    static void saveScores(){

    }

    static void addScore(Score newScore){
        scoreList.add(newScore);
    }

    private void deleteScore(Score score){
        scoreList.remove(score);
    }
    
    private void sortScores(){
        scoreList.sort( (o1, o2) -> 
        {
            if(o1.value == o2.value){
                return 0;
            }
            else{
                return o1.value < o2.value ? -1 : 1;
            }
        });
    } 

    //   ----   //

    public String Serialize()
    {
        String result = "";
        for (Score score : scoreList) {
            result += score.Serialize();
        }
        return result;
    }

    public String ToString()
    {
        return this.Serialize();
    }


    //  Getters-Setters  //


    public ArrayList<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(ArrayList<Score> scoreList) {
        this.scoreList = scoreList;
    }

    static public ScoreManager getIt() {
        return It;
    }

}