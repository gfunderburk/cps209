import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class W_Credits {

    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());


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
        AppGUI.windowLoad("Main Menu", getClass().getResource("W_MainMenu.fxml"), null);
    }
    

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


}