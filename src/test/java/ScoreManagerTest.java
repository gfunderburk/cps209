import org.junit.Test;
import Data_model.*;

import static org.junit.Assert.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ScoreManagerTest {

    LocalDateTime now = LocalDateTime.now();

    ArrayList<Score> testList = new ArrayList<Score>(Arrays.asList(
        new Score("name0", now, 10),
        new Score("name1", now, 11),
        new Score("name2", now, 12),
        new Score("name3", now, 13),
        new Score("name4", now, 14)
    ));


    @Test
    public void test_Add_Scores() 
    {
        ScoreManager.getIt().setList(testList);
        ScoreManager.getIt().addScore(new Score("userName", now, 100));
        assertEquals(6, ScoreManager.getIt().getList().size());
        assertEquals("userName", ScoreManager.getIt().getList().get(5).getName());
    }

    @Test
    public void test_SortScores() 
    {
        ScoreManager.getIt().setList(testList);
        ScoreManager.getIt().addScore(new Score("userName", now, 100));
        assertEquals(6, ScoreManager.getIt().getList().size());
        assertEquals("userName", ScoreManager.getIt().getList().get(5).getName());

        ScoreManager.getIt().sortScores();
        assertEquals("userName", ScoreManager.getIt().getList().get(0).getName());
    }

    @Test
    public void test_DeleteScore()   throws IOException 
    {
        ScoreManager.getIt().setList(testList);
        ScoreManager.getIt().deleteScore(ScoreManager.getIt().getList().get(0));
        assertEquals(4, ScoreManager.getIt().getList().size());
        assertEquals("name1", ScoreManager.getIt().getList().get(0).getName());
    }
    
    @Test
    public void test_LoadScores()   throws IOException 
    {
        ScoreManager.getIt().setList(testList);

        ScoreManager.getIt().addScore(new Score("userName", now, 100));
        ScoreManager.getIt().sortScores();
        ScoreManager.getIt().saveScores();
        ScoreManager.getIt().loadScores();
        assertEquals("userName", ScoreManager.getIt().getList().get(0).getName());
    }
}