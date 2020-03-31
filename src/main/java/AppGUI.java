
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


//------------------------------------------------------------------
//File:   AppGUI.java
//Desc:   This file is the intermediary class that is called,   
//        when launching the program to its default screen.
//------------------------------------------------------------------ 


public class AppGUI extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        var loader = new FXMLLoader(getClass().getResource("Window_MainMenu.fxml"));
        var scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("Start Menu");
        stage.show();
    }
}
