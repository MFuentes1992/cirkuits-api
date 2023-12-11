package com.cirkuits.cirkuitsapi.login;

public class Login {
    private final String email;
    private final String password;

    public Login(String userName, String password) {
        this.email = userName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
