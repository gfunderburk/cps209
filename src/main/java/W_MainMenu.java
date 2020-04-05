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
    Stage stage = AppGUI.getStage();

    @FXML
    void btn_newGameClicked(ActionEvent event) throws IOException, InterruptedException {

        var loader = new FXMLLoader(getClass().getResource("W_InGame.fxml"));
        var scene = new Scene(loader.load());

        W_InGame gameWindow = loader.getController();
        gameWindow.initialize();       

        stage.setScene(scene);
        stage.show();
    }    

    @FXML
    void btn_loadSavedGameClicked(ActionEvent event) throws IOException, InterruptedException {
        var loader = new FXMLLoader(getClass().getResource("W_CRUDsaves.fxml"));
        var scene = new Scene(loader.load());

        W_CRUDsaves crudsaves = loader.getController();
        crudsaves.initialize();       

        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btn_ControlsClicked(ActionEvent event) {

    }

    @FXML
    void btn_highScoresClicked(ActionEvent event) {

    }

    @FXML
    void btn_aboutClicked(ActionEvent event) {

    }
}