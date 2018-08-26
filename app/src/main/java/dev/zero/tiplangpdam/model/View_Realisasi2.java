package dev.zero.tiplangpdam.model;

public class View_Realisasi2 {
    private int id, no_meter, angka_angkat, batd_id, pelanggaran_id, status_baru, status_revisi;
    private String tanggal_realisasi, hasil, kondisi_stan_meter, catatan_stan_meter, tangal_angkat, ukuran_meter, merk_meter, ket_realisasi;

    public String getKet_realisasi() {
        return ket_realisasi;
    }

    public int getId() {
        return id;
    }

    public int getNo_meter() {
        return no_meter;
    }

    public int getAngka_angkat() {
        return angka_angkat;
    }

    public int getBatd_id() {
        return batd_id;
    }

    public int getPelanggaran_id() {
        return pelanggaran_id;
    }

    public int getStatus_baru() {
        return status_baru;
    }

    public int getStatus_revisi() {
        return status_revisi;
    }

    public String getTanggal_realisasi() {
        return tanggal_realisasi;
    }

    public String getHasil() {
        return hasil;
    }

    public String getKondisi_stan_meter() {
        return kondisi_stan_meter;
    }

    public String getCatatan_stan_meter() {
        return catatan_stan_meter;
    }

    public String getTangal_angkat() {
        return tangal_angkat;
    }

    public String getUkuran_meter() {
        return ukuran_meter;
    }

    public String getMerk_meter() {
        return merk_meter;
    }
}
