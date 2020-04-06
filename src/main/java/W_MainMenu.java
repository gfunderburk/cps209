import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//-----------------------------------------------------------
//File:   Window_MainWindow.java
//Desc:   This class is the default screen upon startup.
//----------------------------------------------------------- 


public class W_MainMenu {


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
    void btn_newGameClicked(ActionEvent event) throws IOException, InterruptedException {

        //TODO: USE         https://code.makery.ch/blog/javafx-dialogs-official/ 
        //      TO BUILD    a pop-up box to ask for difficulty level and a playername for the this game's playthrough
        //      THEN        open up W_InGame.fxml, passing it the recently input parameters with some of the code below.

    }    

    @FXML
    void btn_loadSavedGameClicked(ActionEvent event) throws IOException, InterruptedException {
        var loader = new FXMLLoader(getClass().getResource("W_CRUDsaves.fxml"));
        var scene = new Scene(loader.load());

        // W_CRUDsaves crudsaves = loader.getController();
        // crudsaves.initialize();       

        // var stage = new Stage();
        // stage.setScene(scene);
        // stage.show();

        newStage.setScene(scene);
        newStage.setTitle("Load/Save Game");
        newStage.show();
        
        if(oldStage.isShowing()){
            oldStage.close();
        }
    }

    @FXML
    void btn_ControlsClicked(ActionEvent event) throws IOException {
        AppGUI.windowLoad(oldStage, newStage, "Controls / How to Play", getClass().getResource("W_Controls.fxml"));
    }

    @FXML
    void btn_scoreboardClicked(ActionEvent event) throws IOException {
        AppGUI.windowLoad(oldStage, newStage, "Scoreboard", getClass().getResource("W_Scoreboard.fxml"));
    }

    @FXML
    void btn_creditsClicked(ActionEvent event) throws IOException {
        AppGUI.windowLoad(oldStage, newStage, "Credits", getClass().getResource("W_Credits.fxml"));
    }

    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //

}