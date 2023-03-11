package com.isa.jjdzr.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int userID;
    private String userName;
    private String userPassword;
    private String userEmail;
    private int userAdvancementLevel;
}
