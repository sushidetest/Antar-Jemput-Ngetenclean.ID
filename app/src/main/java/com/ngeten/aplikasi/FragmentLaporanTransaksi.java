package com.ngeten.aplikasi;

import static com.ngeten.aplikasi.PrintActivity.REQUEST_ID_MULTIPLE_PERMISSIONS;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.ngeten.aplikasi.adapter.LapData;
import com.ngeten.aplikasi.api.ApiClient;
import com.ngeten.aplikasi.api.ApiInterface;
import com.ngeten.aplikasi.model.Laporan.DataLaporan;
import com.ngeten.aplikasi.model.Laporan.Laporan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLaporanTransaksi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLaporanTransaksi extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private List<DataLaporan> listLap;
    String tgl_pert, tgl_ked;

    FloatingActionButton print;
    TextView tgl1,tgl2;
    Button tampil;
    DatePickerDialog datep;
    SimpleDateFormat dateFormat;

    DataLaporan vkd_trans, vnama, vnama_paket, vtgl_pesan, vkurir, vjml_sepatu, vtotal, vstatus, vbayar;
    private Locale locale;
    private NumberFormat formatRp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentLaporanTransaksi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLaporanTransaksi.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLaporanTransaksi newInstance(String param1, String param2) {
        FragmentLaporanTransaksi fragment = new FragmentLaporanTransaksi();
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
        View v = inflater.inflate(R.layout.fragment_laporan_transaksi, container, false);

        locale = new Locale("in","ID");
        formatRp = NumberFormat.getCurrencyInstance(locale);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        tgl1 = v.findViewById(R.id.tgl1);
        tgl2 = v.findViewById(R.id.tgl2);
        recyclerView = v.findViewById(R.id.data);
        print = v.findViewById(R.id.print);
        tampil = v.findViewById(R.id.tampil);

        tgl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog1();
            }
        });

        tgl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog2();
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tgl_pert = tgl1.getText().toString();
                tgl_ked = tgl2.getText().toString();
                recyclerView.setHasFixedSize(true);
                manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                tampilData(tgl_pert,tgl_ked);
            }
        });

        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tgl_pert = tgl1.getText().toString();
                tgl_ked = tgl2.getText().toString();
                printLap(tgl_pert,tgl_ked);
            }
        });
        return v;
    }

    private void tampilData(String tgl_pert, String tgl_ked) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Laporan> call = apiInterface.laporanRespon(tgl_pert, tgl_ked);
        call.enqueue(new Callback<Laporan>() {
            @Override
            public void onResponse(Call<Laporan> call, Response<Laporan> response) {
                listLap = response.body().getData();
                adapter = new LapData(getContext(), listLap);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Laporan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDateDialog1() {
        Calendar nowdate = Calendar.getInstance();
        datep = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                tgl1.setText(dateFormat.format(newDate.getTime()));
            }
        },nowdate.get(Calendar.YEAR), nowdate.get(Calendar.MONTH), nowdate.get(Calendar.DAY_OF_MONTH));
        datep.show();
    }

    private void showDateDialog2() {
        Calendar nowdate = Calendar.getInstance();
        datep = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                tgl2.setText(dateFormat.format(newDate.getTime()));
            }
        },nowdate.get(Calendar.YEAR), nowdate.get(Calendar.MONTH), nowdate.get(Calendar.DAY_OF_MONTH));
        datep.show();
    }

    private void printLap(String tgl_pert, String tgl_ked){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Laporan> call = apiInterface.laporanRespon(tgl_pert, tgl_ked);
        call.enqueue(new Callback<Laporan>() {
            @Override
            public void onResponse(Call<Laporan> call, Response<Laporan> response) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        createPdf(response);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else  {
                    checkAndRequestPermissions();
                }
            }

            @Override
            public void onFailure(Call<Laporan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createPdf(Response<Laporan> response)  throws FileNotFoundException, DocumentException{
        List<DataLaporan> dataLap = response.body().getData();
        File docFolder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            String path = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
            docFolder = new File(path,"PDF");
        } else {
            String path = Environment.getExternalStorageDirectory()+"/PDF";
            docFolder = new File(path);
        }

        if(!docFolder.exists()) {
            docFolder.mkdir();
        }

        String targetPdf ="Laporan" + tgl_pert + "::" + tgl_ked + ".pdf";
        File filePath = new File(docFolder.getAbsolutePath(),targetPdf);
        OutputStream outputStream = new FileOutputStream(filePath);
        Document document = new Document(PageSize.A4);
        PdfPTable table = new PdfPTable(new float[]{3, 3, 3, 3, 3, 3, 3, 3});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setFixedHeight(50);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell("kd");
        table.addCell("Tanggal");
        table.addCell("Nama");
        table.addCell("Kurir");
        table.addCell("Jumlah Pesan");
        table.addCell("Total");
        table.addCell("Status");
        table.addCell("Pembayaran");
        PdfPCell[] cell = table.getRow(0).getCells();
        for (int j = 0; j < cell.length; j++){
            cell[j].setBackgroundColor(BaseColor.BLUE);
        }
        for (int i = 0;i <dataLap.size(); i++){
            vkd_trans = dataLap.get(i);
            vtgl_pesan = dataLap.get(i);
            vkurir = dataLap.get(i);
            vnama = dataLap.get(i);
            vnama_paket = dataLap.get(i);
            vjml_sepatu = dataLap.get(i);
            vtotal = dataLap.get(i);
            vstatus = dataLap.get(i);
            vbayar = dataLap.get(i);

            String kode = vkd_trans.getKdTrans();
            String tgl = vtgl_pesan.getTglPesan();
            String kur = vkurir.getNamaKurir();
            String nam = vnama.getNama();
            String jml = vjml_sepatu.getJmlSepatu();
            String np = vnama_paket.getNamaPaket();
            String tot = formatRp.format(vtotal.getTotal());
            String stat = vstatus.getStatus();
            String bay = vbayar.getStatusBayar();

            table.addCell(String.valueOf(kode));
            table.addCell(String.valueOf(tgl));
            table.addCell(String.valueOf(kur));
            table.addCell(String.valueOf(nam));
            table.addCell(jml + " " + np);
            table.addCell(tot);
            table.addCell(String.valueOf(stat));
            table.addCell(String.valueOf(bay));
        }

        PdfWriter.getInstance(document, outputStream);
        document.open();
        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 30.0f, Font.NORMAL, BaseColor.BLACK);
        Font g = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLACK);
        document.add(new Paragraph("Laporan Transaksi", f));
        document.add(new Paragraph("Tanggal " + tgl_pert + " sampai " + tgl_ked, g));
        document.add(table);

        document.close();
        Toast.makeText(getActivity(), "PDF is created!!!", Toast.LENGTH_LONG).show();

    }

    private boolean checkAndRequestPermissions() {
        int wtite = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (wtite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

}