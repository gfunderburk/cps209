import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;

//------------------------------------------------------------------
//File:   Window_InGame.java
//Desc:   This class is for active in-game gameplay.
//------------------------------------------------------------------ 

public class W_InGame {

    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();

    // --------------- //
    // Media Elements //
    // --------------- //

    //final AudioClip AUDIO_RESTART = new AudioClip(getClass().getResource("/media/_filename_.wav").toString());

    // --------------- //
    // View Variables //
    // --------------- //

    boolean mouseWithinPane;

    // ------------- //
    // GUI Elements //
    // ------------- //

    @FXML
    VBox vbox_masterParent;
    @FXML
    Label lbl_coords;
    @FXML
    Button btn_esc;

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    @FXML
    void onMouseMoved(MouseEvent event) {
        if (mouseWithinPane) {
            lbl_coords.setText(String.format("(%d, %d)", (int) event.getX(), (int) event.getY()));
        }
    }

    /**
     * Action: closes GameWindow and ends the game instance.
     * 
     * @throws IOException
     */
    @FXML
    void onEscClicked() throws IOException {
        // Stage stage = (Stage) btn_esc.getScene().getWindow();
        // stage.close();
        AppGUI.windowLoad(oldStage, newStage, "Esc Menu", getClass().getResource("W_EscMenu.fxml"));
    }

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //

    int difficulty;

    @FXML
    void initialize(int difficultyLevel) throws InterruptedException {
        this.difficulty = difficultyLevel;
        System.out.println(this.difficulty);
        // vbox_masterParent.getChildren().add(createButton("Test Button","btn_return",
        // false));
    }

    /**
     * Creates a new Button object and sets its attributes.
     * 
     * @param name,       the ID of the button.
     * @param disableBtn, btn is initialized as disabled IF true.
     * @return newly created and attributed button.
     */
    private Button createButton(String btnText, String name, boolean disableBtn) {

        Button newButton = new Button(btnText);

        newButton.setId(name); // attach name
        newButton.getStyleClass().add("CSS_Class"); // attach style class
        if (disableBtn)
            newButton.setDisable(true); // set disable status

        return newButton;
    }

}
