/* --------------------------------------------------------------------------------------------- 
File:   W_Credits.java
Desc.   Credits window lists which team member was responsable which section of the program.
Primary Author: Jeremiah Cox 
--------------------------------------------------------------------------------------------- */


import Game_model.GameSounds;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class W_Credits {

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