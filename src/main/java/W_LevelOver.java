/* --------------------------------------------------------------------------------------------- 
File:   W_LevelOver.java
Desc.   LevelOver window appears if the player kills all hostiles.
        It displays options to either prgress to the next level or quit the game.
--------------------------------------------------------------------------------------------- */


import Game_model.GameSounds;
import java.io.IOException;
import Game_model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class W_LevelOver implements AppInitialize {
    Game game = Game.getIt();


    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


    @FXML
    void btn_onMainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    } 


    @FXML
    void btn_onNextClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Game", "W_InGame.fxml", new Object[]{game.getStateDiff(), game.getGameLvl() + 1, game.getScore()});      
    } 


    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


    public void initialize(){
        game.pause();
    } 
}