package com.isa.jjdzr.user.model;

import jakarta.persistence.*;
import lombok.NonNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @NonNull
    private String userName;
    @NonNull
    private String userPassword;
    @NonNull
    private String userEmail;
    private int userAdvancementLevel;


    public User(int userID, @NonNull String userName, @NonNull String userPassword, @NonNull String userEmail, int userAdvancementLevel) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAdvancementLevel = userAdvancementLevel;
    }

    public User(int userID, @NonNull String userName, @NonNull String userPassword, @NonNull String userEmail) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public User(@NonNull String userName, @NonNull String userPassword, @NonNull String userEmail, int userAdvancementLevel) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAdvancementLevel = userAdvancementLevel;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserAdvancementLevel() {
        return userAdvancementLevel;
    }

    public void setUserAdvancementLevel(int userAdvancementLevel) {
        this.userAdvancementLevel = userAdvancementLevel;
    }

}
