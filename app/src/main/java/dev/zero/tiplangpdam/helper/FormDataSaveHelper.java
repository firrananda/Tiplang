package dev.zero.tiplangpdam.helper;

import dev.zero.tiplangpdam.model.local.FormData;
import io.realm.Realm;

public class FormDataSaveHelper {
    public static void addDataString (String BATDId, String spk,String batd, String tglba, String noPelanggan, String namaPelanggan, String masukanalamat, String tglRealisasi, String hasilRealisasi, String pelanggaran, String keterangan, String kondisi, String catatan, String tglAngkat, String noMeter, String ukuranMeter, String angkaAngkat, String merkMeteran){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FormData data = realm.createObject(FormData.class);
        data.setBatdId(BATDId);
        data.setSpk(spk);
        data.setBatd(batd);
        data.setTglba(tglba);
        data.setNamaPelanggan(noPelanggan);
        data.setNamaPelanggan(namaPelanggan);
        data.setAlamat(masukanalamat);
        data.setTglRealisasi(tglRealisasi);
        data.setHasilRealisasi(hasilRealisasi);
        data.setPelanggaran(pelanggaran);
        data.setKeterangan(keterangan);
        data.setKondisi(kondisi);
        data.setCatatan(catatan);
        data.setTglAngkat(tglAngkat);
        data.setNoMeter(noMeter);
        data.setUkuranMeter(ukuranMeter);
        data.setAngkaAngkat(angkaAngkat);
        data.setMerkMeteran(merkMeteran);
        realm.commitTransaction();
        realm.close();
    }
    public static void deleteDataPerBATD(String batdId){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FormData data = realm.where(FormData.class)
                .equalTo("batdId", batdId).findFirst();
        data.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }
}
