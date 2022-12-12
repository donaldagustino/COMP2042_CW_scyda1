package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LeaderBoard {
    private static LeaderBoard leaderBoard = null;
    private int width;
    private int height;
    private Group leaderBoardRoot;
    private Scene leaderBoardScene;
    private Stage primaryStage;

    public LeaderBoard() {
        this.width = 900;
        this.height = 600;

        this.leaderBoardRoot = new Group();
        this.leaderBoardScene = new Scene(this.leaderBoardRoot, this.width, this.height, Color.rgb(150, 20, 100, 0.2));
    }

    public static LeaderBoard getInstance() {
        if (leaderBoard == null) {
            leaderBoard = new LeaderBoard();
            return leaderBoard;
        }

        return leaderBoard;
    }

    public void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setAsPrimaryStage() {
        if (this.primaryStage != null) {
            this.primaryStage.setScene(this.leaderBoardScene);
        }
    }

    public void show() {
        ObservableList<UserAccount> userAccountObservableList = FXCollections.observableArrayList(UserListAccount.getInstance().getUserAccountsFromHighestScore());

        TableView<UserAccount> table = new TableView<>(userAccountObservableList);

        TableColumn<UserAccount, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(this.primaryStage.getWidth() / 3);

        TableColumn<UserAccount, String> fullNameCol = new TableColumn<>("Full Name");
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fullNameCol.setPrefWidth(this.primaryStage.getWidth() / 3);

        TableColumn<UserAccount, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setPrefWidth(this.primaryStage.getWidth() / 3);

        table.getColumns().setAll(idCol, fullNameCol, scoreCol);

        table.setMinWidth(this.primaryStage.getWidth());
        table.setMaxHeight(this.primaryStage.getHeight() - 160);
        this.leaderBoardRoot.getChildren().add(table);

        int buttonWidth = 240;
        int buttonHeight = 32;

        int centerX = (this.width - buttonWidth) / 2;

        Button backButton = new Button("Back to Main Menu");
        backButton.setPrefSize(buttonWidth, buttonHeight);
        backButton.setTextFill(Color.BLACK);
        backButton.relocate(centerX, this.primaryStage.getHeight() - 160);
        this.leaderBoardRoot.getChildren().add(backButton);

        backButton.setOnMouseClicked(mouseEvent -> {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.init(primaryStage);
            mainMenu.setAsPrimaryStage();
            mainMenu.show();
        });
    }
}
