package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.UUID;

public class UserAccount implements Comparable<UserAccount> {
    private String id;
    private String name;
    private long score;

    public UserAccount(String id, String name, long score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public UserAccount(String name, long score) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = score;
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

    public void saveUserAccount() {
        String text = String.format("%s;%s;%d\n", this.id, this.name, this.score);
        try {
            Files.write(Paths.get("UserAccounts.txt"), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nName: %s\nScore: %d\n", this.id, this.name, this.score);
    }

    @Override
    public int compareTo(UserAccount userAccount) {
        return (int) (userAccount.getScore() - this.score);
    }
}
