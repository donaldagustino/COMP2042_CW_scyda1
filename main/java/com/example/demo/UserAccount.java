package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.UUID;

/**
 * The object of this class represent a single instance of the user account (player) which hold the details of
 * a record of user account, score, and the level of the game that the user play.
 *
 * @author Donald Agustino
 */
public class UserAccount implements Comparable<UserAccount> {
    private String id;
    private String name;
    private long score;
    private String mode;

    /**
     * Constructor of UserAccount class
     * @param id a string, id of the user
     * @param name a string represent the user's name
     * @param score a long represent the user's score
     * @param mode a string represent which mode the user play to get the score
     */
    public UserAccount(String id, String name, long score, String mode) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.mode = mode;
    }

    /**
     * Constructor of UserAccount class. Initialize user's id using UUID.
     * @param name a string represent the user's name
     * @param score a long represent the score that the user get
     */
    public UserAccount(String name, long score) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = score;
        this.mode = "";
    }

    /**
     * Constructor of UserAccount class. Used to create guest user
     */
    public UserAccount() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        this.id = UUID.randomUUID().toString();
        this.name = String.format("guest-%06d", number);
        this.score = 0;
    }

    /**
     * Get user's id
     * @return a string represent the id of the user object
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id of the user
     * @param id a string
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get user's name
     * @return  a string represent the name of the user object
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the user
     * @param name a string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user's score
     * @return a long represent the score of the user object
     */
    public long getScore() {
        return this.score;
    }

    /**
     * Set the user's score
     * @param score a long
     */
    public void setScore(long score) {
        this.score = score;
    }

    /**
     * Get the mode of the user record
     * @return a string represent the mode that the user play at a certain game
     */
    public String getMode() { return this.mode; }

    /**
     * Save user gameplay to the text file
     * @param mode a string represent the mode (difficulty) of the game that the user play
     */
    public void saveUserGameplay(String mode) {
        this.mode = mode;
        String text = String.format("%s;%s;%d;%s\n", this.id, this.name, this.score, this.mode);
        try {
            Files.write(Paths.get("UserAccounts.txt"), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Override the default toString method of Object class
     * @return a string that will be outputted when outputted the value of the UserAccount instance
     */
    @Override
    public String toString() {
        return String.format("ID: %s\nName: %s\nScore: %d\nMode: %s\n", this.id, this.name, this.score, this.mode);
    }

    /**
     * Compare user account based on the score
     * @param userAccount the object to be compared.
     * @return an integer
     */
    @Override
    public int compareTo(UserAccount userAccount) {
        return (int) (userAccount.getScore() - this.score);
    }
}
