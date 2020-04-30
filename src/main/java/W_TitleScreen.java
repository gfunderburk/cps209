/* --------------------------------------------------------------------------------------------- 
File:   W_TitleScreen.java
Desc.   TitleScreen window displays the welcome graphic for the program.
Primary Author: Jeremiah Cox 
--------------------------------------------------------------------------------------------- */


import Game_model.GameSounds;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class W_TitleScreen {
    /**
     * Action: launches Main Menu screen and plays button click sound.
     */
    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        new W_Scoreboard();
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    }
}