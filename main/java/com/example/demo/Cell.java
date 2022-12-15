package com.example.demo;


import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Object of this class represent a Cell of 2048 boards number
 *
 * @author  Donald Agustino - modified
 */
public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    /**
     * Constructor of Cell class
     * @param x a double represent the x coordinate of the cell
     * @param y a double represent the y coordinate of the cell
     * @param scale a double represent the size of the cell
     * @param root a root of the scene which the cell will be injected.
     */

    public Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        this.root = root;
        rectangle.setFill(ThemeController.getInstance().getCurrentTheme().getCellColorMap().get(0));
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    /**
     * Set whether a cell is modifiable or not.
     * @param modify A boolean value which determine a cell could be modified or not.
     */

    public void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     * Get the modifiable information of the cell
     * @return a boolean which represent whether a cell is modifiable or not.
     */

    public boolean getModify() {
        return modify;
    }

    /**
     * Set the text of the cell
     * @param textClass a Javafx Text class object
     */

    public void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Change the current cell information to the cell information that pass through the params.
     * @param cell a Cell object
     */

    public void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * Update the value of the cell by adding up value of this class cell with the value of
     * the cell that pass through the params.
     * @param cell a Cell object
     */

    public void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * Set the color of the cell based on the number that the cell holds.
     * @param number an integer represent the number inside the cell
     */

    public void setColorByNumber(int number) {
        ThemeController themeController = ThemeController.getInstance();
        rectangle.setFill(themeController.getCurrentTheme().getCellColorMap().get(number));
    }

    /**
     * Get the x coordinate of the cell at the application view
     * @return a double represent the x coordinate of the cell
     */

    public double getX() {
        return rectangle.getX();
    }

    /**
     * Get the y coordinate of the cell at the application view
     * @return a double represent the y coordinate of the cell
     */

    public double getY() {
        return rectangle.getY();
    }

    /**
     * Get the number of the cell
     * @return an integer represent the number that the cell hold currently
     */

    public int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     * Get the object of the cell's javafx text
     * @return an object of Javafx Text class
     */

    private Text getTextClass() {
        return textClass;
    }

}
