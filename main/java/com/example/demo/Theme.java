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

    /**
     * Constructor of Theme class
     */
    public Theme() {
        this.cellColorMap = new HashMap<Integer, Color>();
    }

    /**
     * Set the backgroundColor
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Get the backgroundColor
     * @return javaFx Color instance represent the color of the scene background
     */
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Add color of the cell to the color map
     * @param number the number of the 2048 board cell value
     * @param color the color to represent the number at the board cell
     */
    public void addCellColorMap(int number, Color color) {
        cellColorMap.put(number, color);
    }

    /**
     * Get the cell color map
     * @return color map
     */
    public HashMap<Integer, Color> getCellColorMap() {
        return this.cellColorMap;
    }
}
