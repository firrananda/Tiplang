package dev.zero.tiplangpdam.service;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "UserPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String NAMA = "nama";
    private static final String NIP = "nip";
    private static final String JABATAN = "jabatan";
    public static final String KEY_ID = "id";

    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(int id, String nama, int nip, String jabatan){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(NAMA, nama);
        editor.putString(NIP, String.valueOf(nip));
        editor.putString(JABATAN, jabatan);
        editor.putString(KEY_ID, String.valueOf(id));
        editor.commit();
    }

    public boolean checkLogin(){
        if (!this.isLoggedIn()){
            return false;
        }
        return true;
    }

    public String getKeyId() {
        return pref.getString(KEY_ID,"");
    }

    public String getNama() {
        return pref.getString(NAMA,"");
    }

    public String getNip() {
        return pref.getString(NIP,"");
    }

    public String getJabatan() {
        return pref.getString(JABATAN,"");
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn()
    {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
