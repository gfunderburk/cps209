/* --------------------------------------------------------------------------------------------- 
File:   W_AllLevelsCompleted.java
Desc.   AllLevelsCompleted window appears if the player kills all hostiles at level 3.
        It displays options to either save the player's score or to quit directly to MainMenu.
Author: Jeremiah Cox 
--------------------------------------------------------------------------------------------- */

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import Data_model.Cereal;
import Data_model.Score;
import Data_model.ScoreManager;
import Game_model.Game;
import Game_model.GameSounds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class W_AllLevelsCompleted implements AppInitialize{

    //  -------------------- //
    //    Game Singleton     //
    // --------------------  //

    Game game = Game.getIt();


    //  --------------- //
    //    Variables     //
    // ---------------  //

    private LocalDateTime newDate; // Contains the date to be used in score saving.
    private String newName; // Contains the name to be used in score saving.


    /**
     * Upon initialization of this class, the game is paused.
     */
    public void initialize(){
        game.pause();
    } 


    /**
     * This method is called when the quit button is clicked.
     * A sound is played and an confirmation box is launched, to ask if the player would like to save their score.
     */
    @FXML
    void btn_onQuitClicked(ActionEvent event) throws IOException, InterruptedException {
        var scoreManager = ScoreManager.getIt();
        GameSounds.it().BTN_CLICK.play();
        //Launch alert box with "Do you want to save before quitting?" with Yes and No buttons
        // - Yes = Save game
        // - No  = Main Menu
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to save your score?");
        

        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnYes, btnNo, btnCancel);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == btnYes) {
            // Save the score and get the name before showing high scores screen.
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
        else if (result.get() == btnNo) {
            // Return to main menu and close the game window
            GameSounds.it().BTN_CLICK.play();
            AppGUI.getPopupStage().close();     
            AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);


        } else {
            // ... user chose CANCEL or closed the dialog
            GameSounds.it().BTN_CLICK.play();
        }        
    }
}