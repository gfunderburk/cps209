import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

//------------------------------------------------------------------
//File:   AppGUI.java
//Desc:   This file is the intermediary class that is called,   
//        when launching the program to its default screen.
//------------------------------------------------------------------ 

public class AppGUI extends Application {


    //  --------------- //
    //  Media Elements  //
    // ---------------  //
    
                                                                                                

    private static Stage currentStage;

    @Override
    public void start(Stage stage) throws Exception {
        AppGUI.currentStage = stage;
        var load_titleScreen = new FXMLLoader(getClass().getResource("W_TitleScreen.fxml"));
        var scene_a = new Scene(load_titleScreen.load());
        stage.getIcons().add(new Image("/icons/terminatorIcon2.png")); // https://stackoverflow.com/questions/58241811/set-top-left-image-on-dialog-pane-with-javafx
        stage.setScene(scene_a);
        stage.setHeight(600);
        stage.setWidth(800);
        stage.show();

    }

    public static void setStage(Stage newStage) {
        currentStage = newStage;
    }

    public static Stage getStage() {
        return currentStage;
    }

    public static void windowLoad(Stage oldStage, Stage newStage, String newWindowName, URL windowURL) throws IOException {

        var loader = new FXMLLoader(windowURL);
        var scene = new Scene(loader.load());

        newStage.setScene(scene);
        newStage.setTitle(newWindowName);
        newStage.setHeight(600);
        newStage.setWidth(800);

        newStage.show();
        
        if(oldStage.isShowing()){
            oldStage.close();
        }
    }

}
