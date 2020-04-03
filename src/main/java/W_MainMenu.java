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

    @FXML
    void btn_start_click(ActionEvent event) throws IOException, InterruptedException {

        var loader = new FXMLLoader(getClass().getResource("Window_InGame.fxml"));
        var scene = new Scene(loader.load());

        W_InGame gameWindow = loader.getController();
        gameWindow.initialize();       

        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }    
}