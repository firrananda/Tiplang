package dev.zero.tiplangpdam.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pelanggan implements Parcelable {
    private int spk_id, batd_id, pelanggan_id;
    private String nomor_spk, nomor_batd, tanggal_batd, nomor_pelanggan, nama_pelanggan, zona, jalan;

    public int getSpk_id() {
        return spk_id;
    }

    public int getBatd_id() {
        return batd_id;
    }

    public int getPelanggan_id() {
        return pelanggan_id;
    }

    public String getNomor_spk() {
        return nomor_spk;
    }

    public String getNomor_batd() {
        return nomor_batd;
    }

    public String getTanggal_batd() { return tanggal_batd; }

    public String getNomor_pelanggan() {
        return nomor_pelanggan;
    }

    public String getNama_pelanggan() { return nama_pelanggan; }

    public String getZona() {
        return zona;
    }

    public String getJalan() {
        return jalan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.spk_id);
        dest.writeInt(this.batd_id);
        dest.writeInt(this.pelanggan_id);
        dest.writeString(this.nomor_spk);
        dest.writeString(this.nomor_batd);
        dest.writeString(this.tanggal_batd);
        dest.writeString(this.nomor_pelanggan);
        dest.writeString(this.nama_pelanggan);
        dest.writeString(this.zona);
        dest.writeString(this.jalan);
    }

    public Pelanggan() {
    }

    protected Pelanggan(Parcel in) {
        this.spk_id = in.readInt();
        this.batd_id = in.readInt();
        this.pelanggan_id = in.readInt();
        this.nomor_spk = in.readString();
        this.nomor_batd = in.readString();
        this.tanggal_batd = in.readString();
        this.nomor_pelanggan = in.readString();
        this.nama_pelanggan = in.readString();
        this.zona = in.readString();
        this.jalan = in.readString();
    }

    public static final Parcelable.Creator<Pelanggan> CREATOR = new Parcelable.Creator<Pelanggan>() {
        @Override
        public Pelanggan createFromParcel(Parcel source) {
            return new Pelanggan(source);
        }

        @Override
        public Pelanggan[] newArray(int size) {
            return new Pelanggan[size];
        }
    };
}
