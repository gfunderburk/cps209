import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

//------------------------------------------------------------------
//File:   AppGUI.java
//Desc:   This file is the intermediary class that is called,   
//        when launching the program to its default screen.
//------------------------------------------------------------------ 

public class AppGUI extends Application {

    
    //  --------------- //
    //    Variables     //
    // ---------------  //


    private static Stage currentStage;
    private static Stage popupStage = new Stage();


    //  --------------- //
    //     Methods      //
    // ---------------  //


    @Override
    public void start(Stage stage) throws Exception {
        AppGUI.currentStage = stage;
        AppGUI.windowLoad("Title Screen", getClass().getResource("W_TitleScreen.fxml"), null);
        currentStage.show();
    }


    public static void windowLoad(String newWindowTitle, URL windowURL, Object windowInitData) 
        throws IOException, InterruptedException {

        var loader = new FXMLLoader(windowURL);
        var scene = new Scene(loader.load());

        currentStage.setScene(scene);
        currentStage.setTitle(newWindowTitle);
        // currentStage.setWidth(1340);
        // currentStage.setHeight(600);
        // currentStage.setFullScreen(true);
        // currentStage.setMaximized(true);currentStage

            // Get current screen of the stage      
// ObservableList<Screen> screens = Screen.getScreensForRectangle(new Rectangle2D(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()));

// // Change stage properties
// Rectangle2D bounds = screens.get(0).getVisualBounds();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        currentStage.setX(bounds.getMinX());
        currentStage.setY(bounds.getMinY());
        currentStage.setWidth(bounds.getWidth());
        currentStage.setHeight(bounds.getHeight());

        // currentStage.setFullScreenExitHint("");
        currentStage.getIcons().add(new Image("/icons/terminatorIcon2.png"));

        if(newWindowTitle.equals("Game")){           
            W_InGame game = loader.getController();
            game.ingameScene = scene;

            scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
                if(key.isShiftDown()) {
                    //((instance.isCheatMode() == true) ? instance.setCheatMode(false) : instance.);
                }
             });

            game.initialize((int)windowInitData);
        }

        // currentStage.show();
    }


    //  --------------- //
    //   Get-Setters    //
    // ---------------  //


    public static void setStage(Stage newStage) {
        currentStage = newStage;
    }

    public static Stage getStage() {
        return currentStage;
    }

    public static Stage getPopupStage() {
        return popupStage;
    }

    public static void setPopupStage(Stage popupStage) {
        AppGUI.popupStage = popupStage;
    }

}
