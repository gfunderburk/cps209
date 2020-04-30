/* --------------------------------------------------------------------------------------------- 
File:   Window_InGame.java
Desc.   This class corresponds with InGame.fxml and contains all GUI-related methods for game play.
        Ingame window displays the in-game state.
Author(s): Jonathan Layton (Game play/animations), Jeremiah Cox (GUI methods) 
--------------------------------------------------------------------------------------------- */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import Game_model.GameSounds;
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
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Point3D;
import javafx.util.Duration;


public class W_InGame {

    // -------------------- //
    //  Singleton Variables //
    // -------------------- //

    Game game = Game.getIt();  // Game singleton
    EH_Avatar avatar = EH_Avatar.getIt(); // Avatar singleton (human player)
    GameSounds sounds = GameSounds.it();  // Game Sounds singleton


    // --------------- //
    // Media Elements //
    // --------------- //
    
  
    final Image CROSSHAIRS = new Image("/icons/crosshairs_4.png"); // Crosshairs image for mouse


    // --------------- //
    // View Variables //
    // --------------- //


    Scene ingameScene;       // Contains in-game scene. 
    StateDifficulty difficulty;  // Contains difficulty level state.
    double mouseX, mouseY, paneW, paneH;  // Mouse position and pane dimension variables.
    boolean readyForNextFrame = true;
    boolean playFanFare;    // Whether or not the end-of-level fanfare should play.      
    final double animationRate_sec = 0.05; //Animation rate

    
    // ------------- //
    // GUI Elements //
    // ------------- //

    @FXML
    VBox vbox_masterParent; //master vbox
    @FXML
    VBox vbox_health; // Health bar vbox
    @FXML
    HBox hud_hbox; // HUD vbox
    @FXML
    Button btn_esc; //escape button
    @FXML
    Button btn_toggleCheatmode; // Cheat mode toggle button
    @FXML
    Button btn_reload; //Reload button
    @FXML
    Pane pane; // Game screen pane
    @FXML
    Label lbl_ammoStats; // Ammo stats label
    @FXML
    Label lbl_Score; // Score label
    //@FXML
    //ProgressBar progBar_health; // Progress bar for health


    // ------------ //
    //  GUI Methods // (DIRECT USER EVENTS)
    // ------------ //


    /**
     * Action: launches popup screen with esc menu.
     * 
     * @throws IOException
     */

    @FXML
    void onEscClicked() throws IOException {
        sounds.BTN_CLICK.play();
        AppGUI.popupLoad(this, "W_EscMenu.fxml", "ESC Menu");
    }
    
    /**
     * Reloads gun and updates ammo count on HUD
     */
    @FXML
    void onReloadClicked() throws IOException {
        avatar.reload();
        GameSounds.it().Avatar_reloading.play();
        updateHealthGUI();
    }
    
    /**
     * Action: toggles cheat mode
     */
    @FXML
    void ontoggleCheatmodeClicked() throws FileNotFoundException {
        sounds.BTN_CLICK.play();
        game.toggleCheatMode();
        btn_toggleCheatmode.setText(game.isCheatMode() ? "Cheatmode - ON": "Cheatmode - OFF");
    }


    // Sets mouse image as soon as it enters the pane.
    @FXML
    void mouseEnteredPane(){
        pane.getScene().setCursor(new ImageCursor(CROSSHAIRS,
        CROSSHAIRS.getWidth() /2,
        CROSSHAIRS.getHeight() /2));
    }

    /**
     * Changes mouse back to default when it exits the game pane.
     */
    @FXML
    void mouseExitedPane(){
        pane.getScene().setCursor(Cursor.DEFAULT);
    }


    /**
     * Fires gun when mouse is clicked while inside the pane if there is ammo in the mag.
     * Updates HUD after firing.
     * @param event
     * @throws IOException
     */
    @FXML
    void mouseClickedPane(MouseEvent event) throws IOException {
        updateHealthGUI();
        if (avatar.getMag() >= 1) 
        {
            avatar.setMag(avatar.getMag() - 1);
            avatar.attack(event.getX(), pane.getHeight()-event.getY(), pane.getWidth(), pane.getHeight());

            if(game.isCheatMode()){
                sounds.Avatar_attackingCheatmode.play();
                
            }
            else{
                sounds.Avatar_attacking.play();
            }
        } 
        else 
        {
            avatar.setMag(0);
            sounds.Avatar_attackingEmptyMag.play();
        }
        updateHealthGUI();
    }


    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //


    /**
     * Central update method that can be called to update the HUD.
     */
    void updateHealthGUI() throws IOException {

        // Update Health Bar

        double health = EH_Avatar.getIt().getCurrentHealth()/100;
        // System.out.println(health);
        vbox_health.getChildren().clear();
        var progBar_health = new ProgressBar();
        progBar_health.setProgress(health);

        if (health < .5) 
        {
            progBar_health.setStyle("-fx-accent: red;");

            if(EH_Avatar.getIt().isGotHurt())
            {
                EH_Avatar.getIt().setGotHurt(false);
                GameSounds.it().Avatar_hurt.play();
            }
        } else 
        {
            progBar_health.setStyle("-fx-accent: lime;");
        }
        vbox_health.getChildren().addAll(new Label("Health:"), progBar_health);

        
        // Update Score and Ammo stats

        hud_hbox.getChildren().removeAll(lbl_Score, lbl_ammoStats);
        lbl_Score.setText("Score: " + game.getScore());
        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + "/" + avatar.getAmmo());
        hud_hbox.getChildren().addAll(lbl_Score, lbl_ammoStats);

        // Check player health
        if (health == 0 || health < 0) {
            game.setGameOver(true);
        }

        // check for GameOver

        if (game.isGameOver()) 
        {
            if (game.isPlayerWinner() && game.getGameLvl() >= 3) 
            {
                AppGUI.popupLoad(this, "W_AllLevelsCompleted.fxml", "YOU WON!!");
                if(playFanFare){
                    GameSounds.it().Avatar_wins.play();
                    playFanFare = false;
                }
            }
            else if(game.isPlayerWinner())
            {
                AppGUI.popupLoad(this, "W_LevelOver.fxml", "Level OVER"); 
                if(playFanFare){
                    GameSounds.it().Avatar_wins.play();
                    playFanFare = false;
                }
            }
            else
            {
                GameSounds.it().Avatar_loses.play();
                AppGUI.popupLoad(this, "W_GameOver.fxml", "GAME OVER");
            }
        } 
    }


    /**
     * This method is called when the game is initialized.
     * @param difficultyLevel
     * @param gameLevel
     * @param score
     * @throws InterruptedException
     * @throws IOException
     */
    @FXML
    void initialize(StateDifficulty difficultyLevel, int gameLevel, int score) throws InterruptedException, IOException  {
        
        //  Reset in-game Pane

        sounds.THEME.stop();
        playFanFare = true;
        pane.getChildren().clear();
        game.setEntityList(new ArrayList<Entity>());    
        game.setDeadEntityList(new ArrayList<Entity>());
        EH_Avatar.resetAvatarSingleton();
        Game.resetGameSingleton();
        game = Game.getIt();
        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + " / " + avatar.getAmmo());
        this.difficulty = difficultyLevel;
        game.setScore(score);
        game.startGame(difficultyLevel, gameLevel);
        

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



    /**
     * This method is used for all in-game animations.
     */
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

            game.sortEntityList();  // sort, so that entities are properly visually layered according to z depth

            // draw entities visually in View

            pane.getChildren().clear();
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

    /**
     * This method is used to draw all in-game entities on the screen.
     */
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
