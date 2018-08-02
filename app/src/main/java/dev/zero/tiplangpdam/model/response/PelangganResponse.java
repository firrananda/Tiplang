package dev.zero.tiplangpdam.model.response;

import java.util.ArrayList;

import dev.zero.tiplangpdam.model.Pelanggan;

public class PelangganResponse {
    private Integer code;
    private String description, message;
    private ArrayList <Pelanggan> data;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Pelanggan> getData() {
        return data;
    }
}
