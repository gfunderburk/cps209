import java.io.File;
import java.io.IOException;

import Data_model.Score;
import Data_model.ScoreManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

//-----------------------------------------------------------
//File:   Window_MainWindow.java
//Desc:   This class is the default screen upon startup.
//----------------------------------------------------------- 

public class W_MainMenu {

    // --------------- //
    // View Variables //
    // --------------- //

    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();

    private static Stage gameStage;
    private ScoreManager scoreManager = ScoreManager.getIt();


    final AudioClip THEME = new AudioClip(getClass().getResource("/media/maintheme.mp3").toString());
    final AudioClip BTN_CLICK = new AudioClip(getClass().getResource("/media/btnClick_seatBelt.mp3").toString());

    // ------------- //
    // GUI Elements //
    // ------------- //

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    @FXML
    void btn_newGameClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();   

        // items for the dialog
        Integer difficulty[] = { 1, 2, 3 };

        // create a choice dialog
        ChoiceDialog d = new ChoiceDialog(difficulty[0], difficulty);
        d.setHeaderText("Please Select Difficulty Level");

        // var loader = new FXMLLoader(getClass().getResource("W_InGame.fxml"));
        // var scene = new Scene(loader.load());

        // var stage = new Stage();
        // stage.getIcons().add(new Image("/icons/terminatorIcon2.png"));
        // stage.setScene(scene);
        // stage.setHeight(600);
        // stage.setWidth(800);
        // gameStage = stage;
        d.showAndWait().ifPresent(choice -> 
        {
            THEME.play();
            // stage.show();            
            //newStage.close();
            try 
            {
                AppGUI.windowLoad(oldStage, newStage, "Game", getClass().getResource("W_InGame.fxml"), true, d.getSelectedItem());
                // game.initialize((int) d.getSelectedItem());
            } 
            catch (InterruptedException | IOException e) 
            {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void btn_loadSavedGameClicked(ActionEvent event) throws IOException, InterruptedException {
        W_CRUDsaves CRUDInstance = W_CRUDsaves.instance();
        // Play button click sounds
        BTN_CLICK.play();
        var loader = new FXMLLoader(getClass().getResource("W_CRUDsaves.fxml"));
        //TODO: instead of adding a new VBox to the window, make W_CRUDSaves.java controller static and reference variables in there
        var tableView = new TableView();

        TableColumn<String, String> column1 = new TableColumn<>("Score");
        column1.setCellValueFactory(new PropertyValueFactory<>("score"));
    
        TableColumn<String, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, String> column3 = new TableColumn<>("Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        var vbox = CRUDInstance.getVbox_CRUDSaves();
        vbox.getChildren().add(tableView);

        
        var scene = new Scene(loader.load());
        newStage.setScene(scene);
        newStage.setTitle("Load/Save Game");
        newStage.show();

        // if (oldStage.isShowing()) {
        //     oldStage.close();
        // }
    }

    @FXML
    void btn_ControlsClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad(oldStage, newStage, "Controls / How to Play", getClass().getResource("W_Controls.fxml"), true, null);
    }

    @FXML
    void btn_scoreboardClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();

        //Load Scores
        scoreManager.loadScores();
        var scores = scoreManager.getList();
        TableView tableView = new TableView();

        TableColumn<String, String> column1 = new TableColumn<>("Score");
        column1.setCellValueFactory(new PropertyValueFactory<>("score"));
    
        TableColumn<String, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, String> column3 = new TableColumn<>("Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);


        if (scores.size() > 0) {
            for (Score score: scores) {
                score.getDt();
                score.getScore();
                score.getName();
                //add each of these things to a row in the table
                tableView.getItems().add(0, score.getScore());
                tableView.getItems().add(1, score.getName());
                tableView.getItems().add(2, score.getDt());
                        
            }

        }
        
    
        VBox vbox = new VBox(tableView);
        //AppGUI.windowLoad(oldStage, newStage, "Scoreboard", getClass().getResource("W_Scoreboard.fxml"));
        AppGUI.windowLoad(oldStage, newStage, "Scoreboard", getClass().getResource("W_Scoreboard.fxml"), true, null);
    }

    @FXML
    void btn_creditsClicked(ActionEvent event) throws IOException, InterruptedException {
        // Play button click sounds
        BTN_CLICK.play();
        AppGUI.windowLoad(oldStage, newStage, "Credits", getClass().getResource("W_Credits.fxml"), true, null);
    }

    public static Stage getGameStage() {
        return gameStage;
    }

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //

}