package com.example.demo;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * The object of this class is responsible to create a javafx text object.
 *
 * @author  Donald Agustino-modified
 */
public class TextMaker {
    private static TextMaker textMaker = null;

    /**
     * To initialize the TextMaker singleton.
     * @return the TextMaker class singleton object.
     */
    public static TextMaker getInstance() {
        if (textMaker == null)
            textMaker = new TextMaker();
        return textMaker;
    }

    /**
     * Create text instance on a certain position and root of the scene
     * @param input a string represent the text that would be shown
     * @param xCell a double that represent the x coordinate where the cell will be placed on the scene
     * @param yCell a double that represent the y coordinate where the cell will be placed on the scene
     * @param root a JavaFX Group class where the text will be attached to
     * @return
     */
    public Text madeText(String input, double xCell, double yCell, Group root) {
        double length = GameScene.getLength();
        double fontSize = (2 * length) / 7.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /**
     * Change the value exist in two text instance
     * @param first Javafx Text instance
     * @param second Javafx Text instance
     */
    public static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);
    }

}
