

import javafx.util.Duration;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//------------------------------------------------------------------
//File:   AppGUI.java
//Desc:   This file is the intermediary class that is called,   
//        when launching the program to its default screen.
//------------------------------------------------------------------ 

public class AppGUI extends Application {

    private static Stage currentStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.currentStage = stage;        
        var load_titleScreen = new FXMLLoader(getClass().getResource("W_TitleScreen.fxml"));
        var scene_a = new Scene(load_titleScreen.load());

        stage.setScene(scene_a);
        stage.show();


    }

    public static Stage getStage() {
        return currentStage;
    }

}
