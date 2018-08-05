package dev.zero.tiplangpdam.model;

import java.util.Date;

public class PelangganData {
    int id, spk_id, pelanggan_id;
    Date tanggal_batd;
    String nomor_batd;

    public int getId() {
        return id;
    }

    public int getSpk_id() {
        return spk_id;
    }

    public int getPelanggan_id() {
        return pelanggan_id;
    }

    public Date getTanggal_batd() {
        return tanggal_batd;
    }

    public String getNomor_batd() {
        return nomor_batd;
    }
}
