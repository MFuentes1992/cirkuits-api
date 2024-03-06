package com.cirkuits.cirkuitsapi.Verify;

public class Verify {
    private boolean active;
    private String email;

    public Verify(String email, boolean active) {
        this.email = email;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public String getEmail() {
        return email;
    }
}
