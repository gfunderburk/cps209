import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class W_TitleScreen {
    Stage stage = AppGUI.getStage();

    @FXML
    void onContinueClicked(ActionEvent event) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("W_MainMenu.fxml"));
        var scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }
}