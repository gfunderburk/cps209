/* --------------------------------------------------------------------------------------------- 
File:   W_Controls.java
Desc.   Controls window explains the controls and how to play the in-game to the user/player.
--------------------------------------------------------------------------------------------- */

import java.io.IOException;

import Game_model.GameSounds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class W_Controls {

    // --------------- //
    // Media Elements //
    // --------------- //



    //  --------------- //
    //  View Variables  //
    // ---------------  //




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
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        // TODO: Update Help and Controls with a game play description
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    }
}