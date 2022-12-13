package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CreateUserMenu {
    private static CreateUserMenu createUserMenu = null;
    private int width;
    private int height;
    private Group createUserRoot;
    private Scene createUserScene;
    private Stage primaryStage;

    public CreateUserMenu() {
        this.width = 900;
        this.height = 600;

        this.createUserRoot = new Group();
        this.createUserScene = new Scene(this.createUserRoot, this.width, this.height, ThemeController.getInstance().getCurrentTheme().getBackgroundColor());
    }

    public static CreateUserMenu getInstance() {
        if (createUserMenu == null) {
            createUserMenu = new CreateUserMenu();
            return createUserMenu;
        }

        return createUserMenu;
    }

    public void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setAsPrimaryStage() {
        if (this.primaryStage != null) {
            this.primaryStage.setScene(this.createUserScene);
        }
    }

    public void show() {
        Text text = new Text("2048");
        text.setFont(Font.font(80));
        text.setWrappingWidth(this.width);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setY(140);
        this.createUserRoot.getChildren().add(text);

        Text errorText = new Text();
        errorText.setFont(Font.font(14));
        errorText.setWrappingWidth(this.width);
        errorText.setFill(Color.RED);
        errorText.setTextAlignment(TextAlignment.CENTER);
        errorText.setY(220);
        this.createUserRoot.getChildren().add(errorText);

        int textFieldWidth = 240;
        int textFieldHeight = 32;

        int centerXTextField = (this.width - textFieldWidth) / 2;

        TextField textField = new TextField();
        textField.setPrefSize(textFieldWidth, textFieldHeight);
        textField.relocate(centerXTextField, 280);
        this.createUserRoot.getChildren().add(textField);

        int buttonWidth = 240;
        int buttonHeight = 32;

        int centerXButton = (this.width - buttonWidth) / 2;

        Button createUserButton = new Button("CREATE USER");
        createUserButton.setPrefSize(buttonWidth, buttonHeight);
        createUserButton.setTextFill(Color.BLACK);
        createUserButton.relocate(centerXButton, 340);
        this.createUserRoot.getChildren().add(createUserButton);

        createUserButton.setOnMouseClicked(mouseEvent -> {
            if (textField.getText().length() < 6) {
                errorText.setText("Please input username minimum 6 characters");
            } else {
                String name = textField.getText();
                UserListAccount.getInstance().setCurrentUser(new UserAccount(name, 0));
                GameplayMenu gameplayMenu = GameplayMenu.getInstance();
                gameplayMenu.init(this.primaryStage);
                gameplayMenu.setAsPrimaryStage();
                gameplayMenu.show();
                this.primaryStage.show();
            }
        });
    }
}
