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
        stage.setHeight(900);
        stage.setWidth(1440);
        stage.show();

    }

    public static void setStage(Stage newStage) {
        currentStage = newStage;
    }

    public static Stage getStage() {
        return currentStage;
    }

    public static void windowLoad(Stage oldStage, Stage newStage, String newWindowName, URL windowURL, boolean closeOldWindow, Object windowInitData) throws IOException,
            InterruptedException {

        var loader = new FXMLLoader(windowURL);
        var scene = new Scene(loader.load());

        newStage.setScene(scene);
        newStage.setTitle(newWindowName);
        newStage.setWidth(1440);
        newStage.setHeight(900);
        newStage.getIcons().add(new Image("/icons/terminatorIcon2.png"));

        newStage.show();
        
        if(closeOldWindow){
            if(oldStage.isShowing()){
                oldStage.close();
            }
        }

        if(newWindowName.equals("Game")){            
            W_InGame game = loader.getController();
            game.ingameScene = scene;
            game.initialize((int)windowInitData);
        }
    }

}
