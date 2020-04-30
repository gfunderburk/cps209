/* --------------------------------------------------------------------------------------------- 
File:   W_Controls.java
Desc.   Controls window explains the controls and how to play the in-game to the user/player.
Primary Author: Jeremiah Cox 
--------------------------------------------------------------------------------------------- */

import java.io.IOException;
import Game_model.GameSounds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class W_Controls {

    /**
     * This method is called when the main menu button is pressed.
     * Button click sound is played and Main Menu is launched. 
     */
    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    }
}