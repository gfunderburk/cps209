import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Game_model.EH_Avatar;
import Game_model.E_Projectile;
import Game_model.Entity;
import Game_model.Game;
import Game_model.Game.StateGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.geometry.Point3D;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

//------------------------------------------------------------------
//File:   Window_InGame.java
//Desc:   This class is for active in-game gameplay.
//------------------------------------------------------------------ 

public class W_InGame {

    // Singleton Instance Variables
    Game game = Game.getIt();
    EH_Avatar avatar = EH_Avatar.getIt();


    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());
    final AudioClip SHOOT_FOOTSOLDIER = new AudioClip(getClass().getResource("/media/footsoldiergun.wav").toString());
    final AudioClip SHOOT_50CAL = new AudioClip(getClass().getResource("/media/50cal.mp3").toString());
    final Image CROSSHAIRS = new Image("/icons/crosshairs_3.PNG");

    // --------------- //
    // View Variables //
    // --------------- //

    Scene ingameScene;
    int difficulty;
    boolean mouseWithinPane;
    double mouseX, mouseY, paneW, paneH;

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
    Label lbl_ammoStats;

    @FXML
    Label lbl_Score;
    

    // ------------ //
    //  GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    /**
     * Action: closes GameWindow and ends the game instance.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void onEscClicked() throws IOException, InterruptedException {
        
        BTN_CLICK.play();
        game.pause();
        
        // Load ESC_Menu
        var loader = new FXMLLoader(getClass().getResource("W_EscMenu.fxml"));
        var scene = new Scene(loader.load());        
        AppGUI.getPopupStage().setScene(scene);
        AppGUI.getPopupStage().setTitle("ESC Menu");
        AppGUI.getPopupStage().setWidth(400);
        AppGUI.getPopupStage().setHeight(300);
        AppGUI.getPopupStage().show();
    }


    @FXML
    void mouseEnteredPane(){
        mouseWithinPane = true;
        pane.getScene().setCursor(new ImageCursor(CROSSHAIRS,
        CROSSHAIRS.getWidth() /2,
        CROSSHAIRS.getHeight() /2));
    }


    @FXML
    void mouseExitedPane(){
        mouseWithinPane = false;
        pane.getScene().setCursor(Cursor.DEFAULT);
    }
    

    @FXML
    void onMouseMoved(MouseEvent event) {
        // if(mouseWithinPane){
        //     mouseX = event.getX();
        //     mouseY = event.getY();
        // }
    }


    @FXML
    void mouseClickedPane(MouseEvent event) {
        SHOOT_FOOTSOLDIER.play();
        avatar.attack(event.getX(), pane.getHeight()-event.getY(), pane.getWidth(), pane.getHeight());
        if (avatar.getMag() > 1) {
            avatar.setMag(avatar.getMag() - 1);
        } else {
            avatar.setMag(0);
        }
        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + "/" + avatar.getAmmo());
        lbl_Score.setText("Score: " + game.getScore()); 
    }
    

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //


    @FXML
    void initialize(int difficultyLevel) throws InterruptedException {
        //TODO: get ammo and mag variables from EntityKillable

        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + "/" + avatar.getAmmo());
        this.difficulty = difficultyLevel;
        System.out.println(this.difficulty);
        // pane.setOnMouseEntered(me -> pane.getScene().setCursor(Cursor.HAND) );
        // pane.setOnMouseExited(me -> pane.getScene().setCursor(Cursor.DEFAULT) );
        
        Game.getIt().startGame("Joe", difficultyLevel, 1);
        
        String imageAddress = File.separator+"icons"+File.separator+"backgrounds"+File.separator+"lvl"+Game.getIt().getGameLvl()+"Background.png";
        Image lvlImage = new Image(imageAddress);
        
        BackgroundImage background = new BackgroundImage(lvlImage,
                                                        BackgroundRepeat.NO_REPEAT,
                                                        BackgroundRepeat.NO_REPEAT,
                                                        BackgroundPosition.DEFAULT,
                                                        BackgroundSize.DEFAULT);
        pane.setBackground(new Background(background));
        
        
        //  Set Global Animation Timer
        var keyFrame = new KeyFrame(Duration.seconds(.05), e -> timerAnimate());
        var clockTimeline = new Timeline(keyFrame);
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
        
    }


    void timerAnimate() {
        if (Game.getIt().getStateGame() == StateGame.RUNNING) { 

            // move entities physically in Model
            for (int i = 0; i < Game.getIt().getEntityList().size(); i++) {
                Game.getIt().getEntityList().get(i).stateIncrement();
            }
            Game.getIt().sortEntityList();  // sort so that entities are properly visually layered according to z depth

            // draw entities visually in View
            for (Entity entity : Game.getIt().getEntityList()) {
                drawEntity(entity);
            }        

            //  Delete any dead entity images
            for (int i = 0; i < Game.getIt().getDeadEntityList().size(); i++) {
                ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + Game.getIt().getDeadEntityList().get(i).getId());
                if(oldEntityImg != null){
                    pane.getChildren().remove(oldEntityImg);
                }     
            }
            Game.getIt().setDeadEntityList(new ArrayList<Entity>());
        }
    }


    public void drawEntity(Entity entity){    

        //  Delete old entity image if it exists
        ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + entity.getId());
        if(oldEntityImg != null){
            pane.getChildren().remove(oldEntityImg);
        }            

        //  Create and/or Redraw entity image 
        String imageAddress = File.separator+"icons"+entity.getImage();
        ImageView newEntityImg = new ImageView(imageAddress);

        newEntityImg.setId("" + entity.getId());
        newEntityImg.setUserData(entity.getId());
        newEntityImg.getStyleClass().add("gameEntity");
        // newEntityImg.setOnMouseClicked(me -> {
        //     EH_Avatar.getIt().attack(Game.getIt().findEntityById(Integer.parseInt(newEntityImg.getId())));
        // });
        pane.getChildren().add(newEntityImg);
        
        //  Variables

        Point3D loc = entity.getLocation();
        paneW = pane.getWidth();
        paneH = pane.getHeight();
        double paneWper = paneW * .01;
        double paneHper = paneH * .01;

        //  Set ImageView Width/Height

        double imgW = paneWper * entity.getWidth(); // set z=0 Width
        double imgH = paneHper * entity.getHeight(); // set z=0 Height

        imgW -= (0.05 * loc.getZ() * imgW);   // set width according to z depth (deeper z = narrower)
        imgH -= (0.05 * loc.getZ() * imgH);   // set height according to z depth (deeper z = shorter)
    
        newEntityImg.setFitWidth(imgW);
        newEntityImg.setFitHeight(imgH);
        
        //  Set ImageView (x,y)

        double imgX = ( loc.getX() * paneW ) / Game.getIt().getGamePhysicsWidth();   // set x according to physical and visual world ratio     
        double imgY = ( loc.getY() * paneH ) / Game.getIt().getGamePhysicsHeight();   // set y according to physical and visual world ratio

        imgX -= (0.5 * imgW);  //  center width on item pt.

        if(entity instanceof E_Projectile){
            E_Projectile ent = (E_Projectile)entity;

            imgY += (0.5 * imgH); // center img on Y-axis
            if(! ent.isAvatarsProjectile()){
                double XvisualOffsetRaw = (paneWper * ent.getVisualXoffset()); // offset X for entity type @ z=0
                double YvisualOffsetRaw = (paneHper * ent.getVisualYoffset()); // offset Y for entity type @ z=0
                double XvisualOffsetDepthed = (0.05 * loc.getZ() * XvisualOffsetRaw);
                double YvisualOffsetDepthed = (0.05 * loc.getZ() * YvisualOffsetRaw);
                imgX += (XvisualOffsetRaw - XvisualOffsetDepthed);
                imgY += (YvisualOffsetRaw - YvisualOffsetDepthed);
                imgY += (loc.getZ() * 20); // adjust y according to depth (deeper z = higher)
            }
        }
        else{
            imgY += (1.0 * imgH); //  center img's bottom edge on entity's center-point
            imgY += (loc.getZ() * 20); // adjust y according to depth (deeper z = higher)
        }

        newEntityImg.relocate(imgX, paneH - imgY); 
    }
}
