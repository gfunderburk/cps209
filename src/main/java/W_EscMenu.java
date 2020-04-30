/* --------------------------------------------------------------------------------------------- 
File:   W_EscMenu.java
Desc.   EscMenu window pauses the in-game state when initialized and displays options to:
        Resume the current game state, Save the current game state, or quit the game state. 
Primary Author: Jeremiah Cox 
--------------------------------------------------------------------------------------------- */


import Game_model.GameSounds;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import Data_model.Cereal;
import Data_model.Score;
import Data_model.ScoreManager;
import Game_model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class W_EscMenu implements AppInitialize{
    
    
    // --------------- //
    //  Game Singleton //
    // --------------- //

    Game game = Game.getIt();


    //  ---------- //
    //  Variables  //
    // ----------  //

    
    private LocalDateTime newDate; // Variable for date used in score save.
    private String newName;        // Variable for name used in score save.



    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //



    /**
     * This method is called when the save button is clicked.
     * Upon click, game is Serialized and stored in cereal.dat.
     * @throws FileNotFoundException
     */
    @FXML
    void onSaveClicked() throws FileNotFoundException {
        Cereal cereal = new Cereal(game, game.getDt(), "");
        cereal.SerializeGame();
        
        var alert = new Alert(AlertType.INFORMATION, "Game Saved Successfully!");
        alert.setHeaderText(null);
        alert.show();
    }

    /**
     * This method is called with the Quit button is clicked.
     * The user is asked for their name and the score is saved before High Scores screen is shown.
     * @param event
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void btn_onQuitClicked(ActionEvent event) throws IOException, InterruptedException {
        var scoreManager = ScoreManager.getIt();
        GameSounds.it().BTN_CLICK.play();
        
        AppGUI.getPopupStage().close();

        TextInputDialog dialog = new TextInputDialog();
        
        dialog.setHeaderText("Please enter player name.");
        Optional<String> playerName = dialog.showAndWait();
        playerName.ifPresent(name -> {
            newName = name;
            newDate = LocalDateTime.now();
            var newScore = new Score(name, newDate, game.getScore());
            scoreManager.getList().clear();
            scoreManager.loadScores();
            scoreManager.addScore(newScore);
            scoreManager.saveScores();            
        });

        AppGUI.windowLoad(this, "High Scores", "W_ScoreBoard.fxml", null);   
    }

    /**
     * This method is called when the Resume button is clicked. 
     * Game screen is shown again and game is played.
     * @param event
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void btn_onResumeClicked(ActionEvent event) throws IOException, InterruptedException {
        // Return to game window
        GameSounds.it().BTN_CLICK.play();
        AppGUI.getPopupStage().close();
        Game.getIt().play();
    }

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //

    /**
     * This method is called upon initialization of this class and game is paused.
     */
    public void initialize() {
        game.pause();
    }

}