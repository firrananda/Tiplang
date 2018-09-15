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

    public FormDataRev(String no_batd, String tanggal_batd, String no_pelanggan, String nama_pelanggan, String zona, String jalan, String ket_realisasi, String hasil, String kondisi_stan_meter, String catatan_stan_meter, String tanggal_angkat, String no_meter, String ukuran_meter, String angka_angkat, String merk_meter, String batd_id, String pelanggaran) {

        this.no_batd = no_batd;
        this.tanggal_batd = tanggal_batd;
        this.no_pelanggan = no_pelanggan;
        this.nama_pelanggan = nama_pelanggan;
        this.zona = zona;
        this.jalan = jalan;
        this.ket_realisasi = ket_realisasi;
        this.hasil = hasil;
        this.kondisi_stan_meter = kondisi_stan_meter;
        this.catatan_stan_meter = catatan_stan_meter;
        this.tanggal_angkat = tanggal_angkat;
        this.no_meter = no_meter;
        this.ukuran_meter = ukuran_meter;
        this.angka_angkat = angka_angkat;
        this.merk_meter = merk_meter;
        this.batd_id = batd_id;
        this.pelanggaran = pelanggaran;
    }

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

    public String getKet_realisasi() {
        return ket_realisasi;
    }

    public void setKet_realisasi(String ket_realisasi) {
        this.ket_realisasi = ket_realisasi;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.no_batd);
        dest.writeString(this.tanggal_batd);
        dest.writeString(this.no_pelanggan);
        dest.writeString(this.nama_pelanggan);
        dest.writeString(this.zona);
        dest.writeString(this.jalan);
        dest.writeString(this.ket_realisasi);
        dest.writeString(this.hasil);
        dest.writeString(this.kondisi_stan_meter);
        dest.writeString(this.catatan_stan_meter);
        dest.writeString(this.tanggal_angkat);
        dest.writeString(this.no_meter);
        dest.writeString(this.ukuran_meter);
        dest.writeString(this.angka_angkat);
        dest.writeString(this.merk_meter);
        dest.writeString(this.batd_id);
        dest.writeString(this.pelanggaran);
    }

    protected FormDataRev(Parcel in) {
        this.no_batd = in.readString();
        this.tanggal_batd = in.readString();
        this.no_pelanggan = in.readString();
        this.nama_pelanggan = in.readString();
        this.zona = in.readString();
        this.jalan = in.readString();
        this.ket_realisasi = in.readString();
        this.hasil = in.readString();
        this.kondisi_stan_meter = in.readString();
        this.catatan_stan_meter = in.readString();
        this.tanggal_angkat = in.readString();
        this.no_meter = in.readString();
        this.ukuran_meter = in.readString();
        this.angka_angkat = in.readString();
        this.merk_meter = in.readString();
        this.batd_id = in.readString();
        this.pelanggaran = in.readString();
    }

    public static final Creator<FormDataRev> CREATOR = new Creator<FormDataRev>() {
        @Override
        public FormDataRev createFromParcel(Parcel source) {
            return new FormDataRev(source);
        }

        @Override
        public FormDataRev[] newArray(int size) {
            return new FormDataRev[size];
        }
    };
}
