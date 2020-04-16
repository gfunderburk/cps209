import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class W_CRUDsaves {


    //  --------------- //
    //  View Variables  //
    // ---------------  //



    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();


    //  ------------- //
    //  GUI Elements  //
    // -------------  //


    @FXML
    VBox vbox_CRUDSaves;



    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        // TODO: Save input

        AppGUI.windowLoad(oldStage, newStage, "Main Menu", getClass().getResource("W_MainMenu.fxml"), true, null);
    }

    @FXML
    void btn_loadSavedGame(ActionEvent event) {
        // TODO: Load the game from the .dat file ArrayList 
        // using the selected row number as the index
    }



    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //



    @FXML
    void initialize() throws InterruptedException {
        // vbox_CRUDSaves.getChildren().add(createButton("Load Saved Game", "btn_load", false));
        // vbox_CRUDSaves.getChildren().add(createButton("Return to Main Menu", "btn_back", false));
    }


    /**
     * Creates a new Button object and sets its attributes.
     * @param name, the ID of the button.
     * @param disableBtn, btn is initialized as disabled IF true.
     * @return newly created and attributed button.
     */
    private Button createButton(String btnText, String name, boolean disableBtn) {

        Button newButton = new Button(btnText);

        newButton.setId(name);                      // attach name
        newButton.getStyleClass().add("CSS_Class"); // attach style class
        if(disableBtn) newButton.setDisable(true);  // set disable status

        return newButton;
    }
}