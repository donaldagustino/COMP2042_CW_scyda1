package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The object of this class is responsible to maintain the information of the current player (user) and store the
 * user gameplay result to the text file.
 *
 * @author Donald Agustino
 */
public class UserListAccount {
    private ArrayList<UserAccount> userAccounts;
    private UserAccount currentUser = null;
    private static UserListAccount userListAccount = null;

    /**
     * To initialize the UserListAccount singleton.
     * @return the UserListAccount class singleton object.
     */
    public static UserListAccount getInstance() {
        if (userListAccount == null) {
            userListAccount = new UserListAccount();
            return userListAccount;
        }
        return userListAccount;
    }

    /**
     * Constructor of UserListAccount
     */
    public UserListAccount() {
        this.userAccounts = new ArrayList<UserAccount>();
        this.loadUserAccounts();
    }

    /**
     * Load user account from the text file
     */
    private void loadUserAccounts() {
        this.userAccounts.clear();
        File file = new File("UserAccounts.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String lines = scanner.nextLine();
                String words[] = lines.split(";");
                UserAccount userAccount = new UserAccount(words[0], words[1], Integer.valueOf(words[2]), words[3]);
                this.userAccounts.add(userAccount);
            }
            this.userAccounts.forEach(userAccount -> System.out.println(userAccount));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sort user account by the score (descending)
     * @return a ArrayList of UserAccount sorted based on the score
     */
    public ArrayList<UserAccount> getUserAccountsFromHighestScore() {
        this.loadUserAccounts();
        ArrayList<UserAccount> sortedUserAccounts = (ArrayList<UserAccount>)userAccounts.clone();
        Collections.sort(sortedUserAccounts);

        sortedUserAccounts.forEach(userAccount -> System.out.println(userAccount));
        return sortedUserAccounts;
    }

    /**
     * Set current active user
     * @param user a UserAccount instance that will be set as the active user
     */
    public void setCurrentUser(UserAccount user) {
        this.currentUser = user;
    }

    /**
     * Get the current active user account
     * @return a UserAccount instance
     */
    public UserAccount getCurrentUser() {
        return this.currentUser;
    }
}
