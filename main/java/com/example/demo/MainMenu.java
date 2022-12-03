package com.example.demo;

import javafx.geometry.Pos;
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

    private RadioButton rb;

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
        text.setWrappingWidth(this.width);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setY(100);
        this.mainMenuRoot.getChildren().add(text);

        int buttonWidth = 144;
        int buttonHeight = 32;

        int centerXButton = (this.width - buttonWidth) / 2;

        Button accountGameButton = new Button("Account");
        accountGameButton.setPrefSize(buttonWidth, buttonHeight);
        accountGameButton.setTextFill(Color.BLACK);
        accountGameButton.relocate(centerXButton, 150);
        this.mainMenuRoot.getChildren().add(accountGameButton);

        ToggleGroup radioGroup = new ToggleGroup();

        int radioWidth = 120;
        int radioHeight = 32;

        int centerX = (this.width - radioWidth) / 2;

        RadioButton r1 = new RadioButton("Easy  : 6 x 6");
        r1.setMinSize(radioWidth, radioHeight);
        r1.relocate(centerX, 200);
        RadioButton r2 = new RadioButton("Medium: 5 x 5");
        r2.setMinSize(radioWidth, radioHeight);
        r2.relocate(centerX, 240);
        RadioButton r3 = new RadioButton("Hard  : 4 x 4");
        r3.setMinSize(radioWidth, radioHeight);
        r3.relocate(centerX, 280);

        r1.setToggleGroup(radioGroup);
        r2.setToggleGroup(radioGroup);
        r3.setToggleGroup(radioGroup);

        r1.setSelected(true);

        this.mainMenuRoot.getChildren().add(r1);
        this.mainMenuRoot.getChildren().add(r2);
        this.mainMenuRoot.getChildren().add(r3);

        Button startGameButton = new Button("Start");
        startGameButton.setPrefSize(buttonWidth, buttonHeight);
        startGameButton.setTextFill(Color.BLACK);
        startGameButton.relocate(centerXButton, 320);
        this.mainMenuRoot.getChildren().add(startGameButton);

        this.rb = (RadioButton)radioGroup.getSelectedToggle();

        startGameButton.setOnMouseClicked(mouseEvent -> {
            Group endGameRoot = new Group();
            Scene endGameScene = new Scene(endGameRoot, 900, 900, Color.rgb(250, 20, 100, 0.2));
            Group gameRoot = new Group();
            Scene gameScene = new Scene(gameRoot, 900, 900, Color.rgb(189, 177, 92));
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

            game.run();
        });

        radioGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            this.rb = (RadioButton)radioGroup.getSelectedToggle();
        });
    }
}
