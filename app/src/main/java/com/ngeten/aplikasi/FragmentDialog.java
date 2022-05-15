package com.ngeten.aplikasi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Pesan.Pesan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class FragmentDialog extends DialogFragment implements View.OnClickListener {
    SessionManager sessionManager;
    SupportMapFragment supportMapFragment;
    ApiInterface apiInterface;
    TextView jumlah, treat, total, lat, lon;
    Button cancel, confirm;
    String sepatu, paket, totl,
            id_user,kd_paket, jml_sepatu, lati,longi;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        Bundle b = getArguments();
        sessionManager = new SessionManager(getActivity());

        jumlah = v.findViewById(R.id.jumlah);
        treat = v.findViewById(R.id.treat);
        total = v.findViewById(R.id.total);
        lat = v.findViewById(R.id.lat);
        lon = v.findViewById(R.id.lon);

        sepatu = "Jumlah Sepatu : " + b.getString("sepatu");
        paket = "Treatment : " + b.getString("paket");
        totl = "Total Harga : " + b.getString("totl");
        lati = b.getString("lat");
        longi = b.getString("lon");

        jumlah.setText(sepatu);
        treat.setText(paket);
        total.setText(totl);
        lat.setText(lati);
        lon.setText(longi);

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng latLng = new LatLng(Float.parseFloat(lati), Float.parseFloat(longi));
                googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Lokasi Jemput Disini kan?"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                // Zoom in, animating the camera.
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 60000, null);
            }
        });

        cancel = v.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        confirm = v.findViewById(R.id.confirm);
        confirm.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                getDialog().dismiss();
                break;
            case R.id.confirm:
                Bundle b = getArguments();
                id_user = sessionManager.getUserDetail().get(SessionManager.id);
                jml_sepatu = b.getString("sepatu");
                kd_paket = b.getString("kd_paket");
                lati = b.getString("lat");
                longi = b.getString("lon");
                pesan(id_user, jml_sepatu, kd_paket, lati, longi);
                reset();
                break;
        }
    }

    private void pesan(String id_user, String jml_sepatu, String kd_paket, String lati, String longi) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Pesan> call = apiInterface.pesanRespon(id_user, kd_paket, jml_sepatu, lati, longi);
        call.enqueue(new Callback<Pesan>() {
            @Override
            public void onResponse(Call<Pesan> call, Response<Pesan> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }else {
                    Toast.makeText(getContext(), response.body().getMessage()
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pesan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void reset() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}