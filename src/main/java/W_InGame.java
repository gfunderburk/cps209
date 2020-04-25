import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import Game_model.EH_Avatar;
import Game_model.E_Projectile;
import Game_model.Entity;
import Game_model.Game;
import Game_model.Game.StateGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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


public class W_InGame implements EventHandler<KeyEvent>{


    // Singleton Instance Variables
    Game game = Game.getIt();
    
    EH_Avatar avatar = EH_Avatar.getIt();
    static Object lock = new Object();


    // --------------- //
    // Media Elements //
    // --------------- //

    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());
    final AudioClip SHOOT_FOOTSOLDIER = new AudioClip(getClass().getResource("/media/footsoldiergun.wav").toString());
    final AudioClip SHOOT_50CAL = new AudioClip(getClass().getResource("/media/50cal.mp3").toString());
    final AudioClip SHOOT_M16 = new AudioClip(getClass().getResource("/media/m16.mp3").toString());
    final AudioClip SHOOT_SHOTGUN = new AudioClip(getClass().getResource("/media/shotgun.mp3").toString());
    final Image CROSSHAIRS = new Image("/icons/crosshairs_3.PNG");

    // --------------- //
    // View Variables //
    // --------------- //

    Scene ingameScene;
    Scene scene;
    String difficulty;
    boolean mouseWithinPane;
    double mouseX, mouseY, paneW, paneH;
    boolean cheatMode = game.isCheatMode();

    // ------------- //
    // GUI Elements //
    // ------------- //

    @FXML
    VBox vbox_masterParent;
    @FXML
    VBox vbox_health;

    @FXML
    Button btn_esc;

    @FXML
    Pane pane;

    @FXML
    Label lbl_ammoStats;

    @FXML
    Label lbl_Score;

    @FXML
    ProgressBar progBar_health;
    

    // ------------ //
    //  GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    /**
     * Action: closes GameWindow and ends the game instance.
     * 
     * @throws IOException
     * 
     * @ @throws InterruptedException
     */

    @FXML
    void onEscClicked() throws IOException {
        // Load ESC_Menu
        var loader = new FXMLLoader(getClass().getResource("W_EscMenu.fxml"));
        var scene = new Scene(loader.load());  
        scene.setOnKeyPressed((KeyEvent event) -> {if(event.getCode()==KeyCode.R){System.out.print("Reload");}});   
        AppGUI.getPopupStage().setScene(scene);
        AppGUI.getPopupStage().setTitle("ESC Menu");
        AppGUI.getPopupStage().setWidth(400);
        AppGUI.getPopupStage().setHeight(300);
        AppGUI.getPopupStage().show();

        BTN_CLICK.play();
        AppGUI.popupLoad(getClass().getResource("W_EscMenu.fxml"), "ESC Menu");

    }


    @FXML
    void mouseEnteredPane(){
        mouseWithinPane = true;
        ImageCursor cursor = new ImageCursor(CROSSHAIRS, 40, 40);
        pane.getScene().setCursor(cursor);
        // pane.getScene().setCursor(new ImageCursor(CROSSHAIRS,
        // CROSSHAIRS.getWidth() /2,
        // CROSSHAIRS.getHeight() /2));
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

        avatar.attack(event.getX(), pane.getHeight()-event.getY(), pane.getWidth(), pane.getHeight());
        if (avatar.getMag() > 1) {
            SHOOT_M16.play();
            avatar.setMag(avatar.getMag() - 1);
        } else {
            BTN_CLICK.play();
            avatar.setMag(0);
        }
        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + "/" + avatar.getAmmo());
        lbl_Score.setText("Score: " + game.getScore()); 
        game.setPlayerWin(true);
        game.setGameOver(true);
    }

    // //Written by Funderburk, pushed by Cox
    // @Override
    // public void handle(KeyEvent event) {
    //     System.out.println(event.getCharacter());
    //    if(event.getCharacter()=="R"){
    //     System.out.print("RELOAD");
    //     avatar.getIt().reload();
    //    }
    //    if(event.getCharacter()=="C"){
    //        System.out.print("CHEAT");
    //        game.getIt().toggleCheatMode(cheatMode);
    //    }
    // }
    

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //

    void updateHealthGUI() throws IOException {
        double health = avatar.getCurrentHealth();
        vbox_health.getChildren().clear();
        progBar_health = new ProgressBar();
        progBar_health.setProgress(health);
        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + "/" + avatar.getAmmo());
        lbl_Score.setText("Score: " + game.getScore()); 
        vbox_health.getChildren().addAll(new Label("Health:"), progBar_health);

        if (game.isGameOver()) {
            // Game.getIt().closeGame();

            if(game.isPlayerWinner()){
                // Load-Launch "YOU HAVE COMPLETED THE LEVEL!" LevelOver menu
                AppGUI.popupLoad(getClass().getResource("W_LevelOver.fxml"), "Level OVER");
            }
            else{
                // Load-Launch "YOU HAVE DIED!" GameOver menu
                AppGUI.popupLoad(getClass().getResource("W_GameOver.fxml"), "GAME OVER");
            }
        } 
    }

    //Used for debugging of healthBar functionality...will remove before submission
    // void minusHealthBar() {
    //     health -= 0.01;
    //     vbox_health.getChildren().clear();
    //     progBar_health = new ProgressBar();
    //     progBar_health.setProgress(health);
    //     vbox_health.getChildren().addAll(new Label("Health:"), progBar_health);
    //     // plusHealthBar();
    // }

    // void plusHealthBar() {
    //     KeyFrame timer = new KeyFrame(Duration.seconds(1), e -> {
    //         health += .01;
    //         updateHealth();
    //     });
    //     var timeline = new Timeline(timer);
    //     timeline.play();
    // }

    @FXML
    void initialize(String difficultyLevel, int gameLevel) throws InterruptedException, IOException  {
        
        resetPane();
        lbl_ammoStats.setText("Ammo: " + avatar.getMag() + " / " + avatar.getAmmo());
        this.difficulty = difficultyLevel;
        System.out.println(this.difficulty);
        
        Game.getIt().startGame("Joe", difficultyLevel, gameLevel);
        
        String imageAddress = File.separator+"icons"+File.separator+"backgrounds"+File.separator+"lvl"+Game.getIt().getGameLvl()+"Background.png";
        Image lvlImage = new Image(imageAddress);
        
        BackgroundImage background = new BackgroundImage(lvlImage,
                                                        BackgroundRepeat.NO_REPEAT,
                                                        BackgroundRepeat.NO_REPEAT,
                                                        BackgroundPosition.DEFAULT,
                                                        BackgroundSize.DEFAULT);
        pane.setBackground(new Background(background));
        
        
        //  Set Global Animation Timer
        var keyFrame = new KeyFrame(Duration.seconds(.05), e -> 
        {
            try {timerAnimate();} 
            catch (IOException e1){e1.printStackTrace();}
        });
        var clockTimeline = new Timeline(keyFrame);
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
        
    }



    void timerAnimate() throws IOException {
        if (Game.getIt().getStateGame() == StateGame.RUNNING) { 

            // move entities physically in Model
            for (int i = 0; i < Game.getIt().getEntityList().size(); i++) {
                Game.getIt().getEntityList().get(i).stateIncrement();
            }
            Game.getIt().sortEntityList();  // sort so that entities are properly visually layered according to z depth

            // for(ImageView item: (ImageView[])pane.getChildren().stream().filter(me -> me instanceof ImageView).toArray()){
            //     item.unbind();
                
            // }

            pane.getChildren().clear();

            // draw entities visually in View
            for (Entity entity : Game.getIt().getEntityList()) {
                drawEntity(entity);
            }        

            // //  Delete any dead entity images
            // for (int i = 0; i < Game.getIt().getDeadEntityList().size(); i++) {
            //     ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + Game.getIt().getDeadEntityList().get(i).getId());
            //     if(oldEntityImg != null){
            //         pane.getChildren().remove(oldEntityImg);
            //     }     
            // }
            // Game.getIt().setDeadEntityList(new ArrayList<Entity>());
            updateHealthGUI();
        }
    }

    public void drawEntity(Entity entity) {

        // //  Delete old entity image if it exists
        // ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + entity.getId());
        // if(oldEntityImg != null){
        //     pane.getChildren().remove(oldEntityImg);
        // }
        // else{
        //     System.out.println("imageView not found: " + entity.Serialize());
        //     System.out.println("----- ID#  " + entity.getId());
        // }                

        //  Create and/or Redraw entity image 
       
        ImageView newEntityImg = null;
       try{
        String imageAddress = File.separator+"icons"+entity.getImage();
        newEntityImg = new ImageView(imageAddress);
       }
       catch(Exception e){
        System.out.println(entity.Serialize() + "  -  " + entity.getImage());
        return;
       }
        // final ImageView newEntityImg = newEntityImgIntermediary; 

        newEntityImg.setId("" + entity.getId());
        newEntityImg.setUserData(entity.getId());
        newEntityImg.getStyleClass().add("gameEntity");
        // newEntityImg.setOnMouseClicked(me -> {
        //     EH_Avatar.getIt().attack(Game.getIt().findEntityById(Integer.parseInt(newEntityImg.getId())));
        // });
        pane.getChildren().add(newEntityImg);
        
        
        // Thread thread = new Thread(() -> {
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
                    // SHOOT_FOOTSOLDIER.play();
                }
            }
            else{
                imgY += (1.0 * imgH); //  center img's bottom edge on entity's center-point
                imgY += (loc.getZ() * 20); // adjust y according to depth (deeper z = higher)
            }


            newEntityImg.setFitWidth(imgW);
            newEntityImg.setFitHeight(imgH);
            newEntityImg.relocate(imgX, paneH - imgY);


            // final double fimgX = imgX;
            // final double fimgY = imgY;
            // final double fimgW = imgW;
            // final double fimgH = imgH;
            // Platform.runLater(() -> {
            //     synchronized (lock) {
            //         newEntityImg.setFitWidth(fimgW);
            //         newEntityImg.setFitHeight(fimgH);
            //         newEntityImg.relocate(fimgX, paneH - fimgY);
            //     }
            // });
        // });
        // thread.start();
    }

    void resetPane(){
        
        //  Delete any dead entity images
        for (int i = 0; i < Game.getIt().getEntityList().size(); i++) {
            ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + Game.getIt().getDeadEntityList().get(i).getId());
            if(oldEntityImg != null){
                pane.getChildren().remove(oldEntityImg);
            }     
        }        
        Game.getIt().setEntityList(new ArrayList<Entity>());    
        
        //  Delete any dead entity images
        for (int i = 0; i < Game.getIt().getDeadEntityList().size(); i++) {
            ImageView oldEntityImg = (ImageView) ingameScene.lookup("#" + Game.getIt().getDeadEntityList().get(i).getId());
            if(oldEntityImg != null){
                pane.getChildren().remove(oldEntityImg);
            }     
        }
        Game.getIt().setDeadEntityList(new ArrayList<Entity>());
    }


    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCharacter());
       if( event.getCharacter()=="R"){
        System.out.print("RELOAD");
        avatar.reload();
       }
       if(event.getCharacter()=="C"){
           System.out.print("CHEAT");
           game.toggleCheatMode();
       }

    }
}
