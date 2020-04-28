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

public class W_AllLevelsCompleted implements AppGUI_popupWin{
    
    
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

        AppGUI.windowLoad("High Scores", getClass().getResource("W_ScoreBoard.fxml"), null);                    
    }

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


    public void initialize(){
        game.pause();
    } 
}