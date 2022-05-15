package com.ngeten.aplikasi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Ubahkas.Ubahkas;
import com.ngeten.aplikasi.model.Ubahkur.Ubahkur;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentKonfig#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentKonfig extends Fragment implements View.OnClickListener {
    TextView id_user;
    EditText nama, no_hp, alamat, username, password;
    Button ubah, keluar;
    String id, name, hp, almt, user, pass;
    SessionManager sessionManager;
    ApiInterface apiInterface;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentKonfig() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentKonfig.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentKonfig newInstance(String param1, String param2) {
        FragmentKonfig fragment = new FragmentKonfig();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        sessionManager = new SessionManager(getActivity());

        id_user = v.findViewById(R.id.id_user);
        nama = v.findViewById(R.id.nama);
        no_hp = v.findViewById(R.id.no_hp);
        alamat = v.findViewById(R.id.alamat);
        username = v.findViewById(R.id.username);
        password = v.findViewById(R.id.password);

        id = sessionManager.getUserDetail().get(SessionManager.id);
        name = sessionManager.getUserDetail().get(SessionManager.nama);
        hp = sessionManager.getUserDetail().get(SessionManager.no_hp);
        almt = sessionManager.getUserDetail().get(SessionManager.alamat);
        user = sessionManager.getUserDetail().get(SessionManager.username);
        pass = sessionManager.getUserDetail().get(SessionManager.password);

        id_user.setText(id);
        nama.setText(name);
        no_hp.setText(hp);
        alamat.setText(almt);
        username.setText(user);
        password.setText(pass);

        ubah = v.findViewById(R.id.ubah);;
        ubah.setOnClickListener(this);
        keluar = v.findViewById(R.id.keluar);
        keluar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ubah:
                name=nama.getText().toString();
                hp=no_hp.getText().toString();
                almt=alamat.getText().toString();
                user=username.getText().toString();
                pass=password.getText().toString();
                ubah(id, name, hp, almt, user, pass);
                break;
            case R.id.keluar:
                sessionManager.logout();
                moveToLogin();
        }
    }

    private void ubah(String id, String name, String hp, String almt, String user, String pass) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Ubahkur> call = apiInterface.ubahKurRespon(id, name, hp, almt, user, pass);
        call.enqueue(new Callback<Ubahkur>() {
            @Override
            public void onResponse(Call<Ubahkur> call, Response<Ubahkur> response) {
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Ubahkur> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void moveToLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}