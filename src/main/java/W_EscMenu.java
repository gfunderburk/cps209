/* --------------------------------------------------------------------------------------------- 
File:   W_EscMenu.java
Desc.   EscMenu window pauses the in-game state when initialized and displays options to:
        Resume the current game state, Save the current game state, or quit the game state. 
--------------------------------------------------------------------------------------------- */


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
    // Media Elements //
    // --------------- //



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
    void onSaveClicked() throws FileNotFoundException {
        Cereal cereal = new Cereal(game, game.getDt(), "");
        cereal.SerializeGame();
        
        var alert = new Alert(AlertType.INFORMATION, "Game Saved Successfully!");
        alert.setHeaderText(null);
        alert.show();
    }

    @FXML
    void btn_onQuitClicked(ActionEvent event) throws IOException, InterruptedException {
        var scoreManager = ScoreManager.getIt();
        AppSounds.it().BTN_CLICK.play();
        
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

    @FXML
    void btn_onResumeClicked(ActionEvent event) throws IOException, InterruptedException {
        // Return to game window
        AppSounds.it().BTN_CLICK.play();
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