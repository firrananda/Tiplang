package dev.zero.tiplangpdam.model.response;

import java.util.ArrayList;

import dev.zero.tiplangpdam.model.PelangganRev;

public class PelangganRevResponse {
    private Integer code;
    private String description, messaage;
    private ArrayList<PelangganRev> list;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessaage() {
        return messaage;
    }

    public ArrayList<PelangganRev> getList() {
        return list;
    }
}
