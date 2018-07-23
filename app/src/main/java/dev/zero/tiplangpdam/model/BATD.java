package dev.zero.tiplangpdam.model;

public class BATD {
    private int id, spk_id, pelanggan_id;
    private String nomor_batd;
    private String tanggal_batd;

    public int getId(){ return id; }
    public int getSpk_id() {return spk_id;}
    public int getPelanggan_id() {return pelanggan_id;}
    public String getNomor_spk() {return  nomor_batd;}
    public String getTanggal_spk() {return tanggal_batd;}

    public String getNomor_batd() {
        return nomor_batd;
    }

    public String getTanggal_batd() {
        return tanggal_batd;
    }
}
