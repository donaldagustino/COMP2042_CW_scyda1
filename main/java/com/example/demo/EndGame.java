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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Optional;


public class EndGame {

    private int width;
    private int height;
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
        this.width = 900;
        this.height = 600;
        this.endGameScene = endGameScene;
        this.endGameRoot = endGameRoot;
        this.primaryStage = primaryStage;
    }

    public void show(long score, String textPrompt) {
        Text text = new Text(textPrompt);
        text.setWrappingWidth(this.width);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setY(100);
        text.setFont(Font.font(80));
        this.endGameRoot.getChildren().add(text);

        Text scoreText = new Text("Score: " + score);
        scoreText.setFill(Color.BLACK);
        scoreText.setWrappingWidth(this.width);
        scoreText.setTextAlignment(TextAlignment.CENTER);
        scoreText.setY(250);
        scoreText.setFont(Font.font(80));
        this.endGameRoot.getChildren().add(scoreText);

        int buttonWidth = 144;
        int buttonHeight = 32;

        int centerX = (this.width - buttonWidth) / 2;

        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(buttonWidth, buttonHeight);
        quitButton.setTextFill(Color.BLACK);
        this.endGameRoot.getChildren().add(quitButton);
        quitButton.relocate(centerX, 400);

        Button mainMenuButton = new Button("MAIN MENU");
        mainMenuButton.setPrefSize(buttonWidth, buttonHeight);
        mainMenuButton.setTextFill(Color.BLACK);
        this.endGameRoot.getChildren().add(mainMenuButton);
        mainMenuButton.relocate(centerX, 500);

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

        mainMenuButton.setOnMouseClicked(mouseEvent -> {
            MainMenu mainMenu = MainMenu.getInstance();

            System.out.println(mainMenu);

            mainMenu.init(primaryStage);

            mainMenu.setAsPrimaryStage();

            mainMenu.show();
        });
    }
}
