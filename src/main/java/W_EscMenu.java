import java.io.IOException;
import java.time.LocalDate;
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
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class W_EscMenu {
    Game game = Game.getIt();
    
    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());


    //  --------------- //
    //  View Variables  //
    // ---------------  //



    Stage oldStage = AppGUI.getStage();
    Stage newStage = new Stage();
    Stage gameStage = W_MainMenu.getGameStage();


    //  ------------- //
    //  GUI Elements  //
    // -------------  //




    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //



    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


    @FXML
    void btn_onQuitClicked(ActionEvent event) throws IOException, InterruptedException {
        var scoreManager = ScoreManager.getIt();
        BTN_CLICK.play();
        //Launch alert box with "Do you want to save before quitting?" with Yes and No buttons
        // - Yes = Save game
        // - No  = Main Menu
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to save before quitting?");
        

        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnYes, btnNo, btnCancel);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == btnYes) {
            // Save the game and close the game and esc windows
            gameStage.close();
            
            TextInputDialog dialog = new TextInputDialog();
            
            dialog.setHeaderText("Please enter player name.");
            //dialog.setContentText("Please enter your name:");
            Optional<String> playerName = dialog.showAndWait();
            playerName.ifPresent(name -> {
                System.out.println("Your name: " + name);
                var newDate = LocalDateTime.now();
                var newScore = new Score(name, newDate, game.getScore());
                scoreManager.getList().clear();
                scoreManager.loadScores();
                scoreManager.addScore(newScore);
                scoreManager.saveScores();
                game.setGameOver(true);

                //TODO: write code to call game save methods here
            });

            AppGUI.windowLoad(oldStage, newStage, "High Scores", getClass().getResource("W_ScoreBoard.fxml"), true, null);            
        } 
        else if (result.get() == btnNo) {
            // Return to main menu and close the game window
            gameStage.close();
            game.setGameOver(true);            
            AppGUI.windowLoad(oldStage, newStage, "Main Menu", getClass().getResource("W_MainMenu.fxml"), true, null);

        } else {
            // ... user chose CANCEL or closed the dialog
            AppGUI.windowLoad(oldStage, newStage, "Esc Menu", getClass().getResource("W_EscMenu.fxml"), true, null);            
        }        
    }

    @FXML
    void btn_onResumeClicked(ActionEvent event) throws IOException, InterruptedException {
        BTN_CLICK.play();
        // Return to game window
        newStage.close();
        gameStage.show();
        Game.getIt().play();
    }
}