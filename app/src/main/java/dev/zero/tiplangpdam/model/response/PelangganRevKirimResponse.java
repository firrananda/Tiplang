package dev.zero.tiplangpdam.model.response;

import java.util.ArrayList;

import dev.zero.tiplangpdam.model.PelangganRevKirim;

public class PelangganRevKirimResponse {

    private Integer code;
    private String description, message;
    private ArrayList<PelangganRevKirim> list;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<PelangganRevKirim> getList() {
        return list;
    }
}
