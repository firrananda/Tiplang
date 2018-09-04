package dev.zero.tiplangpdam.model.response;

import dev.zero.tiplangpdam.model.ViewRealRevBaru;

public class ViewRealRevBaruResponse {
    private Integer code;
    private String description, message;
    private ViewRealRevBaru data;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public ViewRealRevBaru getData() {
        return data;
    }
}
