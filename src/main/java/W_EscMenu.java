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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class W_EscMenu implements AppGUI_popupWin{
    
    
    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());


    //  --------------- //
    //  View Variables  //
    // ---------------  //

    Game game = Game.getIt();
    private LocalDateTime newDate;
    private String newName;


    //  ------------- //
    //  GUI Elements  //
    // -------------  //




    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


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
            
            AppGUI.getPopupStage().close();
            

            TextInputDialog dialog = new TextInputDialog();
            
            dialog.setHeaderText("Please enter player name.");
            //dialog.setContentText("Please enter your name:");
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

            Cereal cereal = new Cereal(game, newDate, newName);
            cereal.SerializeGame();

            Game.getIt().closeGame();

            AppGUI.windowLoad("High Scores", getClass().getResource("W_ScoreBoard.fxml"), null);            
        } 
        else if (result.get() == btnNo) {
            // Return to main menu and close the game window
            AppGUI.getPopupStage().close();         
            Game.getIt().closeGame();
            AppGUI.windowLoad("Main Menu", getClass().getResource("W_MainMenu.fxml"), null);


        } else {
            // ... user chose CANCEL or closed the dialog
        }        
    }

    @FXML
    void btn_onResumeClicked(ActionEvent event) throws IOException, InterruptedException {
        // Return to game window
        BTN_CLICK.play();
        AppGUI.getPopupStage().close();
        Game.getIt().play();
    }

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //

    
    public void initialize() {
        game.pause();
    }

}