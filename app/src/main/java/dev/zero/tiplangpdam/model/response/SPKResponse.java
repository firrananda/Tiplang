package dev.zero.tiplangpdam.model.response;

import java.util.ArrayList;

import dev.zero.tiplangpdam.model.SPK;

public class SPKResponse {
    private Integer code;
    private String description;
    private String message;
    private ArrayList<SPK> data;

    public Integer getCode(){
        return code;
    }
    public String getDescription(){
        return description;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<SPK> getDataSPK() {
        return data;
    }
}
