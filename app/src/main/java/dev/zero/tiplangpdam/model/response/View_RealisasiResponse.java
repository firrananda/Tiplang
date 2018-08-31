package dev.zero.tiplangpdam.model.response;

import java.util.List;

import dev.zero.tiplangpdam.model.DataListRealisasi;

public class View_RealisasiResponse {
    private Integer code;
    private String description, message;
    private DataListRealisasi data;

    public DataListRealisasi getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

}
