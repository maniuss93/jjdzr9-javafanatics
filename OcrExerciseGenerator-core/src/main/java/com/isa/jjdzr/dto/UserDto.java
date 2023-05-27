package com.isa.jjdzr.dto;

import com.isa.jjdzr.user.service.AdvancementLevelCategory;
import org.springframework.stereotype.Service;

@Service
public class UserDto {

    private Long userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    public UserDto(Long userId, String userName, String userPassword, String userEmail, AdvancementLevelCategory userAdvancementLevel) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAdvancementLevel = userAdvancementLevel;
    }

    public UserDto() {
    }

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
