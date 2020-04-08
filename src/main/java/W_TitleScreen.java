import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class W_TitleScreen {


    //  --------------- //
    //  View Variables  //
    // ---------------  //
    final AudioClip THEME = new AudioClip(getClass().getResource("/media/maintheme.mp3").toString());

    //  --------------- //
    //  Stages          //
    // ---------------  //
    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();


    //  ------------- //
    //  GUI Elements  //
    // -------------  //




    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException {
        AppGUI.windowLoad(oldStage, newStage, "Main Menu", getClass().getResource("W_MainMenu.fxml"));
        THEME.play();
    }


    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //

}