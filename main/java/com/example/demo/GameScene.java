package com.example.demo;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

class GameScene {
    private static int height = 700;
    private static int size;
    private final static int distanceBetweenCells = 10;
    private static double length;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells;
    private Group gameRoot;
    private Scene gameScene;
    private Scene endGameScene;
    private Group endGameRoot;
    private Stage primaryStage;

    private Text scoreText;
    private long currentScore = 0;

    static void setSize(int number) {
        size = number;
        length = (height - ((size + 1) * distanceBetweenCells)) / (double) size;
    }

    static double getLength() {
        return length;
    }

    private void randomFillNumber() {
        Cell[][] emptyCells = new Cell[this.size][this.size];
        int a = 0;
        int b = 0;
        int aForBound = 0, bForBound = 0;
        outer:
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < this.size - 1) {
                        bForBound = b;
                        b++;

                    } else {
                        aForBound = a;
                        a++;
                        b = 0;
                        if (a == this.size)
                            break outer;
                    }
                }
            }
        }


        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
        xCell = random.nextInt(aForBound + 1);
        yCell = random.nextInt(bForBound + 1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), this.gameRoot);
            emptyCells[xCell][yCell].setTextClass(text);
            this.gameRoot.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), this.gameRoot);
            emptyCells[xCell][yCell].setTextClass(text);
            this.gameRoot.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    private int haveEmptyCell() {
        int countSpace = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (cells[i][j].getNumber() == 0)
                    countSpace++;
                if (cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }

        if (countSpace > 0) {
            return 1;
        }
        return -1;
    }

    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        if (direct == 'l') {
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        coordinate = j;
        if (direct == 'r') {
            for (int k = j + 1; k <= this.size - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == this.size - 1) {
                    coordinate = this.size - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= this.size - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == this.size - 1) {
                    coordinate = this.size - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    private int moveLeft() {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                count += moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < this.size; j++) {
                cells[i][j].setModify(false);
            }
        }

        return count;
    }

    private int moveRight() {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = this.size - 1; j >= 0; j--) {
                count += moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < this.size; j++) {
                cells[i][j].setModify(false);
            }
        }

        return count;
    }

    private int moveUp() {
        int count = 0;
        for (int j = 0; j < this.size; j++) {
            for (int i = 0; i < this.size; i++) {
                count += moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < this.size; i++) {
                cells[i][j].setModify(false);
            }
        }
        return count;
    }

    private int moveDown() {
        int count = 0;
        for (int j = 0; j < this.size; j++) {
            for (int i = this.size - 1; i >= 0; i--) {
                count += moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < this.size; i++) {
                cells[i][j].setModify(false);
            }
        }

        return count;
    }

    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < this.size && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify() && !cells[i][j].getModify()
                    && cells[i][des + sign].getNumber() != 0 && cells[i][j].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    private int moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            currentScore += cells[i][j].getNumber() + cells[i][des + sign].getNumber();
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des + sign].setModify(true);
            return 1;
        } else if (des != j) {
            if (cells[i][j].getNumber() != 0) {
                cells[i][j].changeCell(cells[i][des]);
                return 1;
            }
        }
        return 0;
    }

    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < this.size && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify() && !cells[i][j].getModify()
                    && cells[des + sign][j].getNumber() != 0 && cells[i][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    private int moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            currentScore += cells[i][j].getNumber() + cells[des + sign][j].getNumber();
            cells[i][j].adder(cells[des + sign][j]);
            cells[des + sign][j].setModify(true);
            return 1;
        } else if (des != i) {
            if (cells[i][j].getNumber() != 0) {
                cells[i][j].changeCell(cells[des][j]);
                return 1;
            }
        }
        return 0;
    }

    private boolean haveSameNumberNearly(int i, int j) {
        if (i < this.size - 1 && j < this.size - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    private boolean canNotMove() {
        for (int i = 0; i < this.size - 1; i++) {
            for (int j = 0; j < this.size - 1; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // GameScene constructor to inject all the dependencies needed in this class from the Main class.
    public GameScene(Scene gameScene, Group gameRoot, Stage primaryStage, Scene endGameScene, Group endGameRoot, int size) {
        this.gameRoot = gameRoot;
        this.gameScene = gameScene;
        this.primaryStage = primaryStage;
        this.endGameScene = endGameScene;
        this.endGameRoot = endGameRoot;

        this.scoreText = new Text();

        this.setSize(size);
        this.cells = new Cell[this.size][this.size];
    }

    public void initialize() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                cells[i][j] = new Cell((j) * length + (j + 1) * distanceBetweenCells,
                        (i) * length + (i + 1) * distanceBetweenCells, length, this.gameRoot);
            }

        }

        Text text = new Text();
        this.gameRoot.getChildren().add(text);
        text.setText("SCORE: ");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        this.gameRoot.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        randomFillNumber();
        randomFillNumber();
    }

    void update(int movementCount) {
        this.scoreText.setText(String.valueOf(currentScore));
        int emptyCell = this.haveEmptyCell();
        System.out.println(emptyCell);
        if (emptyCell == 0) {
            primaryStage.setScene(endGameScene);

            EndGame.getInstance().show(endGameScene, endGameRoot, primaryStage, currentScore, "YOU WIN");
            this.gameRoot.getChildren().clear();
            currentScore = 0;
            return;
        }

        if (emptyCell == -1) {
            if (this.canNotMove()) {
                primaryStage.setScene(endGameScene);

                EndGame.getInstance().show(endGameScene, endGameRoot, primaryStage, currentScore, "GAME OVER");
                this.gameRoot.getChildren().clear();
                currentScore = 0;
            }
            return;
        }

        if (emptyCell == 1) {
            if (movementCount != 0) {
                this.randomFillNumber();
            }
        }
    }

    void run() {
        this.initialize();

        this.gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            Platform.runLater(() -> {
                int movementCount = 0;
                if (key.getCode() == KeyCode.DOWN || key.getCode() == KeyCode.S) {
                    movementCount = this.moveDown();
                } else if (key.getCode() == KeyCode.UP || key.getCode() == KeyCode.W) {
                    movementCount = this.moveUp();
                } else if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A) {
                    movementCount = this.moveLeft();
                } else if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D) {
                    movementCount = this.moveRight();
                }
                this.update(movementCount);
            });
        });
    }
}
