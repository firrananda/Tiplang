package dev.zero.tiplangpdam.model.response;

import java.util.ArrayList;

import dev.zero.tiplangpdam.model.BATD;

public class BATDResponse {
    private Integer code;
    private String description;
    private String message;
    private ArrayList<BATD> data;

    public Integer getCode(){
        return code;
    }
    public String getDescription(){
        return description;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<BATD> getData() {
        return data;
    }
}
