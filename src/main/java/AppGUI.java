/* --------------------------------------------------------------------------------------------- 
File:   AppGUI.java
Desc.   AppGUI is a modified class that is auto-generated by Node.js
        It holds static utility methods for the other FXML.java classes.
--------------------------------------------------------------------------------------------- */


import javafx.scene.media.AudioClip;
import java.io.IOException;
import Game_model.Game;
import Game_model.Game.StateDifficulty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class AppGUI extends Application {

    
    //  --------------- //
    //    Variables     //
    // ---------------  //


    private static Stage currentStage;
    private static Stage popupStage = new Stage();
    private static Game game = Game.getIt();


    //  --------------- //
    //     Methods      //
    // ---------------  //


    @Override
    public void start(Stage stage) throws Exception {
        AppGUI.currentStage = stage;
        AppGUI.windowLoad(this, "Title Screen", "W_TitleScreen.fxml", null);
        currentStage.show();
    }

    public static AudioClip audioClip(Object parentClass, String audioURL){
        return new AudioClip(parentClass.getClass().getResource("/media/"+audioURL).toString());
    }


    public static void popupLoad(Object parentClass, String windowURL, String windowTitle) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(parentClass.getClass().getResource(windowURL));
        Scene scene = new Scene(loader.load());
        AppGUI_popupWin ctrl = (AppGUI_popupWin)loader.getController();
        ctrl.initialize();
        
        AppGUI.getPopupStage().setScene(scene);
        AppGUI.getPopupStage().setTitle(windowTitle);
        // AppGUI.getPopupStage().setWidth(400);
        // AppGUI.getPopupStage().setHeight(300);
        AppGUI.getPopupStage().show();
    }

    public static void windowLoad(Object parentClass, String newWindowTitle, String windowURL, Object[] windowInitData) 
        throws IOException, InterruptedException {
        currentStage.close();
        currentStage = new Stage();

        var loader = new FXMLLoader(parentClass.getClass().getResource(windowURL));
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
        // currentStage.setWidth(bounds.getWidth());
        // currentStage.setHeight(bounds.getHeight());
        currentStage.setWidth(1440);
        currentStage.setHeight(900);

        // currentStage.setFullScreenExitHint("");
        currentStage.getIcons().add(new Image("/icons/terminatorIcon2.png"));

        if(newWindowTitle.equals("Game")){    
                   
            W_InGame gameScene = loader.getController();
            gameScene.ingameScene = scene;

            scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
                if(key.getCode() == KeyCode.C) {
                    System.out.println("cheat mode toggled");
                    if (game.isCheatMode()) {
                        game.setCheatMode(false);
                    } else {
                        game.setCheatMode(true);
                    }
                }
             });

            gameScene.initialize((StateDifficulty)windowInitData[0], (int)windowInitData[1], (int) windowInitData[2]);
        }

        currentStage.show();
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
