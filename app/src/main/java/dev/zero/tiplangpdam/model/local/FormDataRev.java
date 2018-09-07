package dev.zero.tiplangpdam.model.local;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class FormDataRev extends RealmObject implements Parcelable {
    private String no_batd;
    private String tanggal_batd;
    private String no_pelanggan;
    private String nama_pelanggan;
    private String zona;
    private String jalan;
    private String ket_realisasi;
    private String hasil;
    private String kondisi_stan_meter;
    private String catatan_stan_meter;
    private String tanggal_angkat;
    private String no_meter;
    private String ukuran_meter;
    private String angka_angkat;
    private String merk_meter;
    private String batd_id;
    private String pelanggaran;

    public FormDataRev() {

    }

    protected FormDataRev(Parcel in) {
        no_batd = in.readString();
        tanggal_batd = in.readString();
        no_pelanggan = in.readString();
        nama_pelanggan = in.readString();
        zona = in.readString();
        jalan = in.readString();
        ket_realisasi = in.readString();
        hasil = in.readString();
        kondisi_stan_meter = in.readString();
        catatan_stan_meter = in.readString();
        tanggal_angkat = in.readString();
        no_meter = in.readString();
        ukuran_meter = in.readString();
        angka_angkat = in.readString();
        merk_meter = in.readString();
        batd_id = in.readString();
        pelanggaran = in.readString();
    }

    public static final Parcelable.Creator<FormDataRev> CREATOR = new Parcelable.Creator<FormDataRev>() {
        @Override
        public FormDataRev createFromParcel(Parcel in) {
            return new FormDataRev(in);
        }

        @Override
        public FormDataRev[] newArray(int size) {
            return new FormDataRev[size];
        }
    };

    public String getNo_batd() {
        return no_batd;
    }

    public void setNo_batd(String no_batd) {
        this.no_batd = no_batd;
    }

    public String getTanggal_batd() {
        return tanggal_batd;
    }

    public void setTanggal_batd(String tanggal_batd) {
        this.tanggal_batd = tanggal_batd;
    }

    public String getNo_pelanggan() {
        return no_pelanggan;
    }

    public void setNo_pelanggan(String no_pelanggan) {
        this.no_pelanggan = no_pelanggan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getKondisi_stan_meter() {
        return kondisi_stan_meter;
    }

    public void setKondisi_stan_meter(String kondisi_stan_meter) {
        this.kondisi_stan_meter = kondisi_stan_meter;
    }

    public String getCatatan_stan_meter() {
        return catatan_stan_meter;
    }

    public void setCatatan_stan_meter(String catatan_stan_meter) {
        this.catatan_stan_meter = catatan_stan_meter;
    }

    public String getTanggal_angkat() {
        return tanggal_angkat;
    }

    public void setTanggal_angkat(String tanggal_angkat) {
        this.tanggal_angkat = tanggal_angkat;
    }

    public String getNo_meter() {
        return no_meter;
    }

    public void setNo_meter(String no_meter) {
        this.no_meter = no_meter;
    }

    public String getUkuran_meter() {
        return ukuran_meter;
    }

    public void setUkuran_meter(String ukuran_meter) {
        this.ukuran_meter = ukuran_meter;
    }

    public String getAngka_angkat() {
        return angka_angkat;
    }

    public void setAngka_angkat(String angka_angkat) {
        this.angka_angkat = angka_angkat;
    }

    public String getMerk_meter() {
        return merk_meter;
    }

    public void setMerk_meter(String merk_meter) {
        this.merk_meter = merk_meter;
    }

    public String getBatd_id() {
        return batd_id;
    }

    public void setBatd_id(String batd_id) {
        this.batd_id = batd_id;
    }

    public String getPelanggaran() {
        return pelanggaran;
    }

    public void setPelanggaran(String pelanggaran) {
        this.pelanggaran = pelanggaran;
    }

    public String getKet_realisasi() {
        return ket_realisasi;
    }

    public void setKet_realisasi(String ket_realisasi) {
        this.ket_realisasi = ket_realisasi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(no_batd);
        dest.writeString(tanggal_batd);
        dest.writeString(no_pelanggan);
        dest.writeString(nama_pelanggan);
        dest.writeString(zona);
        dest.writeString(jalan);
        dest.writeString(ket_realisasi);
        dest.writeString(hasil);
        dest.writeString(kondisi_stan_meter);
        dest.writeString(catatan_stan_meter);
        dest.writeString(tanggal_angkat);
        dest.writeString(no_meter);
        dest.writeString(ukuran_meter);
        dest.writeString(angka_angkat);
        dest.writeString(merk_meter);
        dest.writeString(batd_id);
        dest.writeString(pelanggaran);
    }
}
