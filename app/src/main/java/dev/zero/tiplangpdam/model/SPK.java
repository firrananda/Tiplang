package dev.zero.tiplangpdam.model;


public class SPK {
    private int id, petugas_id;
    private String nomor_spk;
    private String tanggal_spk;

    public SPK(int id, int petugas_id, String nomor_spk, String tanggal_spk) {
        this.id = id;
        this.petugas_id = petugas_id;
        this.nomor_spk = nomor_spk;
        this.tanggal_spk = tanggal_spk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetugas_id() {
        return petugas_id;
    }

    public void setPetugas_id(int petugas_id) {
        this.petugas_id = petugas_id;
    }

    public String getNomor_spk() {
        return nomor_spk;
    }

    public void setNomor_spk(String nomor_spk) {
        this.nomor_spk = nomor_spk;
    }

    public String getTanggal_spk() {
        return tanggal_spk;
    }

    public void setTanggal_spk(String tanggal_spk) {
        this.tanggal_spk = tanggal_spk;
    }
}
