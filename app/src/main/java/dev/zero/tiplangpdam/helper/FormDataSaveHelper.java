package dev.zero.tiplangpdam.helper;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import dev.zero.tiplangpdam.model.local.FormData;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.RequestBody;

public class FormDataSaveHelper {
    public static void addDataString (HashMap<String, String> dataSave){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FormData data = realm.createObject(FormData.class);
        Log.d("tatat", "addDataString: " + dataSave.get("catatan_stan_meter"));
        data.setHasil(dataSave.get("hasil"));
        data.setSPK(dataSave.get("spk"));
        data.setNo_batd(dataSave.get("no_batd"));
        data.setTanggal_batd(dataSave.get("tanggal_batd"));
        data.setNo_pelanggan(dataSave.get("no_pelanggan"));
        data.setNama_pelanggan(dataSave.get("nama_pelanggan"));
        data.setZona(dataSave.get("zona"));
        data.setJalan(dataSave.get("jalan"));
        data.setKondisi_stan_meter(dataSave.get("kondisi_stan_meter"));
        data.setCatatan_stan_meter(dataSave.get("catatan_stan_meter"));
        data.setTanggal_angkat(dataSave.get("tanggal_angkat"));
        data.setNo_meter(dataSave.get("no_meter"));
        data.setUkuran_meter(dataSave.get("ukuran_meter"));
        data.setAngka_angkat(dataSave.get("angka_angkat"));
        data.setMerk_meter(dataSave.get("merk_meter"));
        data.setBatd_id(dataSave.get("batd_id"));
        data.setPelanggaran(dataSave.get("pelanggaran_id"));
        realm.commitTransaction();
        realm.close();
    }
    public static void deleteDataPerNoPel(String noPel){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FormData data = realm.where(FormData.class)
                .equalTo("no_pelanggan", noPel).findFirst();
        data.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public static ArrayList<FormData> getData(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<FormData> data = realm.where(FormData.class).findAll();
        ArrayList<FormData> formData = new ArrayList<>();
        formData.addAll(data);
        return formData;
    }
}
