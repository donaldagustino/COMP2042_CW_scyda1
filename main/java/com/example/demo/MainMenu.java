package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
            return new MainMenu();
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
        text.relocate(this.width / 3, 50);
        this.mainMenuRoot.getChildren().add(text);

        Button startGameButton = new Button("Start");
        startGameButton.setPrefSize(100, 30);
        startGameButton.setTextFill(Color.BLACK);
        startGameButton.relocate(this.width / 3, 150);
        this.mainMenuRoot.getChildren().add(startGameButton);

        startGameButton.setOnMouseClicked(mouseEvent -> {
            Group endGameRoot = new Group();
            Scene endGameScene = new Scene(endGameRoot, 900, 900, Color.rgb(250, 20, 100, 0.2));
            Group gameRoot = new Group();
            Scene gameScene = new Scene(gameRoot, 900, 900, Color.rgb(189, 177, 92));
            this.primaryStage.setScene(gameScene);
            GameScene game = new GameScene(gameScene, gameRoot, primaryStage, endGameScene, endGameRoot, 4);
            game.run();
        });
    }
}
