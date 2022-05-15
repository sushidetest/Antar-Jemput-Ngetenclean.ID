package com.ngeten.aplikasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsetsAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.directionhelpers.FetchURL;
import com.ngeten.aplikasi.directionhelpers.TaskLoadedCallback;
import com.ngeten.aplikasi.model.Bayar.Bayar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilManNav extends AppCompatActivity implements View.OnClickListener, TaskLoadedCallback {
    GoogleMap mMap;
    Location loc;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    MarkerOptions kurs, pels;
    Polyline polyline;
    Intent intent;
    public static final int REQUEST_CODE = 101;
    TextView kd_trans, nama, alamat, no_hp, nama_paket, tgl_pesan, status, status_bayar, kd_sepatu, kurir, harga, jml_sepatu, total;
    Button bayar, selesai, rute, back;
    String vkd_trans, vnama, valamat, vno_hp, vnama_paket, vtgl_pesan, vstatus, vstatus_bayar, vkd_sepatu, vkurir, vharga, vjml_sepatu, vtotal, lati,longi;
    String cash="Cash", transfer="Transfer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_man_nav);

        Intent terima = getIntent();

        vkd_trans = terima.getStringExtra("kd_trans");
        vnama = terima.getStringExtra("nama");
        valamat = terima.getStringExtra("alamat");
        vno_hp = terima.getStringExtra("no_hp");
        vnama_paket = terima.getStringExtra("treatment");
        vtgl_pesan = terima.getStringExtra("tgl");
        vstatus = terima.getStringExtra("status");
        vstatus_bayar = terima.getStringExtra("status_bayar");
        vkd_sepatu = terima.getStringExtra("kd_sepatu");
        vkurir = terima.getStringExtra("nama_kurir");
        vharga = terima.getStringExtra("harga");
        vjml_sepatu = terima.getStringExtra("jml_sepatu");
        vtotal = terima.getStringExtra("total");
        lati = terima.getStringExtra("lati");
        longi = terima.getStringExtra("longi");

        kd_trans = findViewById(R.id.kd);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        no_hp = findViewById(R.id.no_hp);
        nama_paket = findViewById(R.id.paket);
        tgl_pesan = findViewById(R.id.tgl_pesan);
        status = findViewById(R.id.status);
        status_bayar = findViewById(R.id.status_bayar);
        kd_sepatu = findViewById(R.id.kd_sepatu);
        kurir = findViewById(R.id.kurir);
        harga = findViewById(R.id.biaya);
        jml_sepatu = findViewById(R.id.jml_sepatu);
        total = findViewById(R.id.total);

        kd_trans.setText("Kode Transaksi : "+vkd_trans);
        nama.setText("Nama Pelanggan : "+vnama);
        alamat.setText("Alamat : "+valamat);
        no_hp.setText("Nomor_HP : "+vno_hp);
        nama_paket.setText("Treatment : "+vnama_paket);
        tgl_pesan.setText("Tanggal Pesan"+vtgl_pesan);
        status.setText("Status : "+vstatus);
        status_bayar.setText("Status Bayar : "+vstatus_bayar);
        kd_sepatu.setText("Kode Speatu : "+vkd_sepatu);
        kurir.setText("Nama Kurir : "+vkurir);
        harga.setText("Biaya Treatment : "+vharga);
        jml_sepatu.setText("Jumlah Sepatu : "+vjml_sepatu);
        total.setText("Total Biaya : "+vtotal);

        client = LocationServices.getFusedLocationProviderClient(TampilManNav.this);
        fetchLastLoc();

        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        bayar = findViewById(R.id.bayar);
        bayar.setOnClickListener(this);
        selesai = findViewById(R.id.selesai);
        selesai.setOnClickListener(this);
        rute = findViewById(R.id.nav);
        rute.setOnClickListener(this);
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    private void fetchLastLoc() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TampilManNav.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    loc=location;
                    pels = new MarkerOptions().position(new LatLng(Float.parseFloat(lati), Float.parseFloat(longi)));
                    kurs = new MarkerOptions().position(new LatLng(loc.getLatitude(),loc.getLongitude()));

                    supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;
                            Log.d("mylog", "Added Markers");
                            mMap.addMarker(pels);
                            mMap.addMarker(kurs);
                            LatLngBounds.Builder builder = new LatLngBounds.Builder();
                            builder.include(pels.getPosition());
                            builder.include(kurs.getPosition());
                            LatLngBounds bounds = builder.build();
                            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100), 2000, null);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                Intent in = new Intent(TampilManNav.this, MainActivityKurir.class);
                startActivity(in);
                finish();
                break;
            case R.id.nav:
                String url = getUrl(kurs.getPosition(), pels.getPosition(), "riding");
                new FetchURL(TampilManNav.this).execute(url, "riding");
                break;
            case R.id.bayar:
                AlertDialog.Builder builder = new AlertDialog.Builder(TampilManNav.this);
                builder.setTitle("Pembayaran");
                builder.setMessage("Pelanggan Bayar Pakai Cash atau Transfer?");
                builder.setCancelable(true);
                builder.setPositiveButton("Cash", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface builder, int which) {
                        cashBayar(vkd_trans,cash);
                        status_bayar.setText("Status Bayar : "+cash);
                        builder.dismiss();
                    }
                });
                builder.setNegativeButton("Transfer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface builder, int which) {
                        trfBayar(vkd_trans,transfer);
                        status_bayar.setText(transfer);
                        builder.dismiss();
                    }
                });
                builder.create().show();

                break;
            case R.id.selesai:
                Intent i = new Intent(TampilManNav.this, MainActivityKurir.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void cashBayar(String vkd_trans, String cash) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bayar> call = apiInterface.bayarRespon(cash,vkd_trans);
        call.enqueue(new Callback<Bayar>() {
            @Override
            public void onResponse(Call<Bayar> call, Response<Bayar> response) {
                Toast.makeText(TampilManNav.this, response.body().getMessage()
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Bayar> call, Throwable t) {
                Toast.makeText(TampilManNav.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void trfBayar(String vkd_trans, String transfer) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Bayar> call = apiInterface.bayarRespon(transfer,vkd_trans);
        call.enqueue(new Callback<Bayar>() {
            @Override
            public void onResponse(Call<Bayar> call, Response<Bayar> response) {
                Toast.makeText(TampilManNav.this, response.body().getMessage()
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Bayar> call, Throwable t) {
                Toast.makeText(TampilManNav.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTaskDone(Object... values) {
        if (polyline != null)
            polyline.remove();
        polyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
}