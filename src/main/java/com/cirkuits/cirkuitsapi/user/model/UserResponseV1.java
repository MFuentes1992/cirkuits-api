package com.cirkuits.cirkuitsapi.user.model;

import jakarta.persistence.Column;

public class UserResponseV1 {
    private String UserID;
    private String fullName;
    private String userName;
    private String email;
    private String mobile;
    private boolean active;
    private String dob;
    private int gender;

    public UserResponseV1() {
    }

    public UserResponseV1(String userID, String fullName, String userName, String email, String mobile, boolean active, String dob, int gender) {
        this.UserID = userID;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.active = active;
        this.dob = dob;
        this.gender = gender;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserResponseV1{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", active=" + active +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
