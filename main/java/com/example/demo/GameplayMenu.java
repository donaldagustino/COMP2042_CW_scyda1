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

public class GameplayMenu {
    private static GameplayMenu gameplayMenu = null;
    private int width;
    private int height;
    private Group gameplayMenuRoot;
    private Scene gameplayMenuScene;
    private RadioButton rb;
    private Stage primaryStage;

    private String mode;

    public GameplayMenu() {
        this.width = 900;
        this.height = 600;

        this.gameplayMenuRoot = new Group();
        this.gameplayMenuScene = new Scene(this.gameplayMenuRoot, this.width, this.height, ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
    }

    public static GameplayMenu getInstance() {
        if (gameplayMenu == null) {
            gameplayMenu = new GameplayMenu();
            return gameplayMenu;
        }

        return gameplayMenu;
    }

    public String getMode() {
        return this.mode;
    }

    public void init(Stage primaryStage) {
        this.gameplayMenuScene.setFill(ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
        this.primaryStage = primaryStage;
    }

    public void setAsPrimaryStage() {
        if (this.primaryStage != null) {
            this.primaryStage.setScene(this.gameplayMenuScene);
        }
    }

    public void show() {
        Text username = new Text("Welcome, " + UserListAccount.getInstance().getCurrentUser().getName());
        username.setFont(Font.font(18));
        username.setWrappingWidth(this.width);
        username.setTextAlignment(TextAlignment.CENTER);
        username.setY(40);
        this.gameplayMenuRoot.getChildren().add(username);

        Text text = new Text("2048");
        text.setFont(Font.font(80));
        text.setWrappingWidth(this.width);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setY(140);
        this.gameplayMenuRoot.getChildren().add(text);

        int buttonWidth = 240;
        int buttonHeight = 32;

        int centerXButton = (this.width - buttonWidth) / 2;

        ToggleGroup radioGroup = new ToggleGroup();

        int radioWidth = 120;
        int radioHeight = 32;

        int centerX = (this.width - radioWidth) / 2;

        RadioButton r1 = new RadioButton("Easy  : 6 x 6");
        r1.setMinSize(radioWidth, radioHeight);
        r1.relocate(centerX, 240);
        RadioButton r2 = new RadioButton("Medium: 5 x 5");
        r2.setMinSize(radioWidth, radioHeight);
        r2.relocate(centerX, 280);
        RadioButton r3 = new RadioButton("Hard  : 4 x 4");
        r3.setMinSize(radioWidth, radioHeight);
        r3.relocate(centerX, 320);

        r1.setToggleGroup(radioGroup);
        r2.setToggleGroup(radioGroup);
        r3.setToggleGroup(radioGroup);

        r1.setSelected(true);

        this.gameplayMenuRoot.getChildren().add(r1);
        this.gameplayMenuRoot.getChildren().add(r2);
        this.gameplayMenuRoot.getChildren().add(r3);

        Button startGameButton = new Button("Start");
        startGameButton.setPrefSize(buttonWidth, buttonHeight);
        startGameButton.setTextFill(Color.BLACK);
        startGameButton.relocate(centerXButton, 400);
        this.gameplayMenuRoot.getChildren().add(startGameButton);

        this.rb = (RadioButton)radioGroup.getSelectedToggle();

        startGameButton.setOnMouseClicked(mouseEvent -> {
            this.gameplayMenuRoot.getChildren().removeAll(username, text, r1, r2, r3, startGameButton);
            Group endGameRoot = new Group();
            Scene endGameScene = new Scene(endGameRoot, 900, 900, ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
            Group gameRoot = new Group();
            Scene gameScene = new Scene(gameRoot, 900, 900, ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
            this.primaryStage.setScene(gameScene);
            GameScene game = new GameScene(gameScene, gameRoot, primaryStage, endGameScene, endGameRoot, 6);

            if (this.rb.equals(r1)) {
                game = new GameScene(gameScene, gameRoot, primaryStage, endGameScene, endGameRoot, 6);
            }

            if (this.rb.equals(r2)) {
                game = new GameScene(gameScene, gameRoot, primaryStage, endGameScene, endGameRoot, 5);
            }

            if (this.rb.equals(r3)) {
                game = new GameScene(gameScene, gameRoot, primaryStage, endGameScene, endGameRoot, 4);
            }

            this.mode = rb.getText();

            game.run();
        });

        radioGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            this.rb = (RadioButton)radioGroup.getSelectedToggle();
        });
    }
}
