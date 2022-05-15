package com.ngeten.aplikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Insertka.Pekas;
import com.ngeten.aplikasi.model.Insertku.Pekur;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nama, no_hp, alamat, username, password;
    Spinner job;
    Button daftar, back;
    String name, hp, almt, user, pass, level;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        nama = (EditText) findViewById(R.id.nama);
        no_hp = (EditText) findViewById(R.id.no_hp);
        alamat = (EditText) findViewById(R.id.alamat);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        job = (Spinner) findViewById(R.id.level);
        daftar= (Button) findViewById(R.id.daftar);
        daftar.setOnClickListener(this);
        back= (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.daftar:
                if (job.getSelectedItem().equals("Kasir")){
                    name = nama.getText().toString();
                    hp = no_hp.getText().toString();
                    almt = alamat.getText().toString();
                    user = username.getText().toString();
                    pass = password.getText().toString();
                    level = String.valueOf(job.getSelectedItem());
                    registerKas(name, hp, almt, user, pass, level);
                }else{
                    name = nama.getText().toString();
                    hp = no_hp.getText().toString();
                    almt = alamat.getText().toString();
                    user = username.getText().toString();
                    pass = password.getText().toString();
                    level = String.valueOf(job.getSelectedItem());
                    registerKur(name, hp, almt, user, pass, level);
                }
                finish();
                break;
            case R.id.back:
                Intent intent = new Intent(this, ValidasiActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void registerKur(String name, String hp, String almt, String user, String pass, String level) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Pekur> call = apiInterface.pekurRespon(name, hp, almt, user, pass, level);
        call.enqueue(new Callback<Pekur>() {
            @Override
            public void onResponse(Call<Pekur> call, Response<Pekur> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    Toast.makeText(TambahActivity.this, response.body().getMessage()
                            , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahActivity.this, ValidasiActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(TambahActivity.this, response.body().getMessage()
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pekur> call, Throwable t) {
                Toast.makeText(TambahActivity.this, t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerKas(String name, String hp, String almt, String user, String pass, String level) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Pekas> call = apiInterface.pekasRespon(name, hp, almt, user, pass, level);
        call.enqueue(new Callback<Pekas>() {
            @Override
            public void onResponse(Call<Pekas> call, Response<Pekas> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    Toast.makeText(TambahActivity.this, response.body().getMessage()
                            , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahActivity.this, ValidasiActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(TambahActivity.this, response.body().getMessage()
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pekas> call, Throwable t) {
                Toast.makeText(TambahActivity.this, t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }
}