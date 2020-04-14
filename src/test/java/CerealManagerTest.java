import org.junit.Test;
import Data_model.*;
import Game_model.*;

import static org.junit.Assert.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class CerealManagerTest {

    private Game game = null;
    private LocalDateTime now = LocalDateTime.now();

    ArrayList<Cereal> testList = new ArrayList<Cereal>(Arrays.asList(
        new Cereal(game, now, "name0"),
        new Cereal(game, now, "name1"),
        new Cereal(game, now, "name2"),
        new Cereal(game, now, "name3"),
        new Cereal(game, now, "name4")
    ));
    

    @Test
    public void test_AddSaves()   throws IOException 
    {
        CerealManager.setList(testList);
        CerealManager.addCerealFile(new Cereal(game, now.plusHours(5), "bob"));
        assertEquals(6, CerealManager.getList().size());
        assertEquals("bob", CerealManager.getList().get(5).getName());
    }

    @Test
    public void test_SortSaves()   throws IOException 
    {
        CerealManager.setList(testList);
        CerealManager.addCerealFile(new Cereal(game, now.plusHours(5), "bob"));
        assertEquals(6, CerealManager.getList().size());
        assertEquals("bob", CerealManager.getList().get(5).getName());

        CerealManager.sortList();
        assertEquals("bob", CerealManager.getList().get(0).getName());
    }

    @Test
    public void test_DeleteSave()   throws IOException 
    {
        CerealManager.setList(testList);
        CerealManager.deleteCereal(CerealManager.getList().get(0));
        assertEquals(4, CerealManager.getList().size());
        assertEquals("name1", CerealManager.getList().get(0).getName());
    }

    @Test
    public void test_LoadSaveDir()   throws IOException 
    {
        CerealManager.setList(testList);

        Cereal testSave = new Cereal(game, now, "testSave");
        CerealManager.saveCerealFile(testSave.getGame());
        CerealManager.sortList();
        CerealManager.loadCerealDir();
        assertEquals("testSave", CerealManager.getList().get(0).getName());
    }
}

