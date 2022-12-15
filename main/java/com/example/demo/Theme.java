package com.example.demo;

import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * The object of this class is responsible for manipulating the theme information of the application such as
 * the background color and the color of the 2048 board.
 *
 * @author Donald Agustino
 */
public class Theme {
    private Color backgroundColor;
    private HashMap<Integer, Color> cellColorMap;

    public Theme() {
        this.cellColorMap = new HashMap<Integer, Color>();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void addCellColorMap(int number, Color color) {
        cellColorMap.put(number, color);
    }

    public HashMap<Integer, Color> getCellColorMap() {
        return this.cellColorMap;
    }
}
