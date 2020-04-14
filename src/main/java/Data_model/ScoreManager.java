package Data_model;

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
        // TODO: read scores data found in Game.scores file.
    }

    public void saveScores() {
        // TODO: serialize scores to Game.scores
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