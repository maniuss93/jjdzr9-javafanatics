package com.isa.jjdzr.user.model;

import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_advancement_level", nullable = false)
    private AdvancementLevelCategory userAdvancementLevel;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userID) {
        this.userId = userID;
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
