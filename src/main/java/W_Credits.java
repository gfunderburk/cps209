/* --------------------------------------------------------------------------------------------- 
File:   W_Credits.java
Desc.   Credits window lists which team member was responsable which section of the program.
--------------------------------------------------------------------------------------------- */


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

public class W_Credits {

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


    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        BTN_CLICK.play();
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    }
    

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


}