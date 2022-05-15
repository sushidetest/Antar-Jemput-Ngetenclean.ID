package com.ngeten.aplikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ValidasiActivity extends AppCompatActivity {
    SessionManager sessionManager;
    ProgressBar load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi);
        load = findViewById(R.id.load);
        sessionManager = new SessionManager(ValidasiActivity.this);
        if (!sessionManager.getLogin()){
            new android.os.Handler().postDelayed(
                    ()->{moveToLogin();},
            3000);
        }else if (sessionManager.sudahLogin()){
            if (sessionManager.getUserDetail().get(SessionManager.level).equals("Pelanggan")){
                Toast.makeText(ValidasiActivity.this, "Hallo " + sessionManager.getUserDetail().get(SessionManager.level), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ValidasiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else if (sessionManager.getUserDetail().get(SessionManager.level).equals("Kasir")){
                Toast.makeText(ValidasiActivity.this, "Hallo " + sessionManager.getUserDetail().get(SessionManager.level), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ValidasiActivity.this, MainActivityKasir.class);
                startActivity(intent);
                finish();
            }else if (sessionManager.getUserDetail().get(SessionManager.level).equals("Kurir")){
                Toast.makeText(ValidasiActivity.this, "Hallo " + sessionManager.getUserDetail().get(SessionManager.level), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ValidasiActivity.this, MainActivityKurir.class);
                startActivity(intent);
                finish();
            }else{
                new android.os.Handler().postDelayed(
                        ()->{Toast.makeText(ValidasiActivity.this, "Tidak Menemukan Sesi", Toast.LENGTH_SHORT).show();
                            moveToLogin();},
                        3000);
            }
        }else{
            new android.os.Handler().postDelayed(
                    ()->{Toast.makeText(ValidasiActivity.this, "Tidak Menemukan Sesi", Toast.LENGTH_SHORT).show();
                        moveToLogin();},
                    3000);
        }
    }

    protected void moveToLogin() {
        Intent intent = new Intent(ValidasiActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}