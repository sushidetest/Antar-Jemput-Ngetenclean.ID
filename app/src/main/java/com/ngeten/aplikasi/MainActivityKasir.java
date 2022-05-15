package com.ngeten.aplikasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityKasir extends AppCompatActivity {

    private static final String TAG = MainActivityKasir.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    protected Fragment fragment;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kasir);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_navigation_items_1);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_container, new FragmentCekPesanan()).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.cek_pesanan:
                        fragment = new FragmentCekPesanan();
                        break;
                    case R.id.laporan_transaksi:
                        fragment = new FragmentLaporanTransaksi();
                        break;
                    case R.id.konfigurasi:
                        fragment = new FragmentKonfigurasi();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
    }
}