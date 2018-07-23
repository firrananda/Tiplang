package dev.zero.tiplangpdam.model;

public class Realisasi {
    int id, status_baru;
    private String kondisi_stan_meter, catatan_stan_meter, no_meter,ukuran_meter, merk_meter, hasil;
    private String batd_id, pelanggaran_id;
    private String tanggal_angkat, tanggal_realisasi;

    public int getId() {
        return id;
    }

    public int getStatus_baru() { return status_baru; }

    public String getKondisi_stan_meter() {
        return kondisi_stan_meter;
    }

    public String getCatatan_stan_meter() {
        return catatan_stan_meter;
    }

    public String getNo_meter() {
        return no_meter;
    }

    public String getUkuran_meter() {
        return ukuran_meter;
    }

    public String getMerk_meter() { return merk_meter; }

    public String getHasil() { return hasil; }

    public String getBatd_id() { return batd_id; }

    public String getPelanggaran_id() {
        return pelanggaran_id;
    }

    public String getTanggal_angkat() {
        return tanggal_angkat;
    }

    public String getTanggal_realisasi() {
        return tanggal_realisasi;
    }
}
