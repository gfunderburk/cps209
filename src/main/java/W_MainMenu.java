/* --------------------------------------------------------------------------------------------- 
File:   W_MainMenu.java
Desc.   MainMenu window is the primary screen for the program.
        Its GUI displays access to all of the other secondary screens of the program. 
Primary Author: Jeremiah Cox 
--------------------------------------------------------------------------------------------- */


import Game_model.GameSounds;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import Data_model.Cereal;
import Game_model.Game;
import Game_model.Game.StateDifficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;


public class W_MainMenu implements AppInitialize{

    // ---------------- //
    //  Game Singleton  //
    // ---------------- //

    Game game = Game.getIt();

    
    // --------------- //
    // View Variables //
    // --------------- //


    StateDifficulty difficulty; // Difficulty level (Easy, Medium, Hard)
    int gameLvl; // Game level (1,2,3)
    

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //


    /**
     * Action: loads new game after getting difficulty level.
     */
    @FXML
    void btn_newGameClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        GameSounds.it().BTN_CLICK.play();   
        

        // items for the dialog
        String difficulty[] = {"Easy", "Medium", "Hard" };

        // create a choice dialog
        ChoiceDialog d = new ChoiceDialog(difficulty[0], difficulty);
        game.setGameLvl(1);
        d.setHeaderText("Please Select Difficulty Level");
        d.showAndWait().ifPresent(choice -> 
        {
            try 
            {
                String result = (String) d.getSelectedItem();
                if (result.equals("Easy")) {
                    this.difficulty = StateDifficulty.EASY;
                }
                else if (result.equals("Medium")){
                    this.difficulty = StateDifficulty.MEDIUM;
                } else {
                    this.difficulty = StateDifficulty.HARD;
                }
                AppGUI.windowLoad(this, "Game", "W_InGame.fxml", new Object[]{this.difficulty, 1, 0});
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        });
    }

    /**
     * Action: Loads saved game from cereal.dat.
     */
    @FXML
    void btn_loadSavedGameClicked(ActionEvent event) throws IOException, InterruptedException {
        
        Cereal cereal = new Cereal(game, LocalDateTime.now(), "hello");

        GameSounds.it().BTN_CLICK.play();
        try(BufferedReader rd = new BufferedReader( new FileReader("cereal.dat")))
        {                       
            String line = rd.readLine();
            while (line != null) { 
                cereal.deSerialize(line);    // add the new score object to scores list
                System.out.println(line); 
                line = rd.readLine(); 
            } 
            rd.close(); 
        } 
        catch (Exception e) 
        { 
            System.out.println("Problem loading cereal.dat"); 
        }
        
        AppGUI.windowLoad(this, "Game", "W_InGame.fxml", new Object[]{game.getStateDiff(), game.getGameLvl(), game.getScore()});
    }

    /**
     * Action: Loads controls/how to play menu screen.
     */
    @FXML
    void btn_ControlsClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Controls / How to Play", "W_Controls.fxml", null);
    }

    /**
     * Action: Loads High Scores screen.
     * @param event
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void btn_scoreboardClicked(ActionEvent event) throws IOException, InterruptedException {  
        GameSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Scoreboard", "W_Scoreboard.fxml", null);
    }

    /**
     * Action: loads credits screen.
     * @param event
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void btn_creditsClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Credits", "W_Credits.fxml", null);
    }

    /**
     * Is called when W_MainMenu is launched.
     */
    @Override
    public void initialize() {
        if(!GameSounds.it().THEME.isPlaying()) {
            GameSounds.it().THEME.play(); 
        } 
    }
}