package dev.zero.tiplangpdam.model.response;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import dev.zero.tiplangpdam.model.List_Realisasi;

public class List_RealisasiResponse {
    private Integer code;
    private String description, messaage;
    private ArrayList<List_Realisasi> list;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessaage() {
        return messaage;
    }

    public ArrayList<List_Realisasi> getList() {
        return list;
    }
}
