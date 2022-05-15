package com.ngeten.aplikasi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ngeten.aplikasi.adapter.AdapterData;
import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Pesanan.DataPesanan;
import com.ngeten.aplikasi.model.Pesanan.Pesanan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCekPesan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCekPesan extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private List<DataPesanan> dataPesanan = new ArrayList<>();
    SessionManager sessionManager;
    String id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCekPesan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCekPesan.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCekPesan newInstance(String param1, String param2) {
        FragmentCekPesan fragment = new FragmentCekPesan();
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
        View v = inflater.inflate(R.layout.fragment_cek_pesan, container, false);
        sessionManager = new SessionManager(getActivity());
        recyclerView = v.findViewById(R.id.data);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        id = sessionManager.getUserDetail().get(SessionManager.id);
        tampilData(id);
        return v;
    }

    public void tampilData(String id){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Pesanan> call = apiInterface.cekpesanRespon(id);
        call.enqueue(new Callback<Pesanan>() {
            @Override
            public void onResponse(Call<Pesanan> call, Response<Pesanan> response) {
                dataPesanan = response.body().getData();
                adapter = new AdapterData(getContext(), dataPesanan);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Pesanan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}