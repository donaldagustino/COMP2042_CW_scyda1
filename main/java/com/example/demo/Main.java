package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        UserListAccount userListAccount = UserListAccount.getInstance();
        userListAccount.getUserAccountsFromHighestScore();

        ThemeController themeController = ThemeController.getInstance();
        themeController.setCurrentThemeByName("BLUE OCEAN");

        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.init(primaryStage);
        mainMenu.setAsPrimaryStage();
        mainMenu.show();

        primaryStage.setTitle("2048");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
