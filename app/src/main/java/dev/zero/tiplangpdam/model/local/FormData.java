package dev.zero.tiplangpdam.model.local;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class FormData extends RealmObject implements Parcelable {
    private String batdId;
    private String spk;
    private String batd;
    private String tglba;
    private String noPelanggan;
    private String namaPelanggan;
    private String alamat;
    private String tglRealisasi;
    private String hasilRealisasi;
    private String pelanggaran;
    private String keterangan;
    private String kondisi;
    private String catatan;
    private String tglAngkat;
    private String noMeter;
    private String ukuranMeter;
    private String angkaAngkat;
    private String merkMeteran;

    public FormData() {}

    public FormData(String batdId, String spk, String batd, String tglba, String noPelanggan, String namaPelanggan, String alamat, String tglRealisasi, String hasilRealisasi, String pelanggaran, String keterangan, String kondisi, String catatan, String tglAngkat, String noMeter, String ukuranMeter, String angkaAngkat, String merkMeteran) {
        this.batdId = batdId;
        this.spk = spk;
        this.batd = batd;
        this.tglba = tglba;
        this.noPelanggan = noPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.alamat = alamat;
        this.tglRealisasi = tglRealisasi;
        this.hasilRealisasi = hasilRealisasi;
        this.pelanggaran = pelanggaran;
        this.keterangan = keterangan;
        this.kondisi = kondisi;
        this.catatan = catatan;
        this.tglAngkat = tglAngkat;
        this.noMeter = noMeter;
        this.ukuranMeter = ukuranMeter;
        this.angkaAngkat = angkaAngkat;
        this.merkMeteran = merkMeteran;
    }

    public String getBatdId() {
        return batdId;
    }

    public void setBatdId(String batdId) {
        this.batdId = batdId;
    }

    public String getSpk() {
        return spk;
    }

    public void setSpk(String spk) {
        this.spk = spk;
    }

    public String getBatd() {
        return batd;
    }

    public void setBatd(String batd) {
        this.batd = batd;
    }

    public String getTglba() {
        return tglba;
    }

    public void setTglba(String tglba) {
        this.tglba = tglba;
    }

    public String getNoPelanggan() {
        return noPelanggan;
    }

    public void setNoPelanggan(String noPelanggan) {
        this.noPelanggan = noPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTglRealisasi() {
        return tglRealisasi;
    }

    public void setTglRealisasi(String tglRealisasi) {
        this.tglRealisasi = tglRealisasi;
    }

    public String getHasilRealisasi() {
        return hasilRealisasi;
    }

    public void setHasilRealisasi(String hasilRealisasi) {
        this.hasilRealisasi = hasilRealisasi;
    }

    public String getPelanggaran() {
        return pelanggaran;
    }

    public void setPelanggaran(String pelanggaran) {
        this.pelanggaran = pelanggaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTglAngkat() {
        return tglAngkat;
    }

    public void setTglAngkat(String tglAngkat) {
        this.tglAngkat = tglAngkat;
    }

    public String getNoMeter() {
        return noMeter;
    }

    public void setNoMeter(String noMeter) {
        this.noMeter = noMeter;
    }

    public String getUkuranMeter() {
        return ukuranMeter;
    }

    public void setUkuranMeter(String ukuranMeter) {
        this.ukuranMeter = ukuranMeter;
    }

    public String getAngkaAngkat() {
        return angkaAngkat;
    }

    public void setAngkaAngkat(String angkaAngkat) {
        this.angkaAngkat = angkaAngkat;
    }

    public String getMerkMeteran() {
        return merkMeteran;
    }

    public void setMerkMeteran(String merkMeteran) {
        this.merkMeteran = merkMeteran;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.batdId);
        dest.writeString(this.spk);
        dest.writeString(this.batd);
        dest.writeString(this.tglba);
        dest.writeString(this.noPelanggan);
        dest.writeString(this.namaPelanggan);
        dest.writeString(this.alamat);
        dest.writeString(this.tglRealisasi);
        dest.writeString(this.hasilRealisasi);
        dest.writeString(this.pelanggaran);
        dest.writeString(this.keterangan);
        dest.writeString(this.kondisi);
        dest.writeString(this.catatan);
        dest.writeString(this.tglAngkat);
        dest.writeString(this.noMeter);
        dest.writeString(this.ukuranMeter);
        dest.writeString(this.angkaAngkat);
        dest.writeString(this.merkMeteran);
    }

    protected FormData(Parcel in) {
        this.batdId = in.readString();
        this.spk = in.readString();
        this.batd = in.readString();
        this.tglba = in.readString();
        this.noPelanggan = in.readString();
        this.namaPelanggan = in.readString();
        this.alamat = in.readString();
        this.tglRealisasi = in.readString();
        this.hasilRealisasi = in.readString();
        this.pelanggaran = in.readString();
        this.keterangan = in.readString();
        this.kondisi = in.readString();
        this.catatan = in.readString();
        this.tglAngkat = in.readString();
        this.noMeter = in.readString();
        this.ukuranMeter = in.readString();
        this.angkaAngkat = in.readString();
        this.merkMeteran = in.readString();
    }

    public static final Parcelable.Creator<FormData> CREATOR = new Parcelable.Creator<FormData>() {
        @Override
        public FormData createFromParcel(Parcel source) {
            return new FormData(source);
        }

        @Override
        public FormData[] newArray(int size) {
            return new FormData[size];
        }
    };
}
