/* --------------------------------------------------------------------------------------------- 
File:   W_Controls.java
Desc.   Controls window explains the controls and how to play the in-game to the user/player.
--------------------------------------------------------------------------------------------- */

import java.io.IOException;
import Game_model.GameSounds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class W_Controls {

    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    }
}