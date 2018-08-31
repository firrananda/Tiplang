package dev.zero.tiplangpdam.model;

public class Realisasi {
    int id, status_baru, status_revisi;
    private String kondisi_stan_meter, catatan_stan_meter, no_meter,ukuran_meter, angka_angkat, merk_meter, hasil;
    private String batd_id, pelanggaran_id;
    private String tanggal_angkat, tanggal_realisasi;
    private String pict1, pict2, pict3, pict4;

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

    public int getStatus_revisi() { return status_revisi; }

    public String getAngka_angkat() { return angka_angkat; }

    public String getPict1() { return pict1; }

    public String getPict2() { return pict2; }

    public String getPict3() { return pict3; }

    public String getPict4() { return pict4; }
}
