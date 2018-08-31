package dev.zero.tiplangpdam.model;

import android.os.Parcel;
import android.os.Parcelable;

public class List_Realisasi implements Parcelable {
    private int id;
    private String nomor_batd, tanggal_batd, nomor_pelanggan, nama, jalan, id_realisasi , zona;

    public int getId() {
        return id;
    }

    public String getNomor_batd() {
        return nomor_batd;
    }

    public String getZona() {return zona; }

    public String getTanggal_batd() {
        return tanggal_batd;
    }

    public String getNomor_pelanggan() {
        return nomor_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public String getJalan() {
        return jalan;
    }

    public String getId_realisasi() { return id_realisasi; }

    protected List_Realisasi(Parcel in) {
        id = in.readInt();
        nomor_batd = in.readString();
        tanggal_batd = in.readString();
        nomor_pelanggan = in.readString();
        nama = in.readString();
        zona = in.readString();
        jalan = in.readString();
        id_realisasi = in.readString();
    }

    public static final Parcelable.Creator<List_Realisasi> CREATOR = new Parcelable.Creator<List_Realisasi>() {
        @Override
        public List_Realisasi createFromParcel(Parcel in) {
            return new List_Realisasi(in);
        }

        @Override
        public List_Realisasi[] newArray(int size) {
            return new List_Realisasi[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomor_batd);
        dest.writeString(tanggal_batd);
        dest.writeString(nomor_pelanggan);
        dest.writeString(nama);
        dest.writeString(zona);
        dest.writeString(jalan);
        dest.writeString(id_realisasi);
    }
}
