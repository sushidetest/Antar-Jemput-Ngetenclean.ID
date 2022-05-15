package com.ngeten.aplikasi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngeten.aplikasi.R;
import com.ngeten.aplikasi.model.Pesanan.DataPesanan;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context context;
    private List<DataPesanan> dataPesanan;

    public AdapterData(Context context, List<DataPesanan> dataPesanan) {
        this.context = context;
        this.dataPesanan = dataPesanan;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View l = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kartu, parent, false);
        HolderData holderData = new HolderData(l);
        return holderData;
    }

    @Override
    public void onBindViewHolder(AdapterData.HolderData holder, int position) {
        DataPesanan cekPD = dataPesanan.get(position);
        holder.id.setText(cekPD.getId());
        holder.nama.setText("Nama : " + cekPD.getNama());
        holder.nama_paket.setText("Nama Paket : "+ cekPD.getNamaPaket());
        holder.tgl_pesan.setText("Tanggal Pesan : " + cekPD.getTglPesan());
        holder.status.setText("Status : " + cekPD.getStatus());
        holder.status_bayar.setText("Status Pembayaran : " + cekPD.getStatusBayar());
        holder.kurir.setText("Kurir : " + cekPD.getNamaKurir());
        holder.nomor.setText("Kurir : " + cekPD.getNomorKurir());
        holder.harga.setText("Biaya :" + cekPD.getHarga());
        holder.jml_sepatu.setText("Jumlah Sepatu : " + cekPD.getJmlSepatu());
        holder.total.setText("Total Biaya : " + String.valueOf(cekPD.getTotal()));

    }

    @Override
    public int getItemCount() {
        return dataPesanan.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView id, nama, nama_paket, tgl_pesan, status, status_bayar, kurir, nomor, harga, jml_sepatu, total;

        public HolderData( View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            nama = itemView.findViewById(R.id.nama);
            nama_paket = itemView.findViewById(R.id.paket);
            tgl_pesan = itemView.findViewById(R.id.tgl_pesan);
            status = itemView.findViewById(R.id.status);
            status_bayar = itemView.findViewById(R.id.status_bayar);
            kurir = itemView.findViewById(R.id.kurir);
            nomor = itemView.findViewById(R.id.nomor);
            harga = itemView.findViewById(R.id.biaya);
            jml_sepatu = itemView.findViewById(R.id.jml_sepatu);
            total = itemView.findViewById(R.id.total);
        }
    }
}
