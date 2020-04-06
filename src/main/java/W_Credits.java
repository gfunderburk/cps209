import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class W_Credits {


    //  --------------- //
    //  View Variables  //
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
    }
    

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


}