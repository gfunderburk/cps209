import org.junit.Test;
import Data_model.*;

import static org.junit.Assert.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ScoreManagerTest {

    LocalDateTime now = LocalDateTime.now();

    // ArrayList<Score> testList = new ArrayList<Score>(Arrays.asList(
    //     new Score("name0", now, 10),
    //     new Score("name1", now, 11),
    //     new Score("name2", now, 12),
    //     new Score("name3", now, 13),
    //     new Score("name4", now, 14)
    // ));


    @Test
    public void test_Add_Scores() 
    {
        ScoreManager.getIt().getList().clear();
        ScoreManager.getIt().addScore(new Score("gunnar", now, 200));
        ScoreManager.getIt().addScore(new Score("jeremy", now, 600));
        ScoreManager.getIt().addScore(new Score("jonny", now, 400));
        ScoreManager.getIt().saveScores();
        assertEquals(3, ScoreManager.getIt().getList().size());
        assertEquals("jeremy", ScoreManager.getIt().getList().get(0).getName());
    }

    @Test
    public void test_SortScores() 
    {
        ScoreManager.getIt().getList().clear();
        ScoreManager.getIt().addScore(new Score("gunnar", now, 200));
        ScoreManager.getIt().addScore(new Score("jeremy", now, 600));
        ScoreManager.getIt().addScore(new Score("jonny", now, 400));
        ScoreManager.getIt().saveScores();
        assertEquals(3, ScoreManager.getIt().getList().size());
        assertEquals("jonny", ScoreManager.getIt().getList().get(1).getName());

        ScoreManager.getIt().sortScores();
        assertEquals("jeremy", ScoreManager.getIt().getList().get(0).getName());
    }

    @Test
    public void test_DeleteScore() throws IOException 
    {
        ScoreManager.getIt().getList().clear();
        //ScoreManager.getIt().setList(testList);
        ScoreManager.getIt().addScore(new Score("gunnar", now, 500));
        ScoreManager.getIt().addScore(new Score("jeremy", now, 400));
        ScoreManager.getIt().deleteScore(ScoreManager.getIt().getList().get(0));
        ScoreManager.getIt().saveScores();
        assertEquals(1, ScoreManager.getIt().getList().size());
        assertEquals("jeremy", ScoreManager.getIt().getList().get(0).getName());
    }
    
    @Test
    public void test_LoadScores()   throws IOException 
    {
        ScoreManager.getIt().getList().clear();
        //scoreManager.getIt().setList(testList);
        ScoreManager.getIt().addScore(new Score("userName", now, 100));
        ScoreManager.getIt().sortScores();
        ScoreManager.getIt().saveScores();
        ScoreManager.getIt().loadScores();
        assertEquals("userName", ScoreManager.getIt().getList().get(0).getName());
    }
}