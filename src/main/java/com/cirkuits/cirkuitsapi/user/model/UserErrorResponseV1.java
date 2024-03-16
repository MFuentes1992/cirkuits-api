package com.cirkuits.cirkuitsapi.user.model;

public class UserErrorResponseV1 {
    private int code;
    private String message;

    public UserErrorResponseV1() {
    }

    public UserErrorResponseV1(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserErrorResponseV1{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
