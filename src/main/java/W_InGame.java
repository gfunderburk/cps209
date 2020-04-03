import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;

//------------------------------------------------------------------
//File:   Window_InGame.java
//Desc:   This class is for active in-game gameplay.
//------------------------------------------------------------------ 


public class W_InGame {


    //  --------------- //
    //  Media Elements  //
    // ---------------  //


    final AudioClip AUDIO_RESTART = new AudioClip(getClass().getResource("/media/_filename_.wav").toString()); 


    //  --------------- //
    //  View Variables  //
    // ---------------  //


    boolean mouseWithinPane;


    //  ------------- //
    //  GUI Elements  //
    // -------------  //


    @FXML VBox vbox_masterParent;
    @FXML Label lbl_coords;
    @FXML Button btn_quit;


    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //


    @FXML
    void onMouseMoved(MouseEvent event) {
        if(mouseWithinPane){
            lbl_coords.setText(String.format("(%d, %d)", (int) event.getX(), (int) event.getY()));
        }
    }
    
    /** Action: closes GameWindow and ends the game instance. */
    @FXML
    void btn_quit_click() {
        AUDIO_RESTART.play();
        Stage stage = (Stage) btn_quit.getScene().getWindow();
        stage.close();
    }
    

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


    @FXML
    void initialize() throws InterruptedException {
        vbox_masterParent.getChildren().add(createButton("btn_ready", true));
    }


    /**
     * Creates a new Button object and sets its attributes.
     * @param name, the ID of the button.
     * @param disableBtn, btn is initialized as disabled IF true.
     * @return newly created and attributed button.
     */
    private Button createButton(String name, boolean disableBtn) {

        Button newButton = new Button("Created Button");

        newButton.setId(name);                      // attach name
        newButton.getStyleClass().add("CSS_Class"); // attach style class
        if(disableBtn) newButton.setDisable(true);  // set disable status

        return newButton;
    }


}
