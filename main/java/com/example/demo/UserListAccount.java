package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class UserListAccount {
    private ArrayList<UserAccount> userAccounts;
    private UserAccount currentUser = null;
    private static UserListAccount userListAccount = null;

    public static UserListAccount getInstance() {
        if (userListAccount == null) {
            userListAccount = new UserListAccount();
            return userListAccount;
        }
        return userListAccount;
    }

    public UserListAccount() {
        this.userAccounts = new ArrayList<UserAccount>();
        try (Scanner scanner = new Scanner(new File("UserAccounts.txt"))) {
            while (scanner.hasNext()) {
                String lines = scanner.nextLine();
                String words[] = lines.split(";");
                UserAccount userAccount = new UserAccount(words[0], words[1], Integer.valueOf(words[2]));
                this.userAccounts.add(userAccount);
            }
            this.userAccounts.forEach(userAccount -> System.out.println(userAccount));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserAccount> getUserAccountsFromHighestScore() {
        ArrayList<UserAccount> sortedUserAccounts = (ArrayList<UserAccount>)userAccounts.clone();
        Collections.sort(sortedUserAccounts);

        sortedUserAccounts.forEach(userAccount -> System.out.println(userAccount));
        return sortedUserAccounts;
    }

    public void setCurrentUser(UserAccount user) {
        this.currentUser = user;
    }

    public UserAccount getCurrentUser() {
        return this.currentUser;
    }
}
