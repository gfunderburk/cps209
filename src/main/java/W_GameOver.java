/* --------------------------------------------------------------------------------------------- 
File:   GameOver.java
Desc.   GameOver window appears if the player is killed by hostile AI's.
        It displays the option to quit to MainMenu.
--------------------------------------------------------------------------------------------- */


import Game_model.GameSounds;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import Data_model.Score;
import Data_model.ScoreManager;
import Game_model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class W_GameOver implements AppInitialize{
    
    
    // --------------- //
    // Media Elements //
    // --------------- //



    //  --------------- //
    //  View Variables  //
    // ---------------  //

    Game game = Game.getIt();
    private LocalDateTime newDate;


    //  ------------- //
    //  GUI Elements  //
    // -------------  //




    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


    @FXML
    void btn_onQuitClicked(ActionEvent event) throws IOException, InterruptedException 
    {
        //Launch alert box with "Do you want to save before quitting?" with Yes and No buttons
        // - Yes = Save game
        // - No  = Main Menu
        var scoreManager = ScoreManager.getIt();
        GameSounds.it().BTN_CLICK.play();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to save your score?");        

        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnYes, btnNo, btnCancel);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == btnYes) 
        {
            // Save the score and get the name before showing high scores screen
            GameSounds.it().BTN_CLICK.play();            
            AppGUI.getPopupStage().close();            

            TextInputDialog dialog = new TextInputDialog();
            
            dialog.setHeaderText("Please enter player name.");
            Optional<String> playerName = dialog.showAndWait();
            playerName.ifPresent(name -> {
                newDate = LocalDateTime.now();
                var newScore = new Score(name, newDate, game.getScore());
                scoreManager.getList().clear();
                scoreManager.loadScores();
                scoreManager.addScore(newScore);
                scoreManager.saveScores();            
            });

            AppGUI.windowLoad(this, "High Scores", "W_ScoreBoard.fxml", null);            
        } 
        else if (result.get() == btnNo)
        {
            // Return to main menu and close the game window
            GameSounds.it().BTN_CLICK.play();
            AppGUI.getPopupStage().close();    
            AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
        } 
        else {
            // ... user chose CANCEL or closed the dialog
            GameSounds.it().BTN_CLICK.play();
        }        
    }

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


    public void initialize(){
        game.pause();
    } 
}