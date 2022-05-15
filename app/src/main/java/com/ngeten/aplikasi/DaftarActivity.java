package com.ngeten.aplikasi;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Daftar.Daftar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nama, no_hp, alamat, username, password;
    Button daftar, back;
    String name, hp, almt, user, pass;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        nama = (EditText) findViewById(R.id.nama);
        no_hp = (EditText) findViewById(R.id.no_hp);
        alamat = (EditText) findViewById(R.id.alamat);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        daftar= (Button) findViewById(R.id.daftar);
        daftar.setOnClickListener(this);
        back= (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.daftar:
                name=nama.getText().toString();
                hp=no_hp.getText().toString();
                almt=alamat.getText().toString();
                user=username.getText().toString();
                pass=password.getText().toString();
                register(name, hp, almt, user, pass);
                finish();
                break;
            case R.id.back:
                Intent i = new Intent(DaftarActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void register(String name, String hp, String almt, String user, String pass) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Daftar> call = apiInterface.daftarRespon(name, hp, almt, user, pass);
        call.enqueue(new Callback<Daftar>() {
            @Override
            public void onResponse(Call<Daftar> call, Response<Daftar> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    Toast.makeText(DaftarActivity.this, response.body().getMessage()
                            , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(DaftarActivity.this, response.body().getMessage()
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Daftar> call, Throwable t) {
                Toast.makeText(DaftarActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}