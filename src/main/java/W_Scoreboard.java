import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class W_Scoreboard {


    //  --------------- //
    //  View Variables  //
    // ---------------  //
    private static W_Scoreboard scoreboardInstance = new W_Scoreboard();

    public static W_Scoreboard getInstance() {
        return scoreboardInstance;
    }


    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();


    //  ------------- //
    //  GUI Elements  //
    // -------------  //

    @FXML
    VBox vbox_scoreboard;





    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


    @FXML
    void btnMainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        AppGUI.windowLoad(oldStage, newStage, "Main Menu", getClass().getResource("W_MainMenu.fxml"), true, null);
    }

    

    public VBox getVbox_scoreboard() {
        return vbox_scoreboard;
    }

    public void setVbox_scoreboard(VBox vbox_scoreboard) {
        this.vbox_scoreboard = vbox_scoreboard;
    }



    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


}