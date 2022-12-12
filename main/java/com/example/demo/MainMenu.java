package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainMenu {
    private static MainMenu mainMenu = null;
    private int width;
    private int height;
    private Group mainMenuRoot;
    private Scene mainMenuScene;
    private Stage primaryStage;

    public MainMenu() {
        this.width = 900;
        this.height = 600;

        this.mainMenuRoot = new Group();
        this.mainMenuScene = new Scene(this.mainMenuRoot, this.width, this.height, Color.rgb(150, 20, 100, 0.2));
    }

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
            return mainMenu;
        }

        return mainMenu;
    }

    public void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setAsPrimaryStage() {
        if (this.primaryStage != null) {
            this.primaryStage.setScene(this.mainMenuScene);
        }
    }

    public void show() {
        Text text = new Text("2048");
        text.setFont(Font.font(80));
        text.setWrappingWidth(this.width);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setY(140);
        this.mainMenuRoot.getChildren().add(text);

        int buttonWidth = 240;
        int buttonHeight = 32;

        int centerXButton = (this.width - buttonWidth) / 2;

        Button startAsUserButton = new Button("Start as User");
        startAsUserButton.setPrefSize(buttonWidth, buttonHeight);
        startAsUserButton.setTextFill(Color.BLACK);
        startAsUserButton.relocate(centerXButton, 240);
        this.mainMenuRoot.getChildren().add(startAsUserButton);

        Button startAsGuestButton = new Button("Start as Guest");
        startAsGuestButton.setPrefSize(buttonWidth, buttonHeight);
        startAsGuestButton.setTextFill(Color.BLACK);
        startAsGuestButton.relocate(centerXButton, 300);
        this.mainMenuRoot.getChildren().add(startAsGuestButton);

        Button leaderboardButton = new Button("View Leaderboard");
        leaderboardButton.setPrefSize(buttonWidth, buttonHeight);
        leaderboardButton.setTextFill(Color.BLACK);
        leaderboardButton.relocate(centerXButton, 360);
        this.mainMenuRoot.getChildren().add(leaderboardButton);

        startAsGuestButton.setOnMouseClicked(mouseEvent -> {
            UserListAccount.getInstance().setCurrentUser(new UserAccount());
            GameplayMenu gameplayMenu = GameplayMenu.getInstance();
            gameplayMenu.init(this.primaryStage);
            gameplayMenu.setAsPrimaryStage();
            gameplayMenu.show();
            this.primaryStage.show();
        });

        startAsUserButton.setOnMouseClicked(mouseEvent -> {
            CreateUserMenu createUserMenu = CreateUserMenu.getInstance();
            createUserMenu.init(this.primaryStage);
            createUserMenu.setAsPrimaryStage();
            createUserMenu.show();
            this.primaryStage.show();
        });

        leaderboardButton.setOnMouseClicked(mouseEvent -> {
            LeaderBoard leaderBoard = LeaderBoard.getInstance();
            leaderBoard.init(this.primaryStage);
            leaderBoard.setAsPrimaryStage();
            leaderBoard.show();
            this.primaryStage.show();
        });
    }
}
