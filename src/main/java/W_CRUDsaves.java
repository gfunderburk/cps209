/* --------------------------------------------------------------------------------------------- 
File:   W_CRUDsaves.java
Desc.   DEETE THIS WINDOW
--------------------------------------------------------------------------------------------- */


import java.time.LocalDateTime;
import Game_model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

public class W_CRUDsaves {

    // --------------- //
    // Media Elements  //
    // --------------- //

    final AudioClip BTN_CLICK = AppGUI.audioClip(this, "btnClick_seatBelt.mp3");
    final AudioClip THEME =     AppGUI.audioClip(this, "maintheme.mp3");

    // ----------- //
    //  Singleton  //
    // ----------- //

    private static W_CRUDsaves CRUDinstance = new W_CRUDsaves();

    public static W_CRUDsaves instance() {
        return CRUDinstance;
    }

    // --------------- //
    // View Variables //
    // --------------- //


    LocalDateTime now = LocalDateTime.now();

    // ------------- //
    // GUI Elements //
    // ------------- //

    @FXML
    VBox vbox_CRUDSaves;


    private ObservableList<Games> rows = FXCollections.observableArrayList();

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //



    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //


    public void initialize() {

        TableView tableView = new TableView();


        TableColumn<String, String> column1 = new TableColumn<>("Date");
        column1.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<String, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        rows.clear();
        rows.add(new Games(null, "Jeremy", now));
        rows.add(new Games(null, "Gunnar", now));
  
        tableView.getItems().clear();
        tableView.setItems(rows);

        Button btnMainMenu = new Button("Main Menu");
        btnMainMenu.setOnAction(e -> {

            try {
                BTN_CLICK.play();
                AppGUI.windowLoad(this, "Main Menu", "W_MainMenu.fxml", null);
            } catch (Exception e1){}
        });

        Button btnLoadSavedGame = new Button("Load Selected Game");
        btnLoadSavedGame.setOnAction(e -> {

            try {
                THEME.stop();
                AppGUI.windowLoad(this, "Game", "W_InGame.fxml", null);
            } catch (Exception e1){}
        });

        Label title = new Label("Saved Games");
        title.getStyleClass().clear();
        title.getStyleClass().add("titles");
        btnMainMenu.setAlignment(Pos.CENTER);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnMainMenu, btnLoadSavedGame);
    
        vbox_CRUDSaves.getChildren().addAll(title, tableView, hbox);
    }

    public VBox getVbox_CRUDSaves() {
        return vbox_CRUDSaves;
    }

    public void setVbox_CRUDSaves(VBox vbox_CRUDSaves) {
        this.vbox_CRUDSaves = vbox_CRUDSaves;
    }

    public static class Games {
 
        private final Game game;
        private final String name;
        private final String date;
 
        private Games(Game game, String newName, LocalDateTime newDate) {

            this.game = game;
            this.name = newName;
            this.date = newDate.getMonthValue() + "/" + newDate.getDayOfMonth() + "/" + newDate.getYear();
        }

        public Game getGame() {
            return game;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

    }

}