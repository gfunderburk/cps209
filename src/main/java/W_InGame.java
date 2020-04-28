/* --------------------------------------------------------------------------------------------- 
File:   Window_InGame.java
Desc.   Ingame window displays the in-game state.
--------------------------------------------------------------------------------------------- */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import Game_model.EH_Avatar;
import Game_model.EK_Scenery;
import Game_model.E_Projectile;
import Game_model.Entity;
import Game_model.EntityHumanoid;
import Game_model.Game;
import Game_model.Game.StateDifficulty;
import Game_model.Game.StateGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.geometry.Point3D;
import javafx.util.Duration;


public class W_InGame implements EventHandler<KeyEvent> {

    // Singleton Instance Variables
    Game game = Game.getIt();
    EH_Avatar avatar = EH_Avatar.getIt();

    // --------------- //
    // Media Elements //
    // --------------- //
    
  
    final Image CROSSHAIRS            = new Image("/icons/crosshairs_4.png");

    // --------------- //
    // View Variables //
    // --------------- //

    Scene ingameScene;
    Scene scene;
    StateDifficulty difficulty;
    double mouseX, mouseY, paneW, paneH;
    boolean readyForNextFrame = true;
    final double animationRate_sec = 0.05;

    // ------------- //
    // GUI Elements //
    // ------------- //

    @FXML
    VBox vbox_masterParent;
    @FXML
    VBox vbox_health;
    @FXML
    HBox hud_hbox;
    @FXML
    Button btn_esc;
    @FXML
    Button btn_toggleCheatmode;
    @FXML
    Button btn_reload;
    @FXML
    Pane pane;
    @FXML
    Label lbl_ammoStats;
    @FXML
    Label lbl_Score;
    @FXML
    ProgressBar progBar_health;

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    /**
     * Action: closes GameWindow and ends the game instance.
     * 
     * @throws IOException
     */

    @FXML
    void onEscClicked() throws IOException {
        AppGUI.popupLoad(this, "W_EscMenu.fxml", "ESC Menu");
    }
    
    @FXML
    void onReloadClicked() throws IOException {
        avatar.reload();
        updateHealthGUI();
    }
    
    @FXML
    void ontoggleCheatmodeClicked() throws FileNotFoundException {
        game.toggleCheatMode();
        btn_toggleCheatmode.setText(game.isCheatMode() ? "Cheatmode - ON": "Cheatmode - OFF");
    }


    @FXML
    void mouseEnteredPane(){
        pane.getScene().setCursor(new ImageCursor(CROSSHAIRS,
        CROSSHAIRS.getWidth() /2,
        CROSSHAIRS.getHeight() /2));
    }


    @FXML
    void mouseExitedPane(){
        pane.getScene().setCursor(Cursor.DEFAULT);
    }


    @FXML
    void mouseClickedPane(MouseEvent event) throws IOException {

        avatar.attack(event.getX(), pane.getHeight()-event.getY(), pane.getWidth(), pane.getHeight());
        if (avatar.getMag() > 1 || avatar.getMag() == 1) {
            // SHOOT_FOOTSOLDIER.play();
            avatar.setMag(avatar.getMag() - 1);
        } else {
            // BTN_CLICK.play();
            avatar.setMag(0);
        }
        updateHealthGUI();
    }

    // //Written by Funderburk, pushed by Cox
    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCharacter());
       if(event.getCharacter()=="R"){
        System.out.print("RELOAD");
        EH_Avatar.getIt().reload();
        // BTN_CLICK.play();
       }
       if(event.getCharacter()=="C"){
           System.out.print("CHEAT");
           Game.getIt().toggleCheatMode();
        //    BTN_CLICK.play();
       }
    }
    

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //

    void updateHealthGUI() throws IOException {

        // Update Health Bar

        double health = avatar.getCurrentHealth()/10;
        System.out.println("Health: " + health);
        vbox_health.getChildren().clear();
        progBar_health = new ProgressBar();
        progBar_health.setProgress(health);
        if (health < .5) {
            progBar_health.setStyle("-fx-accent: red;");
        } else {
            progBar_health.setStyle("-fx-accent: lime;");
        }
        vbox_health.getChildren().addAll(new Label("Health:"), progBar_health);

        
        // Update Score and Ammo stats

        hud_hbox.getChildren().removeAll(lbl_Score, lbl_ammoStats);
        lbl_Score.setText("Score: " + game.getScore());
        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + "/" + avatar.getAmmo());
        hud_hbox.getChildren().addAll(lbl_Score, lbl_ammoStats);


        // check for GameOver

        if (Game.getIt().isGameOver()) 
        {
            if (Game.getIt().isPlayerWinner() && game.getGameLvl() >= 3) 
            {
                AppGUI.popupLoad(this, "W_AllLevelsCompleted.fxml", "YOU WON!!");
            }
            else if(Game.getIt().isPlayerWinner())
            {
                AppGUI.popupLoad(this, "W_LevelOver.fxml", "Level OVER"); 
            }
            else
            {
                AppGUI.popupLoad(this, "W_GameOver.fxml", "GAME OVER");
            }
        } 
    }


    @FXML
    void initialize(StateDifficulty difficultyLevel, int gameLevel, int score) throws InterruptedException, IOException  {
        
        //  Reset in-game Pane

        AppSounds.it().THEME.stop();
        pane.getChildren().clear();
        game.setEntityList(new ArrayList<Entity>());    
        game.setDeadEntityList(new ArrayList<Entity>());
        EH_Avatar.resetAvatarSingleton();
        Game.resetGameSingleton();
        game = Game.getIt();
        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + " / " + avatar.getAmmo());
        this.difficulty = difficultyLevel;
        game.setScore(score);
        game.startGame("Joe", difficultyLevel, gameLevel);
        

        //  Setup lvl background

        String imageAddress = File.separator+"icons"+File.separator+"backgrounds"+File.separator+"lvl"+game.getGameLvl()+"Background.png";
        Image lvlImage = new Image(imageAddress);
        
        BackgroundImage background = new BackgroundImage(lvlImage,
                                                        BackgroundRepeat.NO_REPEAT,
                                                        BackgroundRepeat.NO_REPEAT,
                                                        BackgroundPosition.DEFAULT,
                                                        BackgroundSize.DEFAULT);
        pane.setBackground(new Background(background));
        
        
        //  Set Global Animation Timer

        var keyFrame = new KeyFrame(Duration.seconds(animationRate_sec), e -> 
        {
            if(readyForNextFrame)
            try {timerAnimate();} 
            catch (IOException e1){e1.printStackTrace();}
        });
        var clockTimeline = new Timeline(keyFrame);
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();        
    }



    void timerAnimate() throws IOException {
        if (game.getStateGame() == StateGame.RUNNING) { 

            readyForNextFrame = false;

            // move entities physically in Model
            for (int i = 0; i < Game.getIt().getEntityList().size(); i++) {
                Entity ent = Game.getIt().getEntityList().get(i);

                if(! (ent instanceof EK_Scenery) ) ent.stateIncrement();

                if(ent instanceof EntityHumanoid){
                    EntityHumanoid dude = (EntityHumanoid)ent;    
                    if(dude.isAttacking()) dude.getAudio_attacking().play();
                    if(dude.isDying())     dude.getAudio_dying().play();
                }
            }

            // parking place breakpoint for debugging
            var pi = Game.getIt();
            if(pi.getGameLvl() == 2) {
                var testStopForDebugger = 0;}

            game.sortEntityList();  // sort so that entities are properly visually layered according to z depth

            pane.getChildren().clear();

            // draw entities visually in View
            for (Entity entity : game.getEntityList()) {
                drawEntity(entity);
            }        

            // //  Delete any dead entity images
            for (int i = 0; i < game.getDeadEntityList().size(); i++) {
                ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + game.getDeadEntityList().get(i).getId());
                if(oldEntityImg != null){
                    pane.getChildren().remove(oldEntityImg);
                }     
            }
            game.setDeadEntityList(new ArrayList<Entity>());
            updateHealthGUI();
            
            readyForNextFrame = true;
        }
    }

    public void drawEntity(Entity entity) throws IOException {

        // Find if given entity image exists
        ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + entity.getId());
        ImageView newEntityImg = null;

        if(oldEntityImg != null){  
            oldEntityImg.setImage(entity.getImageState());          
            newEntityImg = oldEntityImg;
        }
        else{
            newEntityImg = new ImageView(entity.getImage());
            // newEntityImg.setCache(false);
            newEntityImg.setId("" + entity.getId());
            newEntityImg.setUserData(entity.getId());
            newEntityImg.getStyleClass().add("gameEntity");

            pane.getChildren().add(newEntityImg);
        }


        //  Re-Calculate Image Specs
    
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
    
        
        //  Set ImageView (x,y)

        double imgX = ( loc.getX() * paneW ) / game.getGamePhysicsWidth();   // set x according to physical and visual world ratio     
        double imgY = ( loc.getY() * paneH ) / game.getGamePhysicsHeight();   // set y according to physical and visual world ratio

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
                // SHOOT_SHOTGUN.play();
            }
        }
        else{
            imgY += (1.0 * imgH); //  center img's bottom edge on entity's center-point
            imgY += (loc.getZ() * 20); // adjust y according to depth (deeper z = higher)
        }


        newEntityImg.setFitWidth(imgW);
        newEntityImg.setFitHeight(imgH);
        newEntityImg.relocate(imgX, paneH - imgY);
        updateHealthGUI();
    }
}
