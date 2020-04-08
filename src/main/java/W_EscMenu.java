import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public class W_EscMenu {


    //  --------------- //
    //  View Variables  //
    // ---------------  //



    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();
    Stage gameStage = W_MainMenu.getGameStage();


    //  ------------- //
    //  GUI Elements  //
    // -------------  //




    //  ------------ //
    //  GUI Methods  //     (DIRECT USER EVENTS)
    // ------------  //



    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


    @FXML
    void btn_onQuitClicked(ActionEvent event) throws IOException {
        //Launch alert box with "Do you want to save before quitting?" with Yes and No buttons
        // - Yes = Save game
        // - No  = Main Menu
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to save before quitting?");

        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnYes, btnNo, btnCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnYes) {
            // Save the game and close the game and esc windows
            gameStage.close();
            
            TextInputDialog dialog = new TextInputDialog();
            
            dialog.setHeaderText("Please enter player name.");
            //dialog.setContentText("Please enter your name:");
            Optional<String> playerName = dialog.showAndWait();
            playerName.ifPresent(name -> System.out.println("Your name: " + name));

            AppGUI.windowLoad(oldStage, newStage, "High Scores", getClass().getResource("W_ScoreBoard.fxml"));
            

        } else if (result.get() == btnNo) {
            // Return to main menu and close the game window
            gameStage.close();
            AppGUI.windowLoad(oldStage, newStage, "Main Menu", getClass().getResource("W_MainMenu.fxml"));
            

        } else {
            // ... user chose CANCEL or closed the dialog
            AppGUI.windowLoad(oldStage, newStage, "Esc Menu", getClass().getResource("W_EscMenu.fxml"));
            
        }
        
    }

    @FXML
    void btn_onResumeClicked(ActionEvent event) throws IOException {
        // Return to game window
        newStage.close();
    }
}