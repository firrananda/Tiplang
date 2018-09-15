package dev.zero.tiplangpdam.model.response;

import dev.zero.tiplangpdam.model.ViewRealRevKirim;

public class ViewRealRevKirimResponse {
    private Integer code;
    private String description, message;
    private ViewRealRevKirim data;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public ViewRealRevKirim getData() {
        return data;
    }
}
