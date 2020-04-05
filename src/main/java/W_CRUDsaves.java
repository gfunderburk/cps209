import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class W_CRUDsaves {
    @FXML
    VBox vbox_CRUDSaves;

    @FXML
    void initialize() throws InterruptedException {
        vbox_CRUDSaves.getChildren().add(createButton("Load Saved Game", "btn_load", false));
        vbox_CRUDSaves.getChildren().add(createButton("Return to Main Menu", "btn_back", false));
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