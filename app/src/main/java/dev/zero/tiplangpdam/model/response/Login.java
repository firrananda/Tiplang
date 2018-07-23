package dev.zero.tiplangpdam.model.response;

import dev.zero.tiplangpdam.model.User;

public class Login {
    private Integer code;
    private String description;
    private String message;
    private User data;

    public Login(Integer code, String description, String message, User data) {
        this.code = code;
        this.description = description;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
