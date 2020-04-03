import org.junit.Test;
import Data_model.*;
import Game_model.Game;

import static org.junit.Assert.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class CerealManagerTest {

    Game game = null;
    LocalDateTime now = LocalDateTime.now();

    ArrayList<Cereal> testList = new ArrayList<Cereal>(Arrays.asList(
        new Cereal(game, "name0", now),
        new Cereal(game, "name1", now),
        new Cereal(game, "name2", now),
        new Cereal(game, "name3", now),
        new Cereal(game, "name4", now)
    ));
    

    @Test
    public void test_Add_SortSaves()   throws IOException 
    {
        CerealManager.setList(testList);
        CerealManager.addCerealFile(new Cereal(game, "bob", now.plusHours(5)));
        assertEquals(6, CerealManager.getList().size());
        assertEquals("bob", CerealManager.getList().get(5).getName());

        CerealManager.sortList();
        System.out.println(CerealManager.Serialize());
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

        CerealManager.saveCerealFile("userName");
        CerealManager.sortList();
        CerealManager.loadCerealDir();
        assertEquals("userName", CerealManager.getList().get(0).getName());
    }
}