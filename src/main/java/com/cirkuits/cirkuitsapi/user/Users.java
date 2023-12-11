package com.cirkuits.cirkuitsapi.user;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table
public class Users {
    @Id
    @UuidGenerator
    private UUID userID;
    private String fullName;
    private String userName;
    private String email;
    private String mobile;
    private String password;
    private boolean active;

    public Users() {

    }

    public Users(String fullName, String userName, String email, String mobile, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public Users(UUID userID, String fullName, String userName, String email, String mobile, String password, boolean active) {
        this.userID = userID;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.active = active;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + userID +
                ", FullName='" + fullName + '\'' +
                ", UserName='" + userName + '\'' +
                ", Email='" + email + '\'' +
                ", Mobile='" + mobile + '\'' +
                ", Password='" + password + '\'' +
                ", Active=" + active +
                '}';
    }
}
