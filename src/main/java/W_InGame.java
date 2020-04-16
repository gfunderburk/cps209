import java.io.File;
import java.io.IOException;

import Game_model.Entity;
import Game_model.Game;
import Game_model.Game.StateGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Point3D;
import javafx.util.Duration;

//------------------------------------------------------------------
//File:   Window_InGame.java
//Desc:   This class is for active in-game gameplay.
//------------------------------------------------------------------ 

public class W_InGame {

    // --------------- //
    // Media Elements //
    // --------------- //

    // final AudioClip AUDIO_RESTART = new
    // AudioClip(getClass().getResource("/media/_filename_.wav").toString());

    // --------------- //
    // View Variables //
    // --------------- //

    Stage oldStage = AppGUI.getStage();
    Stage newStage = new Stage();
    Scene ingameScene;
    int difficulty;
    boolean mouseWithinPane;

    // ------------- //
    // GUI Elements //
    // ------------- //

    @FXML
    VBox vbox_masterParent;
    @FXML
    Button btn_esc;
    @FXML
    Pane pane;

    @FXML
    Label lbl_Id;
    @FXML
    Label lbl_Loc;
    @FXML
    Label lbl_Speed;

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    /**
     * Action: closes GameWindow and ends the game instance.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void onEscClicked() throws IOException, InterruptedException {
        AppGUI.windowLoad(oldStage, newStage, "Esc Menu", getClass().getResource("W_EscMenu.fxml"), false, null);
    }

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //


    @FXML
    void initialize(int difficultyLevel) throws InterruptedException {
        this.difficulty = difficultyLevel;
        System.out.println(this.difficulty);
        
        Game.getIt().startGame("Joe", difficultyLevel, 1);
        var keyFrame = new KeyFrame(Duration.seconds(.2), e -> timerAnimate());
        var clockTimeline = new Timeline(keyFrame);
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
    }


    void timerAnimate() {
        if (Game.getIt().getStateGame() == StateGame.RUNNING) { 

            // move entities physically in Model
            for (Entity entity : Game.getIt().getEntityList()) {
                entity.move();
            }
            Game.getIt().sortEntityList();  // sort so that entities are properly visually layered according to z depth

            // draw entities visually in View
            for (Entity entity : Game.getIt().getEntityList()) {
                drawEntity(entity);
            }
        }
    }


    public void drawEntity(Entity entity){
        lbl_Loc.setText("( X= " + (int)entity.getLocation().getX() + ", Y= " + (int)entity.getLocation().getY() + ", Z= " + (int)entity.getLocation().getZ() + ")");;
        lbl_Id.setText("" + entity.getId());
        lbl_Speed.setText("" + entity.getSpeed());
    

        //  Delete old entity image if it exists
        ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + entity.getId());
        if(oldEntityImg != null){
            pane.getChildren().remove(oldEntityImg);
        }            

        //  Create and/or Redraw entity image 
        String imageAddress = File.separator+"icons"+entity.getImage();
        ImageView newEntityImg = new ImageView(imageAddress);

        // newEntityImg.setPreserveRatio(true);
        newEntityImg.setId("" + entity.getId());
        newEntityImg.setUserData(entity.getId());
        newEntityImg.getStyleClass().add("gameEntity");
        
        pane.getChildren().add(newEntityImg);
        
        Point3D loc = entity.getLocation();

        double paneW = pane.getWidth();
        double paneH = pane.getHeight();

        double paneWper = paneW * .01;
        double paneHper = paneH * .01;

        //  Set ImageView Width/Height

        double imgW = (paneWper * entity.getWidth()) - (loc.getZ() * 5);   // set width according to z depth (deeper z = narrower)
        newEntityImg.setFitWidth(imgW);

        double imgH = (paneHper * entity.getHeight()) - (loc.getZ() * 15);   // set height according to z depth (deeper z = shorter)
        newEntityImg.setFitHeight(imgH);

        //  Set ImageView (x,y)

        double imgX = ( loc.getX() * paneW ) / Game.getIt().getGamePhysicsWidth();   // set x according to physical and visual world ratio     
        double imgY = ( loc.getY() * paneH ) / Game.getIt().getGamePhysicsHeight();   // set y according to physical and visual world ratio

        imgX = imgX - (0.5 * imgW);  //  center width on item pt.
        imgY = imgY + (1.0 * imgH) + (loc.getZ() * 20); //  adjust y according to depth (deeper z = higher)

        newEntityImg.relocate(imgX, paneH - imgY); 
    }
}
