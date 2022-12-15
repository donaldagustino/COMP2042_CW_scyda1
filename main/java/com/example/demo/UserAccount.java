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

    public UserAccount(String id, String name, long score, String mode) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.mode = mode;
    }

    public UserAccount(String name, long score) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = score;
        this.mode = "";
    }

    public UserAccount() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        this.id = UUID.randomUUID().toString();
        this.name = String.format("guest-%06d", number);
        this.score = 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return this.score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getMode() { return this.mode; }

    public void saveUserGameplay(String mode) {
        this.mode = mode;
        String text = String.format("%s;%s;%d;%s\n", this.id, this.name, this.score, this.mode);
        try {
            Files.write(Paths.get("UserAccounts.txt"), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nName: %s\nScore: %d\nMode: %s\n", this.id, this.name, this.score, this.mode);
    }

    @Override
    public int compareTo(UserAccount userAccount) {
        return (int) (userAccount.getScore() - this.score);
    }
}
