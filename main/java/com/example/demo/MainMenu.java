package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * The object of this class represent the main menu of the game. This main menu will be the one that shows up
 * on the very first time the application is started.
 *
 * @author Donald Agustino
 */

public class MainMenu {
    private static MainMenu mainMenu = null;
    private int width;
    private int height;
    private Group mainMenuRoot;
    private Scene mainMenuScene;
    private Stage primaryStage;

    /**
     * Constructor of MainMenu class
     */
    public MainMenu() {
        this.width = 900;
        this.height = 600;

        this.mainMenuRoot = new Group();
        this.mainMenuScene = new Scene(this.mainMenuRoot, this.width, this.height, ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
    }

    /**
     * To initialize the MainMenu singleton.
     * @return instantiated singleton object of the MainMenu class.
     */
    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
            return mainMenu;
        }

        return mainMenu;
    }

    /**
     * Initialize the primaryStage.
     * @param primaryStage a javaFX Stage class object.
     */
    public void init(Stage primaryStage) {
        this.mainMenuScene.setFill(ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
        this.primaryStage = primaryStage;
    }

    /**
     * Set mainMenuScene as the scene that is shown on primaryStage.
     */
    public void setAsPrimaryStage() {
        if (this.primaryStage != null) {
            this.primaryStage.setScene(this.mainMenuScene);
        }
    }

    /**
     * Get GameplayMenu instance show the GameplayMenu scene
     */
    public void showGameplayMenu() {
        GameplayMenu gameplayMenu = GameplayMenu.getInstance();
        gameplayMenu.init(this.primaryStage);
        gameplayMenu.setAsPrimaryStage();
        gameplayMenu.show();
        this.primaryStage.show();
    }

    /**
     * Get CreateUserMenu instance show the CreateUserMenu scene
     */
    public void showCreateUserMenu() {
        CreateUserMenu createUserMenu = CreateUserMenu.getInstance();
        createUserMenu.init(this.primaryStage);
        createUserMenu.setAsPrimaryStage();
        createUserMenu.show();
        this.primaryStage.show();
    }

    /**
     * Get Leaderboard instance show the Leaderboard scene
     */
    public void showLeaderboard() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.init(this.primaryStage);
        leaderBoard.setAsPrimaryStage();
        leaderBoard.show();
        this.primaryStage.show();
    }

    /**
     * Show the display of the MainMenu page.
     */
    public void show() {
        ThemeController themeController = ThemeController.getInstance();

        Text text = new Text("2048");
        text.setFont(Font.font(80));
        text.setWrappingWidth(this.width);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setY(140);
        this.mainMenuRoot.getChildren().add(text);

        int buttonWidth = 240;
        int buttonHeight = 32;

        int centerXButton = (this.width - buttonWidth) / 2;

        Button startAsUserButton = new Button("START AS USER");
        startAsUserButton.setPrefSize(buttonWidth, buttonHeight);
        startAsUserButton.setTextFill(Color.BLACK);
        startAsUserButton.relocate(centerXButton, 240);
        this.mainMenuRoot.getChildren().add(startAsUserButton);

        Button startAsGuestButton = new Button("START AS GUEST");
        startAsGuestButton.setPrefSize(buttonWidth, buttonHeight);
        startAsGuestButton.setTextFill(Color.BLACK);
        startAsGuestButton.relocate(centerXButton, 300);
        this.mainMenuRoot.getChildren().add(startAsGuestButton);

        Button leaderboardButton = new Button("VIEW LEADERBOARD");
        leaderboardButton.setPrefSize(buttonWidth, buttonHeight);
        leaderboardButton.setTextFill(Color.BLACK);
        leaderboardButton.relocate(centerXButton, 360);
        this.mainMenuRoot.getChildren().add(leaderboardButton);

        int comboBoxWidth = 240;
        int comboBoxHeight = 32;

        int centerX = (this.width - comboBoxWidth) / 2;

        ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("BLUE OCEAN");
        comboBox.getItems().add("RED NUANCE");

        comboBox.setValue(themeController.getCurrentThemeName());

        comboBox.setPrefSize(comboBoxWidth, comboBoxHeight);
        comboBox.relocate(centerX, 460);

        this.mainMenuRoot.getChildren().add(comboBox);

        startAsGuestButton.setOnMouseClicked(mouseEvent -> {
            UserListAccount.getInstance().setCurrentUser(new UserAccount());
            this.showGameplayMenu();
        });

        startAsUserButton.setOnMouseClicked(mouseEvent -> {
            this.showCreateUserMenu();
        });

        leaderboardButton.setOnMouseClicked(mouseEvent -> {
            this.showLeaderboard();
        });

        comboBox.setOnAction(actionEvent -> {
            themeController.setCurrentThemeByName(comboBox.getValue().toString());
            this.mainMenuScene.setFill(themeController.getCurrentTheme().getBackgroundColor());
        });
    }
}
