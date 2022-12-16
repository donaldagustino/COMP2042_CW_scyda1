package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Object of this class represent a leaderboard menu which show the history of the game played by the users.
 *
 * @author Donald Agustino
 */
public class LeaderBoard {
    private static LeaderBoard leaderBoard = null;
    private int width;
    private int height;
    private Group leaderBoardRoot;
    private Scene leaderBoardScene;
    private Stage primaryStage;

    /**
     * Constructor of LeaderBoard class
     */
    public LeaderBoard() {
        this.width = 900;
        this.height = 600;

        this.leaderBoardRoot = new Group();
        this.leaderBoardScene = new Scene(this.leaderBoardRoot, this.width, this.height, ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
    }

    /**
     * To initialize the LeaderBoard singleton.
     * @return instantiated singleton object of the LeaderBoard class.
     */
    public static LeaderBoard getInstance() {
        if (leaderBoard == null) {
            leaderBoard = new LeaderBoard();
            return leaderBoard;
        }

        return leaderBoard;
    }

    /**
     * Initialize the primaryStage.
     * @param primaryStage a javaFX Stage class object.
     */
    public void init(Stage primaryStage) {
        this.leaderBoardScene.setFill(ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
        this.primaryStage = primaryStage;
    }

    /**
     * Set leaderBoardScene as the scene that is shown on primaryStage.
     */
    public void setAsPrimaryStage() {
        if (this.primaryStage != null) {
            this.primaryStage.setScene(this.leaderBoardScene);
        }
    }

    /**
     * Get MainMenu instance show the MainMenu scene
     */
    public void showMainMenu() {
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.init(primaryStage);
        mainMenu.setAsPrimaryStage();
        mainMenu.show();
    }

    /**
     * Show the display of the Leaderboard page.
     */
    public void show() {
        ObservableList<UserAccount> userAccountList = FXCollections.observableArrayList(UserListAccount.getInstance().getUserAccountsFromHighestScore());

        TableView<UserAccount> table = new TableView<>();
        table.setItems(userAccountList);

        TableColumn<UserAccount, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(this.primaryStage.getWidth() / 4);

        TableColumn<UserAccount, String> fullNameCol = new TableColumn<>("Full Name");
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fullNameCol.setPrefWidth(this.primaryStage.getWidth() / 4);

        TableColumn<UserAccount, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setPrefWidth(this.primaryStage.getWidth() / 4);

        TableColumn<UserAccount, String> modeCol = new TableColumn<>("Mode");
        modeCol.setCellValueFactory(new PropertyValueFactory<>("mode"));
        modeCol.setPrefWidth(this.primaryStage.getWidth() / 4);

        table.getColumns().setAll(idCol, fullNameCol, scoreCol, modeCol);

        table.setMinWidth(this.primaryStage.getWidth());
        table.setMaxHeight(this.primaryStage.getHeight() - 160);
        this.leaderBoardRoot.getChildren().add(table);

        int comboBoxWidth = 240;
        int comboBoxHeight = 32;

        int centerX = (this.width - comboBoxWidth) / 2;

        ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("All");
        comboBox.getItems().add("Easy  : 6 x 6");
        comboBox.getItems().add("Medium: 5 x 5");
        comboBox.getItems().add("Hard  : 4 x 4");

        comboBox.getSelectionModel().select(0);

        comboBox.setPrefSize(comboBoxWidth, comboBoxHeight);
        comboBox.relocate(centerX, this.primaryStage.getHeight() - 220);

        this.leaderBoardRoot.getChildren().add(comboBox);

        int buttonWidth = 240;
        int buttonHeight = 32;

        Button backButton = new Button("BACK TO MAIN MENU");
        backButton.setPrefSize(buttonWidth, buttonHeight);
        backButton.setTextFill(Color.BLACK);
        backButton.relocate(centerX, this.primaryStage.getHeight() - 160);
        this.leaderBoardRoot.getChildren().add(backButton);

        backButton.setOnMouseClicked(mouseEvent -> {
            this.showMainMenu();
        });

        comboBox.setOnAction(actionEvent -> {
            System.out.println(comboBox.getValue());
            FilteredList<UserAccount> filteredUserAccountList = new FilteredList<>(userAccountList, userAccount -> {
                if (comboBox.getSelectionModel().getSelectedIndex() == 0) {
                    return true;
                }
                return userAccount.getMode().equals(comboBox.getValue());
            });
            table.setItems(filteredUserAccountList);
        });
    }
}
