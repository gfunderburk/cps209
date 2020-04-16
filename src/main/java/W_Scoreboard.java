import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class W_Scoreboard {


    //  --------------- //
    //  View Variables  //
    // ---------------  //



    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();


    //  ------------- //
    //  GUI Elements  //
    // -------------  //

    // Create initialize method that can read array list and populate high scores? Do in MainWindow?




    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


    @FXML
    void btnMainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        AppGUI.windowLoad(oldStage, newStage, "Main Menu", getClass().getResource("W_MainMenu.fxml"), true, null);
    }


    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


}