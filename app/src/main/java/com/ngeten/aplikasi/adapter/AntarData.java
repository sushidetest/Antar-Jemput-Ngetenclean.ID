package com.ngeten.aplikasi.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ngeten.aplikasi.R;
import com.ngeten.aplikasi.SessionManager;
import com.ngeten.aplikasi.TampilManNav;
import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Select.DataSelect;
import com.ngeten.aplikasi.model.Select.Select;
import com.ngeten.aplikasi.model.Selectkur.DataSelectkur;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AntarData extends RecyclerView.Adapter<AntarData.HolderData>{
    Context context;
    List<DataSelectkur> list;
    List<DataSelect> selectList;
    SessionManager sessionManager;
    String id,kd;

    public AntarData(Context context, List<DataSelectkur> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View l = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kartu1,parent,false);
        HolderData holderData = new HolderData(l);
        return holderData;
    }

    @Override
    public void onBindViewHolder(AntarData.HolderData holder, int position) {
        DataSelectkur dataKur = list.get(position);
        sessionManager = new SessionManager(context);
        id = sessionManager.getUserDetail().get(SessionManager.id);
        holder.kd_trans.setText(dataKur.getKdTrans());
        holder.nama.setText("Nama : " + dataKur.getNama());
        holder.alamat.setText("Alamat : " + dataKur.getAlamat());
        holder.no_hp.setText("Nomor WA : " + dataKur.getNoHp());
        holder.nama_paket.setText("Nama Paket : "+ dataKur.getNamaPaket());
        holder.tgl_pesan.setText("Tanggal Pesan : " + dataKur.getTglPesan());
        holder.status.setText("Status : " + dataKur.getStatus());
        holder.status_bayar.setText("Status Pembayaran : " + dataKur.getStatusBayar());
        holder.kd_sepatu.setText("Kode Sepatu : " + dataKur.getKdSepatu());
        holder.kurir.setText("Kurir : " + dataKur.getNamaKurir());
        holder.harga.setText("Biaya :" + dataKur.getHarga());
        holder.jml_sepatu.setText("Jumlah Sepatu : " + dataKur.getJmlSepatu());
        holder.total.setText("Total Biaya : " + String.valueOf(dataKur.getTotal()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView kd_trans, nama, alamat, no_hp, nama_paket, tgl_pesan, status, status_bayar, kd_sepatu, kurir, harga, jml_sepatu, total;

        public HolderData(View itemView) {
            super(itemView);

            kd_trans = itemView.findViewById(R.id.kd);
            nama = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
            no_hp = itemView.findViewById(R.id.no_hp);
            nama_paket = itemView.findViewById(R.id.paket);
            tgl_pesan = itemView.findViewById(R.id.tgl_pesan);
            status = itemView.findViewById(R.id.status);
            status_bayar = itemView.findViewById(R.id.status_bayar);
            kd_sepatu = itemView.findViewById(R.id.kd_sepatu);
            kurir = itemView.findViewById(R.id.kurir);
            harga = itemView.findViewById(R.id.biaya);
            jml_sepatu = itemView.findViewById(R.id.jml_sepatu);
            total = itemView.findViewById(R.id.total);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Pilihan");
                    builder.setMessage("Mau Anter Order ini?");
                    builder.setCancelable(true);
                    kd = kd_trans.getText().toString();
                    builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            selectOrder(kd);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("Ga Jadi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                    return false;
                }
            });
        }

        public  void selectOrder (String kd){
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Select> call = apiInterface.dataTransRespon(kd);
            call.enqueue(new Callback<Select>() {
                @Override
                public void onResponse(Call<Select> call, Response<Select> response) {
                    selectList = response.body().getData();

                    String kd_trans = selectList.get(0).getKdTrans();
                    String nama = selectList.get(0).getNama();
                    String no_hp = selectList.get(0).getNoHp();
                    String alamat = selectList.get(0).getAlamat();
                    String treatment = selectList.get(0).getNamaPaket();
                    String tgl = selectList.get(0).getTglPesan();
                    String status = selectList.get(0).getStatus();
                    String status_bayar = selectList.get(0).getStatusBayar();
                    String kd_sepatu = selectList.get(0).getKdSepatu();
                    String nama_kurir = selectList.get(0).getNamaKurir();
                    String harga = selectList.get(0).getHarga();
                    String jml_sepatu = selectList.get(0).getJmlSepatu();
                    String total = String.valueOf(selectList.get(0).getTotal());
                    String lati = selectList.get(0).getLatitude();
                    String longi = selectList.get(0).getLongitude();

                    Intent i = new Intent(context, TampilManNav.class);
                    i.putExtra("kd_trans",kd_trans);
                    i.putExtra("nama",nama);
                    i.putExtra("alamat",alamat);
                    i.putExtra("no_hp",no_hp);
                    i.putExtra("tgl",tgl);
                    i.putExtra("status",status);
                    i.putExtra("status_bayar",status_bayar);
                    i.putExtra("kd_sepatu",kd_sepatu);
                    i.putExtra("nama_kurir",nama_kurir);
                    i.putExtra("harga",harga);
                    i.putExtra("jml_sepatu",jml_sepatu);
                    i.putExtra("total",total);
                    i.putExtra("lati",lati);
                    i.putExtra("longi",longi);
                    i.putExtra("treatment",treatment);
                    context.startActivity(i);
                }

                @Override
                public void onFailure(Call<Select> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
