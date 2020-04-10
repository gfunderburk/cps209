package Data_model;

import java.util.ArrayList;

public class ScoreManager {


    //  Variables  //


    static private ArrayList<Score> scoreList = new ArrayList<Score>();
    
    
    //  Singleton  //


    private ScoreManager() {
    }

    static private ScoreManager It = new ScoreManager();


    //  Methods  //


    public static void loadScores(){
        //TODO: read scores data found in Game.scores file.
    }

    public static void saveScores(){
        //TODO: serialize scores to Game.scores
    }

    public static void addScore(Score newScore){
        scoreList.add(newScore);
    }

    public static void deleteScore(Score score){
        scoreList.remove(score);
    }
    
    public static void sortScores(){
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


    public String toString()
    {
        String result = "";
        for (Score score : scoreList) {
            result += score.Serialize();
        }
        return result;
    }


    //  Getters-Setters  //


    public static ArrayList<Score> getList() {
        return scoreList;
    }

    public static void setList(ArrayList<Score> scoreList) {
        ScoreManager.scoreList = scoreList;
    }

    public static ScoreManager getIt() {
        return It;
    }

}