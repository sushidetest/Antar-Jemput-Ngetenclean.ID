package com.ngeten.aplikasi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngeten.aplikasi.R;
import com.ngeten.aplikasi.model.Laporan.DataLaporan;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class LapData extends RecyclerView.Adapter<LapData.HolderData>{
    private Context context;
    private List<DataLaporan> listLap;
    Locale locale;
    NumberFormat formatRp;
    String dateSet;

    public void setDateSet(String dateSet) {
        this.dateSet = dateSet;
    }

    public LapData(Context context, List<DataLaporan> listLap) {
        this.context = context;
        this.listLap = listLap;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View l = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabel, parent,false);
        HolderData holderData = new HolderData(l);
        return holderData;
    }

    @Override
    public void onBindViewHolder(LapData.HolderData holder, int position) {
        HolderData rowHolder = holder;
        int rowData = rowHolder.getAbsoluteAdapterPosition();
        locale = new Locale("in","ID");
        formatRp = NumberFormat.getCurrencyInstance(locale);

        if (rowData == 0){
            rowHolder.kd_trans.setBackgroundResource(R.drawable.table_head);
            rowHolder.tanggal.setBackgroundResource(R.drawable.table_head);
            rowHolder.nama.setBackgroundResource(R.drawable.table_head);
            rowHolder.kurir.setBackgroundResource(R.drawable.table_head);
            rowHolder.jumlah.setBackgroundResource(R.drawable.table_head);
            rowHolder.total.setBackgroundResource(R.drawable.table_head);
            rowHolder.status.setBackgroundResource(R.drawable.table_head);
            rowHolder.status_bayar.setBackgroundResource(R.drawable.table_head);

            rowHolder.kd_trans.setText("kd");
            rowHolder.tanggal.setText("Tanggal");
            rowHolder.nama.setText("Nama");
            rowHolder.kurir.setText("Kurir");
            rowHolder.jumlah.setText("Jumlah");
            rowHolder.total.setText("total");
            rowHolder.status.setText("status");
            rowHolder.status_bayar.setText("Pembayaran");
        }else{
            DataLaporan cekLap = listLap.get(rowData - 1);

            rowHolder.kd_trans.setBackgroundResource(R.drawable.table_content);
            rowHolder.tanggal.setBackgroundResource(R.drawable.table_content);
            rowHolder.nama.setBackgroundResource(R.drawable.table_content);
            rowHolder.kurir.setBackgroundResource(R.drawable.table_content);
            rowHolder.jumlah.setBackgroundResource(R.drawable.table_content);
            rowHolder.total.setBackgroundResource(R.drawable.table_content);
            rowHolder.status.setBackgroundResource(R.drawable.table_content);
            rowHolder.status_bayar.setBackgroundResource(R.drawable.table_content);

            rowHolder.kd_trans.setText(cekLap.getKdTrans());
            rowHolder.tanggal.setText(cekLap.getTglPesan());
            rowHolder.nama.setText(cekLap.getNama());
            rowHolder.kurir.setText(cekLap.getNamaKurir());
            rowHolder.jumlah.setText(cekLap.getJmlSepatu() + " " + cekLap.getNamaPaket());
            rowHolder.total.setText(formatRp.format(cekLap.getTotal()));
            rowHolder.status.setText(cekLap.getStatus());
            rowHolder.status_bayar.setText(cekLap.getStatusBayar());
        }
    }

    @Override
    public int getItemCount() {
        return listLap.size() + 1;
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView kd_trans, tanggal, nama, kurir, jumlah,total, status, status_bayar;

        public HolderData(View itemView) {
            super(itemView);
            kd_trans = itemView.findViewById(R.id.kd_trans);
            tanggal = itemView.findViewById(R.id.tgl_pesan);
            nama = itemView.findViewById(R.id.nama);
            kurir = itemView.findViewById(R.id.kurir);
            jumlah = itemView.findViewById(R.id.jml_pesan);
            total = itemView.findViewById(R.id.total);
            status = itemView.findViewById(R.id.status);
            status_bayar = itemView.findViewById(R.id.status_bayar);

        }
    }
}
