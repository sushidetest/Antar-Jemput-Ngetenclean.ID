package com.ngeten.aplikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
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
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class PrintActivity extends AppCompatActivity {
    LinearLayout linPdf;
    TextView tgl, kd_trans, nama, tgl_pesan, kurir, jml_sepatu, treat, biaya, total;
    String vkd_trans, vnama, vnama_paket, vtgl_pesan, vkurir, vharga, vjml_sepatu, vtotal;
    Button print;
    SimpleDateFormat dateFormat;

    Bitmap bitmap;

    Locale locale;
    NumberFormat formatRp;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS= 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        Intent terima = getIntent();

        vkd_trans = terima.getStringExtra("kd_trans");
        vnama = terima.getStringExtra("nama");
        vnama_paket = terima.getStringExtra("treat");
        vtgl_pesan = terima.getStringExtra("tgl");
        vkurir = terima.getStringExtra("nama_kurir");
        vharga = terima.getStringExtra("harga");
        vjml_sepatu = terima.getStringExtra("jml_sepatu");
        vtotal = terima.getStringExtra("total");

        locale = new Locale("in","ID");
        formatRp = NumberFormat.getCurrencyInstance(locale);

        Date nowdate = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(nowdate);

        tgl = findViewById(R.id.tgl);
        kd_trans = findViewById(R.id.kd_trans);
        nama = findViewById(R.id.nama);
        tgl_pesan = findViewById(R.id.tgl_pesan);
        kurir = findViewById(R.id.kurir);
        jml_sepatu = findViewById(R.id.jml_sepatu);
        treat = findViewById(R.id.treat);
        biaya = findViewById(R.id.biaya);
        total = findViewById(R.id.total);
        linPdf = findViewById(R.id.struk);

        tgl.setText(date);
        kd_trans.setText("Kode Transaksi : " + vkd_trans);
        nama.setText("Nama Pelanggan : " + vnama);
        tgl_pesan.setText("Tanggal Pesan : " + vtgl_pesan);
        kurir.setText("Nama Kurir : " + vkurir);
        jml_sepatu.setText("Jumlah Sepatu : " + vjml_sepatu);
        treat.setText(vnama_paket);
        biaya.setText(vjml_sepatu + " x " + formatRp.format(Double.parseDouble(vharga)));
        total.setText("Total : " + formatRp.format(Double.parseDouble(vtotal)));

        print = findViewById(R.id.print);
        print.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                print.setVisibility(View.GONE);
                bitmap = loadBitmapFromView(linPdf, linPdf.getWidth(), linPdf.getHeight());
                createPdf();
                print.setVisibility(View.VISIBLE);
            } else  {
                checkAndRequestPermissions();
            }
        });
    }

    private Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        File docFolder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            String path = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)); // khusus buat android 11 atau lebih tinggi
            docFolder = new File(path,"PDF");
        } else {
            String path = Environment.getExternalStorageDirectory()+"/PDF"; // bisa di ganti sesuai path yang dimau
            docFolder = new File(path);
        }

        if(!docFolder.exists()) {
            docFolder.mkdir();
        }

        String targetPdf ="StrukKD" + vkd_trans + ".pdf";
        File filePath = new File(docFolder.getAbsolutePath(),targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_LONG).show();
    }

    private boolean checkAndRequestPermissions() {
        int wtite = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (wtite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}