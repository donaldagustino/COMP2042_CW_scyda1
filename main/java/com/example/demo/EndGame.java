package com.example.demo;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;


public class EndGame {

    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    private static EndGame singleInstance = null;

    private Scene endGameScene;
    private Group endGameRoot;
    private Stage primaryStage;

    public static EndGame getInstance() {
        if (singleInstance == null)
            singleInstance = new EndGame();
        return singleInstance;
    }

    public void init(Scene endGameScene, Group endGameRoot, Stage primaryStage) {
        this.endGameScene = endGameScene;
        this.endGameRoot = endGameRoot;
        this.primaryStage = primaryStage;
    }

    public void show(long score, String textPrompt) {
        Text text = new Text(textPrompt);
        text.relocate(250, 250);
        text.setFont(Font.font(80));
        this.endGameRoot.getChildren().add(text);


        Text scoreText = new Text(score + "");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(250, 600);
        scoreText.setFont(Font.font(80));
        this.endGameRoot.getChildren().add(scoreText);

        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(100, 30);
        quitButton.setTextFill(Color.BLACK);
        this.endGameRoot.getChildren().add(quitButton);
        quitButton.relocate(100, 800);

        Button newGameButton = new Button("NEW GAME");
        newGameButton.setPrefSize(100, 30);
        newGameButton.setTextFill(Color.BLACK);
        this.endGameRoot.getChildren().add(newGameButton);
        newGameButton.relocate(100, 850);

        Button mainMenuButton = new Button("MAIN MENU");
        mainMenuButton.setPrefSize(100, 30);
        mainMenuButton.setTextFill(Color.BLACK);
        this.endGameRoot.getChildren().add(mainMenuButton);
        mainMenuButton.relocate(100, 750);

        quitButton.setOnMouseClicked(mouseEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit Dialog");
            alert.setHeaderText("Quit from this page");
            alert.setContentText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.endGameRoot.getChildren().clear();
            }
        });

        newGameButton.setOnMouseClicked(mouseEvent -> {
            Group gameRoot = new Group();
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
            primaryStage.setScene(gameScene);
            GameScene game = new GameScene(gameScene, gameRoot, primaryStage, endGameScene, endGameRoot, 4);
            game.run();
        });

        mainMenuButton.setOnMouseClicked(mouseEvent -> {
            MainMenu mainMenu = MainMenu.getInstance();

            System.out.println(mainMenu);

            mainMenu.init(primaryStage);

            mainMenu.setAsPrimaryStage();

            mainMenu.show();
        });
    }
}
