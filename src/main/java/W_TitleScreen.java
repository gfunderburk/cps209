/* --------------------------------------------------------------------------------------------- 
File:   W_TitleScreen.java
Desc.   TitleScreen window displays the welcome graphic for the program.
--------------------------------------------------------------------------------------------- */


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

public class W_TitleScreen {

    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = AppGUI.audioClip(this, "btnClick_seatBelt.mp3");
    final AudioClip THEME     = AppGUI.audioClip(this, "maintheme.mp3");

    //  --------------- //
    //  View Variables  //
    // ---------------  //
    

    //  ------------- //
    //  GUI Elements  //
    // -------------  //




    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        BTN_CLICK.play();
        new W_Scoreboard();
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
        THEME.play();
    }


    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //

}