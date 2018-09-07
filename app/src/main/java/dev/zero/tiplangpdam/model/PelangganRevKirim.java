package dev.zero.tiplangpdam.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PelangganRevKirim implements Parcelable{
    private int id_batd, id_realisasi;
    private String nomor_batd, tanggal_batd, nomor_pelanggan, nama, zona, jalan;

    protected PelangganRevKirim(Parcel in) {
        id_batd = in.readInt();
        id_realisasi = in.readInt();
        nomor_batd = in.readString();
        tanggal_batd = in.readString();
        nomor_pelanggan = in.readString();
        nama = in.readString();
        zona = in.readString();
        jalan = in.readString();
    }

    public static final Parcelable.Creator<PelangganRevKirim> CREATOR = new Parcelable.Creator<PelangganRevKirim>() {
        @Override
        public PelangganRevKirim createFromParcel(Parcel in) {
            return new PelangganRevKirim(in);
        }

        @Override
        public PelangganRevKirim[] newArray(int size) {
            return new PelangganRevKirim[size];
        }
    };

    public int getId_batd() {
        return id_batd;
    }

    public int getId_realisasi() {
        return id_realisasi;
    }

    public String getNomor_batd() {
        return nomor_batd;
    }

    public String getTanggal_batd() {
        return tanggal_batd;
    }

    public String getNomor_pelanggan() {
        return nomor_pelanggan;
    }

    public String getNama() {
        return nama;
    }

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
        dest.writeInt(id_batd);
        dest.writeInt(id_realisasi);
        dest.writeString(nomor_batd);
        dest.writeString(tanggal_batd);
        dest.writeString(nomor_pelanggan);
        dest.writeString(nama);
        dest.writeString(zona);
        dest.writeString(jalan);
    }
}
