import org.junit.Test;
import Data_model.*;
import Game_model.Game;

import static org.junit.Assert.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ScoreManagerTest {

    Game game = null;
    LocalDateTime now = LocalDateTime.now();

    ArrayList<Score> testList = new ArrayList<Score>(Arrays.asList(
        new Score("name0", now, 10),
        new Score("name1", now, 11),
        new Score("name2", now, 12),
        new Score("name3", now, 13),
        new Score("name4", now, 14)
    ));


    @Test
    public void test_Add_SortScores() 
    {
        ScoreManager.setList(testList);
        ScoreManager.addScore(new Score("userName", now, 100));
        assertEquals(6, ScoreManager.getList().size());
        assertEquals("userName", ScoreManager.getList().get(5).getName());

        ScoreManager.sortScores();
        assertEquals("userName", ScoreManager.getList().get(0).getName());
    }

    @Test
    public void test_DeleteScore()   throws IOException 
    {
        ScoreManager.setList(testList);
        ScoreManager.deleteScore(ScoreManager.getList().get(0));
        assertEquals(4, ScoreManager.getList().size());
        assertEquals("name1", ScoreManager.getList().get(0).getName());
    }
    
    @Test
    public void test_LoadScores()   throws IOException 
    {
        ScoreManager.setList(testList);

        ScoreManager.addScore(new Score("userName", now, 100));
        ScoreManager.sortScores();
        ScoreManager.saveScores();
        ScoreManager.loadScores();
        assertEquals("userName", ScoreManager.getList().get(0).getName());
    }
}