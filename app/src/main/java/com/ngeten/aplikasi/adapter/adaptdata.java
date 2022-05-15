package com.ngeten.aplikasi.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ngeten.aplikasi.PrintActivity;
import com.ngeten.aplikasi.R;
import com.ngeten.aplikasi.TampilManNav;
import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Order.DataOrder;
import com.ngeten.aplikasi.model.Select.DataSelect;
import com.ngeten.aplikasi.model.Select.Select;
import com.ngeten.aplikasi.model.Status.Status;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class adaptdata extends RecyclerView.Adapter<adaptdata.HolderData> {
    private Context context;
    private List<DataOrder> dataOrder;
    private List<DataSelect> dataSelect;
    String kd;


    public adaptdata(Context context, List<DataOrder> dataOrder) {
        this.context = context;
        this.dataOrder = dataOrder;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View l = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kartu1, parent,false);
        HolderData holderData = new HolderData(l);
        return holderData;
    }

    @Override
    public void onBindViewHolder(adaptdata.HolderData holder, int position) {
        DataOrder cekO = dataOrder.get(position);
        holder.kd_trans.setText(cekO.getKdTrans());
        holder.nama.setText("Nama : " + cekO.getNama());
        holder.alamat.setText("Alamat : " + cekO.getAlamat());
        holder.no_hp.setText("Nomor HP : " + cekO.getNoHp());
        holder.nama_paket.setText("Nama Paket : "+ cekO.getNamaPaket());
        holder.tgl_pesan.setText("Tanggal Pesan : " + cekO.getTglPesan());
        holder.status.setText("Status : " + cekO.getStatus());
        holder.status_bayar.setText("Status Pembayaran : " + cekO.getStatusBayar());
        holder.kd_sepatu.setText("Kode Sepatu : " + cekO.getKdSepatu());
        holder.kurir.setText("Kurir : " + cekO.getNamaKurir());
        holder.harga.setText("Biaya :" + cekO.getHarga());
        holder.jml_sepatu.setText("Jumlah Sepatu : " + cekO.getJmlSepatu());
        holder.total.setText("Total Biaya : " + String.valueOf(cekO.getTotal()));
    }

    @Override
    public int getItemCount() {
        return dataOrder.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView kd_trans, nama, alamat, no_hp, nama_paket, tgl_pesan, status, status_bayar, kd_sepatu, kurir, harga, jml_sepatu, total;

        public HolderData( View itemView) {
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
                    builder.setMessage("Print Transaksi atau Ubah status?");
                    builder.setCancelable(true);
                    kd = kd_trans.getText().toString();
                    builder.setPositiveButton("Ubah Status", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ubahStat(kd);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("Print", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            selectOrder(kd);
                        }
                    });

                    builder.show();
                    return false;
                }
            });
        }

        private void ubahStat(String kd) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Status> call = apiInterface.selesaiRespon(kd);
            call.enqueue(new Callback<Status>() {
                @Override
                public void onResponse(Call<Status> call, Response<Status> response) {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Status> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public  void selectOrder (String kd){
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Select> call = apiInterface.dataTransRespon(kd);
            call.enqueue(new Callback<Select>() {
                @Override
                public void onResponse(Call<Select> call, Response<Select> response) {
                    dataSelect = response.body().getData();

                    String kd_trans = dataSelect.get(0).getKdTrans();
                    String nama = dataSelect.get(0).getNama();
                    String no_hp = dataSelect.get(0).getNoHp();
                    String alamat = dataSelect.get(0).getAlamat();
                    String treat = dataSelect.get(0).getNamaPaket();
                    String tgl = dataSelect.get(0).getTglPesan();
                    String status = dataSelect.get(0).getStatus();
                    String status_bayar = dataSelect.get(0).getStatusBayar();
                    String kd_sepatu = dataSelect.get(0).getKdSepatu();
                    String nama_kurir = dataSelect.get(0).getNamaKurir();
                    String harga = dataSelect.get(0).getHarga();
                    String jml_sepatu = dataSelect.get(0).getJmlSepatu();
                    String total = String.valueOf(dataSelect.get(0).getTotal());
                    String lati = dataSelect.get(0).getLatitude();
                    String longi = dataSelect.get(0).getLongitude();

                    Intent i = new Intent(context, PrintActivity.class);
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
                    i.putExtra("treat",treat);
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
