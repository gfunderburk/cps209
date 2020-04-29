/* --------------------------------------------------------------------------------------------- 
File:   W_Scoreboard.java
Desc.   Scoreboard window displays the scores of previous players who have played the game.
--------------------------------------------------------------------------------------------- */


import Game_model.GameSounds;
import java.time.LocalDateTime;
import Data_model.Score;
import Data_model.ScoreManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class W_Scoreboard {

    // --------------- //
    // Media Elements //
    // --------------- //


    // --------------- //
    // View Variables //
    // --------------- //
    
    private static W_Scoreboard scoreboardInstance = new W_Scoreboard();

    public static W_Scoreboard getInstance() {
        return scoreboardInstance;
    }

    private ScoreManager scoreManager = ScoreManager.getIt();

    private ObservableList<Scores> rows = FXCollections.observableArrayList();


    // ------------- //
    // GUI Elements //
    // ------------- //

    @FXML
    VBox vbox_scoreboard;

    @FXML
    TableView tableView;

    @FXML
    TableColumn rankingColumn;

    @FXML
    TableColumn nameColumn;

    @FXML
    TableColumn pointsColumn;

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    public void initialize() {
        // Load Scores
        scoreManager.getList().clear();
        scoreManager.loadScores();
        var scores = scoreManager.getList();
        rows.clear(); //clear observable list

        TableView tableView = new TableView();

        TableColumn<String, String> column1 = new TableColumn<>("Date");
        column1.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<String, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<String, String> column3 = new TableColumn<>("Score");
        column3.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        if (scores.size() > 0) {
            for (Score score : scores) {
                rows.add(new Scores(score.getDt(), score.getName(), score.getScore()));
            }

        }
        tableView.getItems().clear(); //Clear tableview
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setMaxWidth(900);
        tableView.setItems(rows); //add the new combination of scores

        Button btnMainMenu = new Button("Main Menu");
        btnMainMenu.setOnAction(e -> {

            try {
                GameSounds.it().BTN_CLICK.play();
                AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
            }catch (Exception e1){}
        });

        Label title = new Label("High Scores");
        title.getStyleClass().clear();
        title.getStyleClass().add("titles");
        btnMainMenu.setAlignment(Pos.CENTER);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnMainMenu);
        vbox_scoreboard.getChildren().addAll(title, tableView, hbox);
    }



    public VBox getVbox_scoreboard() {
        return vbox_scoreboard;
    }

    public void setVbox_scoreboard(VBox vbox_scoreboard) {
        this.vbox_scoreboard = vbox_scoreboard;
    }


    //  ------------- //
    //  View Methods  //    (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // -------------  //


    public static class Scores {
 
        private final int score;
        private final String name;
        private final String date;
 
        private Scores(LocalDateTime newDate, String newName, int newScore) {

            this.score = newScore;
            this.name = newName;
            this.date = newDate.getMonthValue() + "/" + newDate.getDayOfMonth() + "/" + newDate.getYear();
        }

        public int getScore() {
            return score;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

    }

}