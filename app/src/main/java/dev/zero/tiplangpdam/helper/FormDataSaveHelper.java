package dev.zero.tiplangpdam.helper;

import java.util.ArrayList;
import java.util.HashMap;

import dev.zero.tiplangpdam.model.local.FormData;
import io.realm.Realm;
import okhttp3.RequestBody;

public class FormDataSaveHelper {
    public static void addDataString (HashMap<String, String> dataSave){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FormData data = realm.createObject(FormData.class);
        data.setKondisi_stan_meter(dataSave.get("kondisi_stan_meter"));
        data.setCatatan_stan_meter(dataSave.get("catatan_stan_meter"));
        data.setTanggal_angkat(dataSave.get("tanggal_angkat"));
        data.setNo_meter(dataSave.get("no_meter"));
        data.setUkuran_meter(dataSave.get("ukuran_meter"));
        data.setAngka_angkat(dataSave.get("angka_angkat"));
        data.setMerk_meter(dataSave.get("merk_meter"));
        data.setBatd_id(dataSave.get("batd_id"));
        data.setPelanggaran(dataSave.get("pelanggaran"));
        realm.commitTransaction();
        realm.close();
    }
    public static void deleteDataPerBATD(String batdId){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FormData data = realm.where(FormData.class)
                .equalTo("batd_id", batdId).findFirst();
        data.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }
}
