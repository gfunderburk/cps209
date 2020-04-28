/* --------------------------------------------------------------------------------------------- 
File:   W_LevelOver.java
Desc.   LevelOver window appears if the player kills all hostiles.
        It displays options to either prgress to the next level or quit the game.
--------------------------------------------------------------------------------------------- */


import java.io.IOException;
import java.time.LocalDateTime;
import Data_model.ScoreManager;
import Game_model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

public class W_LevelOver implements AppInitialize {
    
    
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
    void btn_onMainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        AppSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    } 


    @FXML
    void btn_onNextClicked(ActionEvent event) throws IOException, InterruptedException {
        var scoreManager = ScoreManager.getIt();
        AppSounds.it().BTN_CLICK.play();


        // TextInputDialog dialog = new TextInputDialog();
        
        // dialog.setHeaderText("Please enter player name.");
        // Optional<String> playerName = dialog.showAndWait();
        // playerName.ifPresent(name -> {
        //     newName = name;
        //     newDate = LocalDateTime.now();
        //     var newScore = new Score(name, newDate, game.getScore());
        //     scoreManager.getList().clear();
        //     scoreManager.loadScores();
        //     scoreManager.addScore(newScore);
        //     scoreManager.saveScores();            
        // });

        AppGUI.windowLoad(this, "Game", "W_InGame.fxml", new Object[]{game.getStateDiff(), game.getGameLvl() + 1, game.getScore()});

        // Cereal cereal = new Cereal(game, newDate, newName);
        // cereal.SerializeGame();          
    } 


    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


    public void initialize(){
        game.pause();
    } 
}