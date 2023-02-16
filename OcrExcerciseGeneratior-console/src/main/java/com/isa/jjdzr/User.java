package com.isa.jjdzr;

class User {
    private String userName;
    private String userPassword;
    private String userEmail;
    private int userAdvancementLevel;

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
