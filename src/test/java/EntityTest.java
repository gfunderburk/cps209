import org.junit.Test;
import Game_model.*;
import Game_model.Entity;
import Game_model.Game;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// EntityTest.java is the primary file to test Game functionality. 
// Since most all of the game logic hinges upon responding to projectile collisions, 
//    little if anything can be tested without projectile functionality. 

public class EntityTest {


    ArrayList<Entity> testList = new ArrayList<Entity>(Arrays.asList(
        new EK_Scenery(0, 0, 0, null, null),
        new EK_Scenery(0, 0, 0, null, null),
        new EK_Scenery(0, 0, 0, null, null),
        new EK_Scenery(0, 0, 0, null, null)
    ));

    @Test
    public void test_EntityDeath() 
    {
        Game.getIt().setEntityList(testList);

        assertEquals(4, Game.getIt().getEntityList().size());

        var testItem = (EK_Scenery)Game.getIt().getEntityList().get(0); // Simulate projectile striking and killing Entity, the Entity will be removed from the Entity List.
        testItem.deathEvent(); 

        assertEquals(3, Game.getIt().getEntityList().size());
    }

    @Test
    public void test_GameOverCheck()   throws IOException 
    {
        // Game.getIt().setHostilesLeft(5);
        
        assertEquals(false, Game.getIt().isGameOver());

        // Game.getIt().setHostilesLeft(0);

        Game.getIt().checkGameOver();  // Should detect a gameOver event and change game state accordingly.

        assertEquals(true, Game.getIt().isGameOver());
    }

}





