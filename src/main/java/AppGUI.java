import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        AppGUI.windowLoad(null, stage, "Title Screen", getClass().getResource("W_TitleScreen.fxml"), false, null);


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
        newStage.setWidth(1340);
        newStage.setHeight(600);
        
        newStage.setFullScreen(true);
        newStage.setFullScreenExitHint("");
        newStage.getIcons().add(new Image("/icons/terminatorIcon2.png"));
        currentStage = newStage;
        newStage.show();
        
        if(closeOldWindow){
            if(oldStage.isShowing()){
                oldStage.close();
            }
        }

        if(newWindowName.equals("Game")){   
            W_MainMenu.setGameStage(newStage);         
            W_InGame game = loader.getController();
            game.ingameScene = scene;
            game.initialize((int)windowInitData);

            // if(windowInitData != null) Game.getIt().startNewGame();
        }
    }

}
