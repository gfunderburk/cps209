/* --------------------------------------------------------------------------------------------- 
File:   W_Controls.java
Desc.   Controls window explains the controls and how to play the in-game to the user/player.
--------------------------------------------------------------------------------------------- */


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

public class W_Controls {

    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = AppGUI.audioClip(this, "btnClick_seatBelt.mp3");


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
        BTN_CLICK.play();
        // TODO: Update Help and Controls with a game play description
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    }
}