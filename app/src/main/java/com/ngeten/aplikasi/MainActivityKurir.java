package com.ngeten.aplikasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityKurir extends AppCompatActivity {

    private static final String TAG = MainActivityKurir.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    protected Fragment fragment;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kurir);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_navigation_items_2);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_container, new FragmentJemput()).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.jemput:
                        fragment = new FragmentJemput();
                        break;
                    case R.id.antar:
                        fragment = new FragmentAntar();
                        break;
                    case R.id.konfig:
                        fragment = new FragmentKonfig();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

    }
}