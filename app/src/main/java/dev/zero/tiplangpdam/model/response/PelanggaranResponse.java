package dev.zero.tiplangpdam.model.response;

import java.util.ArrayList;

import dev.zero.tiplangpdam.model.Pelanggaran;

public class PelanggaranResponse {
    private Integer code;
    private String description;
    private String message;
    private ArrayList<Pelanggaran> data;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Pelanggaran> getData() {
        return data;
    }
}
