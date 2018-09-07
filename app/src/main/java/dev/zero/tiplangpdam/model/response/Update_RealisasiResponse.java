package dev.zero.tiplangpdam.model.response;

import dev.zero.tiplangpdam.model.Update_Realisasi;

public class Update_RealisasiResponse {
    private Integer code;
    private String description, message;
    private Update_Realisasi data;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public Update_Realisasi getData() {
        return data;
    }
}
