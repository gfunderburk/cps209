
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

//-----------------------------------------------------------
//File:   Window_MainWindow.java
//Desc:   This class is the default screen upon startup.
//----------------------------------------------------------- 

public class W_MainMenu {

    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());
    final AudioClip THEME = new AudioClip(getClass().getResource("/media/maintheme.mp3").toString());

    // --------------- //
    // View Variables //
    // --------------- //

    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();

    private static Stage gameStage;
    

    





    // ------------- //
    // GUI Elements //
    // ------------- //

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    @FXML
    void btn_newGameClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();   

        // items for the dialog
        Integer difficulty[] = { 1, 2, 3 };

        // create a choice dialog
        ChoiceDialog d = new ChoiceDialog(difficulty[0], difficulty);
        d.setHeaderText("Please Select Difficulty Level");

        // var loader = new FXMLLoader(getClass().getResource("W_InGame.fxml"));
        // var scene = new Scene(loader.load());

        // var stage = new Stage();
        // stage.getIcons().add(new Image("/icons/terminatorIcon2.png"));
        // stage.setScene(scene);
        // stage.setHeight(600);
        // stage.setWidth(800);
        // gameStage = stage;
        d.showAndWait().ifPresent(choice -> 
        {
            //THEME.play();
            // stage.show();            
            //newStage.close();
            try 
            {
                AppGUI.windowLoad(oldStage, newStage, "Game", getClass().getResource("W_InGame.fxml"), true, d.getSelectedItem());
                // game.initialize((int) d.getSelectedItem());
            } 
            catch (InterruptedException | IOException e) 
            {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void btn_loadSavedGameClicked(ActionEvent event) throws IOException, InterruptedException {
        // W_CRUDsaves CRUDInstance = W_CRUDsaves.instance();
        // CRUDInstance.initialize();

        // Play button click sounds
        BTN_CLICK.play();

        AppGUI.windowLoad(oldStage, newStage, "Saved Games", getClass().getResource("W_CRUDsaves.fxml"), true, null);
        

    }

    @FXML
    void btn_ControlsClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad(oldStage, newStage, "Controls / How to Play", getClass().getResource("W_Controls.fxml"), true, null);
    }

    @FXML
    void btn_scoreboardClicked(ActionEvent event) throws IOException, InterruptedException {
        //W_Scoreboard scoreboardInstance = W_Scoreboard.getInstance();      
        BTN_CLICK.play();
        AppGUI.windowLoad(oldStage, newStage, "Scoreboard", getClass().getResource("W_Scoreboard.fxml"), true, null);
    }

    @FXML
    void btn_creditsClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad(oldStage, newStage, "Credits", getClass().getResource("W_Credits.fxml"), true, null);
    }

    public static Stage getGameStage() {
        return gameStage;
    }

    public static void setGameStage(Stage stage) {
        gameStage = stage;
    }

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //

}