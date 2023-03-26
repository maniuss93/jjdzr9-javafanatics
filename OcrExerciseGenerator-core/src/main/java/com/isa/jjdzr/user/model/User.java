package com.isa.jjdzr.user.model;

import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import jakarta.persistence.*;
import lombok.NonNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @NonNull
    private String userName;
    @NonNull
    private String userPassword;
    @NonNull
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private AdvancementLevelCategory userAdvancementLevel;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
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

    public AdvancementLevelCategory getUserAdvancementLevel() {
        return userAdvancementLevel;
    }

    public void setUserAdvancementLevel(AdvancementLevelCategory userAdvancementLevel) {
        this.userAdvancementLevel = userAdvancementLevel;
    }

}
