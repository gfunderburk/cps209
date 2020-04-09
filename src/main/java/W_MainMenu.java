import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

//-----------------------------------------------------------
//File:   Window_MainWindow.java
//Desc:   This class is the default screen upon startup.
//----------------------------------------------------------- 

public class W_MainMenu {

    // --------------- //
    // View Variables //
    // --------------- //

    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();

    private static Stage gameStage;

    final AudioClip THEME = new AudioClip(getClass().getResource("/media/maintheme.mp3").toString());
    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());

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
        

        var loader = new FXMLLoader(getClass().getResource("W_InGame.fxml"));
        var scene = new Scene(loader.load());

        W_InGame game = loader.getController();
        var stage = new Stage();
        stage.getIcons().add(new Image("/icons/terminatorIcon2.png"));
        stage.setScene(scene);
        gameStage = stage;
        d.showAndWait().ifPresent(choice -> {
            THEME.play();
            stage.show();
            newStage.close();
            try {
                game.initialize((int) d.getSelectedItem());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void btn_loadSavedGameClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();
        var loader = new FXMLLoader(getClass().getResource("W_CRUDsaves.fxml"));
        var scene = new Scene(loader.load());


        newStage.setScene(scene);
        newStage.setTitle("Load/Save Game");
        newStage.show();

        if (oldStage.isShowing()) {
            oldStage.close();
        }
    }

    @FXML
    void btn_ControlsClicked(ActionEvent event) throws IOException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad(oldStage, newStage, "Controls / How to Play", getClass().getResource("W_Controls.fxml"));
    }

    @FXML
    void btn_scoreboardClicked(ActionEvent event) throws IOException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad(oldStage, newStage, "Scoreboard", getClass().getResource("W_Scoreboard.fxml"));
    }

    @FXML
    void btn_creditsClicked(ActionEvent event) throws IOException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad(oldStage, newStage, "Credits", getClass().getResource("W_Credits.fxml"));
    }

    public static Stage getGameStage() {
        return gameStage;
    }

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //

}