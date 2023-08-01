package com.isa.jjdzr.dto;

import com.isa.jjdzr.dictionary.AdvancementLevelCategory;
import com.isa.jjdzr.model.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDto {

    private Long userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private List<Role> roles = new ArrayList<>();

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

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
