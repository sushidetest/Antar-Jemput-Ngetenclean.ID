package com.ngeten.aplikasi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ngeten.aplikasi.model.Login.DataLogin;

import java.util.HashMap;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String loggedin = "isLoggedIn";
    public static final String id = "id";
    public static final String nama = "nama";
    public static final String no_hp = "no_hp";
    public static final String alamat = "alamat";
    public static final String username = "username";
    public static final String password = "password";
    public static final String level = "level";

    public SessionManager (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession (DataLogin user){
        editor.putBoolean(loggedin, true);
        editor.putString(id, user.getId());
        editor.putString(nama, user.getNama());
        editor.putString(no_hp, user.getNoHp());
        editor.putString(alamat, user.getAlamat());
        editor.putString(username, user.getUsername());
        editor.putString(password,user.getPassword());
        editor.putString(level, user.getLevel());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(id, sharedPreferences.getString(id, null));
        user.put(nama, sharedPreferences.getString(nama, null));
        user.put(no_hp, sharedPreferences.getString(no_hp, null));
        user.put(alamat, sharedPreferences.getString(alamat, null));
        user.put(username, sharedPreferences.getString(username, null));
        user.put(password, sharedPreferences.getString(password, null));
        user.put(level, sharedPreferences.getString(level, null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean(loggedin, false);
    }

    public boolean sudahLogin(){
        return sharedPreferences.getBoolean(loggedin, true);
    }
}
