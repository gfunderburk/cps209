import java.io.IOException;
import java.time.LocalDateTime;

import Data_model.Cereal;
import Data_model.CerealManager;
import Game_model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class W_CRUDsaves {

    // ------------ //
    // Singleton //
    // ------------ //

    private static W_CRUDsaves CRUDinstance = new W_CRUDsaves();

    public static W_CRUDsaves instance() {
        return CRUDinstance;
    }

    private CerealManager cerealManager = CerealManager.getIt();

    // --------------- //
    // View Variables //
    // --------------- //

    Stage newStage = AppGUI.getStage();
    Stage oldStage = new Stage();

    // ------------- //
    // GUI Elements //
    // ------------- //

    @FXML
    VBox vbox_CRUDSaves;

    private ObservableList<Games> rows = FXCollections.observableArrayList();

    // ------------ //
    // GUI Methods // (DIRECT USER EVENTS)
    // ------------ //

    @FXML
    void btn_mainMenuClicked(ActionEvent event) throws IOException, InterruptedException {
        // TODO: Save input

        AppGUI.windowLoad(oldStage, newStage, "Main Menu", getClass().getResource("W_MainMenu.fxml"), true, null);
    }

    @FXML
    void btn_loadSavedGame(ActionEvent event) {
        // TODO: Load the game from the .dat file ArrayList
        // using the selected row number as the index

    }

    // ------------- //
    // View Methods // (INDIRECT AUTOMATIC METHODS USED BY THE GUI EVENT METHODS)
    // ------------- //

    @FXML
    void initialize() throws InterruptedException {
        // TODO: add load methods here

        cerealManager.loadCerealDir(); // load method?

        var saves = cerealManager.getList();

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

        if (saves.size() > 0) {
            for (Cereal game : saves) {
                rows.add(new Games(game.getGame(), game.getName(), game.getDt()));
            }

        }

        // TODO: add load game method here

        tableView.getItems().clear();
        tableView.setItems(rows);

        Button btnMainMenu = new Button("Main Menu");
        btnMainMenu.setOnAction(e -> {

            try {
                AppGUI.windowLoad(oldStage, newStage, "Main Menu", getClass().getResource("W_MainMenu.fxml"), true,
                        null);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        Button btnLoadSavedGame = new Button("Load Selected Game");
        btnLoadSavedGame.setOnAction(e -> {
            // TODO: Add code here to load saved game
            //Get selected row
            //Get data from selected row
            //

            try {
                AppGUI.windowLoad(oldStage, newStage, "Game", getClass().getResource("W_InGame.fxml"), true, null);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        btnMainMenu.setAlignment(Pos.CENTER);
        vbox_CRUDSaves.getChildren().addAll(tableView, btnMainMenu);
    }


    /**
     * Creates a new Button object and sets its attributes.
     * @param name, the ID of the button.
     * @param disableBtn, btn is initialized as disabled IF true.
     * @return newly created and attributed button.
     */
    private Button createButton(String btnText, String name, boolean disableBtn) {

        Button newButton = new Button(btnText);

        newButton.setId(name);                      // attach name
        newButton.getStyleClass().add("CSS_Class"); // attach style class
        if(disableBtn) newButton.setDisable(true);  // set disable status

        return newButton;
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