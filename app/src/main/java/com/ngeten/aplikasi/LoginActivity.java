package com.ngeten.aplikasi;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.model.Login.DataLogin;
import com.ngeten.aplikasi.model.Login.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button login;
    String user,pass;
    TextView daftar;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(LoginActivity.this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        daftar = findViewById(R.id.daftar);
        daftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                user = username.getText().toString();
                pass = password.getText().toString();
                login(user, pass);
                break;
            case R.id.daftar:
                Intent intent = new Intent(this, DaftarActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login(String user, String pass) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginRespon(user, pass);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    DataLogin dataLogin = response.body().getData();
                    sessionManager.createLoginSession(dataLogin);
                    Intent intent = new Intent(LoginActivity.this, ValidasiActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage()
                            , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}