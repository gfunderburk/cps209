/* --------------------------------------------------------------------------------------------- 
File:   W_Credits.java
Desc.   Credits window lists which team member was responsable which section of the program.
--------------------------------------------------------------------------------------------- */


import Game_model.GameSounds;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class W_Credits {

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


    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        GameSounds.it().BTN_CLICK.play();
        AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
    }
    

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


}