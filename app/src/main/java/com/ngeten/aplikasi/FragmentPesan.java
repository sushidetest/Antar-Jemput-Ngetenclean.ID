package com.ngeten.aplikasi;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPesan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPesan extends Fragment {
    Location loc;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    MarkerOptions markerOptions;
    public static final int REQUEST_CODE = 101;
    EditText sepatu;
    RadioButton deepclean, repaint, reglue, unyellowing;
    RadioGroup treat;
    TextView total;
    Button lanjut;
    int dc = 30000, rp = 75000, rg = 35000, uyl = 40000, ttl, kd_paket;
    String paket;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPesan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmenPesan.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPesan newInstance(String param1, String param2) {
        FragmentPesan fragment = new FragmentPesan();
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
        View v = inflater.inflate(R.layout.fragment_pesan, container, false);

        sepatu = v.findViewById(R.id.sepatu);

        deepclean = v.findViewById(R.id.deepclean);
        repaint = v.findViewById(R.id.repaint);
        reglue = v.findViewById(R.id.reglue);
        unyellowing = v.findViewById(R.id.unyellowing);
        total = v.findViewById(R.id.total);

        treat = v.findViewById(R.id.treat);
        treat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.deepclean:
                        kd_paket = 1;
                        paket = "Deepclean";
                        ttl = 0;
                        ttl = dc + ttl;
                        total.setText("Total : "+ttl);
                        break;
                    case R.id.repaint:
                        kd_paket = 2;
                        paket = "Repaint";
                        ttl = 0;
                        ttl = rp + ttl;
                        total.setText("Total : "+ttl);
                        break;
                    case R.id.reglue:
                        kd_paket = 3;
                        paket = "Reglue";
                        ttl = 0;
                        ttl = rg + ttl;
                        total.setText("Total : "+ttl);
                        break;
                    case R.id.unyellowing:
                        kd_paket = 4;
                        paket = "Unyellowing";
                        ttl = 0;
                        ttl = uyl + ttl;
                        total.setText("Total : "+ttl);
                        break;
                }
            }
        });

        client = LocationServices.getFusedLocationProviderClient(getActivity());
        fetchLastLoc();

        lanjut = v.findViewById(R.id.lanjut);
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sepatu.equals("") && paket.equals("") && kd_paket==0){
                    Toast.makeText(getContext(), "Pesan Masih Kosong", Toast.LENGTH_SHORT).show();
                }else{
                    final Bundle b = new Bundle();
                    b.putString("sepatu", sepatu.getText().toString());
                    b.putString("paket", paket);
                    b.putString("kd_paket", String.valueOf(kd_paket));
                    b.putString("totl", total.getText().toString());
                    b.putString("lat", String.valueOf(loc.getLatitude()));
                    b.putString("lon", String.valueOf(loc.getLongitude()));
                    FragmentDialog fragmentDialog = new FragmentDialog();
                    fragmentDialog.setArguments(b);
                    fragmentDialog.show(getActivity().getSupportFragmentManager(), "TAG");
                }
            }
        });
        return v;
    }

    private void fetchLastLoc() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    loc=location;
                    supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
                            markerOptions = new MarkerOptions().position(latLng).title("Lokasi Anda Saat ini!");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            googleMap.addMarker(markerOptions);
                        }
                    });

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLoc();
                }
                break;
        }
    }

}