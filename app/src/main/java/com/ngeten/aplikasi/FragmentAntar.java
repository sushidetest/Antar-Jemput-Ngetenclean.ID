package com.ngeten.aplikasi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ngeten.aplikasi.adapter.AntarData;
import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Kurir.DataKurir;
import com.ngeten.aplikasi.model.Selectkur.DataSelectkur;
import com.ngeten.aplikasi.model.Selectkur.Selectkur;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAntar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAntar extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private List<DataSelectkur> list = new ArrayList<>();
    SessionManager sessionManager;
    String id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentAntar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAntar.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAntar newInstance(String param1, String param2) {
        FragmentAntar fragment = new FragmentAntar();
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
        View v = inflater.inflate(R.layout.fragment_antar, container, false);
        sessionManager = new SessionManager(getContext());
        id = sessionManager.getUserDetail().get(SessionManager.id);
        recyclerView = v.findViewById(R.id.data);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        tampilData(id);
        return v;
    }

    private void tampilData(String id) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Selectkur> call = apiInterface.dataAmbilRespon(id);
        call.enqueue(new Callback<Selectkur>() {
            @Override
            public void onResponse(Call<Selectkur> call, Response<Selectkur> response) {
                list = response.body().getData();
                adapter = new AntarData(getContext(),list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Selectkur> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}