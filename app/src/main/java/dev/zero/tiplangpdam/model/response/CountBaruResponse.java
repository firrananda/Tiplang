package dev.zero.tiplangpdam.model.response;

public class CountBaruResponse {
    private Integer code, baru, kirim;
    private String description, message;

    public Integer getCode() {
        return code;
    }

    public Integer getBaru() { return baru; }

    public Integer getKirim() {
        return kirim;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
