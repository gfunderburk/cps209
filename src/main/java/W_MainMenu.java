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
import javafx.scene.media.AudioClip;

//-----------------------------------------------------------
//File:   Window_MainWindow.java
//Desc:   This class is the default screen upon startup.
//----------------------------------------------------------- 

public class W_MainMenu {
    Game game = Game.getIt();

    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());
    final AudioClip THEME = new AudioClip(getClass().getResource("/media/maintheme.mp3").toString());

    // --------------- //
    // View Variables //
    // --------------- //
    String difficulty = "";
    int gameLvl;
    
    // ------------- //
    // GUI Elements //
    // ------------- //


    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //


    @FXML
    void btn_newGameClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();   

        // items for the dialog
        String difficulty[] = {"Easy", "Medium", "Hard" };

        // create a choice dialog
        ChoiceDialog d = new ChoiceDialog(difficulty[0], difficulty);
        game.setGameLvl(1);
        d.setHeaderText("Please Select Difficulty Level");
        d.showAndWait().ifPresent(choice -> 
        {
            THEME.stop();
            // stage.show();            
            //newStage.close();
            try 
            {
                String result = (String) d.getSelectedItem();
                AppGUI.windowLoad("Game", getClass().getResource("W_InGame.fxml"), new Object[]{result, 1});
                // game.initialize((int) d.getSelectedItem());
            } 
            catch (InterruptedException | IOException e) 
            {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void btn_loadSavedGameClicked(ActionEvent event) throws IOException, InterruptedException {
        // W_CRUDsaves CRUDInstance = W_CRUDsaves.instance();
        // CRUDInstance.initialize();
        Cereal cereal = new Cereal(game, LocalDateTime.now(), "hello");

        BTN_CLICK.play();
        try(BufferedReader rd = new BufferedReader( new FileReader("cereal.dat")))
        {                       
            String line = rd.readLine();
            while (line != null) { 
                // var score = new Score(null, null, 0); // create a new score object
                // Score score = Score.deSerialize(line); // set the score date (name, date/time, score)

                // Make method to clear everything before loading in the saved stuff
                cereal.deSerialize(line);    // add the new score object to scores list
                System.out.println(line); 
                line = rd.readLine(); 
            } 
            rd.close(); 

        } 
        catch (IOException e) 
        { 
            System.out.println("Problem loading scores.dat"); 
        }
        
        if (game.getStateDiff() == StateDifficulty.EASY) {
            difficulty = "Easy";            
        }
        else if (game.getStateDiff() == StateDifficulty.MEDIUM) {
            difficulty = "Medium";
        }
        else if (game.getStateDiff() == StateDifficulty.HARD) {
            difficulty = "Hard";
        }
        AppGUI.windowLoad("Game", getClass().getResource("W_InGame.fxml"), new Object[]{difficulty, game.getGameLvl()});
    }

    @FXML
    void btn_ControlsClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad("Controls / How to Play", getClass().getResource("W_Controls.fxml"), null);
    }

    @FXML
    void btn_scoreboardClicked(ActionEvent event) throws IOException, InterruptedException {
        //W_Scoreboard scoreboardInstance = W_Scoreboard.getInstance();      
        BTN_CLICK.play();
        AppGUI.windowLoad("Scoreboard", getClass().getResource("W_Scoreboard.fxml"), null);
    }

    @FXML
    void btn_creditsClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad("Credits", getClass().getResource("W_Credits.fxml"), null);
    }

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //

}