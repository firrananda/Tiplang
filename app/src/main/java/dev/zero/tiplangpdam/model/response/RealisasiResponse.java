package dev.zero.tiplangpdam.model.response;

import dev.zero.tiplangpdam.model.Realisasi;

public class RealisasiResponse {
    private Integer code;
    private String description;
    private String message;
    private Realisasi data;

    public Integer getCode(){
        return code;
    }
    public String getDescription(){
        return description;
    }

    public String getMessage() {
        return message;
    }

    public Realisasi getData() {
        return data;
    }
}
