package com.ngeten.aplikasi.api;

import com.ngeten.aplikasi.model.Bayar.Bayar;
import com.ngeten.aplikasi.model.Daftar.Daftar;
import com.ngeten.aplikasi.model.Dipilih.Dipilih;
import com.ngeten.aplikasi.model.Insertka.Pekas;
import com.ngeten.aplikasi.model.Insertku.Pekur;
import com.ngeten.aplikasi.model.Kurir.Kurir;
import com.ngeten.aplikasi.model.Laporan.Laporan;
import com.ngeten.aplikasi.model.Login.Login;
import com.ngeten.aplikasi.model.Order.Order;
import com.ngeten.aplikasi.model.Pesan.Pesan;
import com.ngeten.aplikasi.model.Pesanan.Pesanan;
import com.ngeten.aplikasi.model.Select.Select;
import com.ngeten.aplikasi.model.Selectkur.Selectkur;
import com.ngeten.aplikasi.model.Status.Status;
import com.ngeten.aplikasi.model.Ubah.Ubah;
import com.ngeten.aplikasi.model.Ubahkas.Ubahkas;
import com.ngeten.aplikasi.model.Ubahkur.Ubahkur;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginRespon(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("daftarpel.php")
    Call<Daftar> daftarRespon(
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("pesan.php")
    Call<Pesan> pesanRespon(
            @Field("id") String id,
            @Field("kd_paket") String kd_paket,
            @Field("jml_sepatu") String jml_sepatu,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );

    @FormUrlEncoded
    @POST("cekpesanpel.php")
    Call<Pesanan> cekpesanRespon(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("ubahpel.php")
    Call<Ubah> ubahRespon(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("cekorder.php")
    Call<Order> cekOrderRespon();

    @FormUrlEncoded
    @POST("status.php")
    Call<Status> selesaiRespon(
            @Field("kd_trans") String kd_trans
    );

    @FormUrlEncoded
    @POST("laporan.php")
    Call<Laporan> laporanRespon(
            @Field("tgl_pert") String tgl_pert,
            @Field("tgl_ked") String tgl_ked
    );

    @FormUrlEncoded
    @POST("ubahkas.php")
    Call<Ubahkas> ubahKasRespon(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("daftarkas.php")
    Call<Pekas> pekasRespon(
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Field("username") String username,
            @Field("password") String password,
            @Field("level") String level
    );

    @FormUrlEncoded
    @POST("daftarkur.php")
    Call<Pekur> pekurRespon(
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Field("username") String username,
            @Field("password") String password,
            @Field("level") String level
    );

    @GET("kurir.php")
    Call<Kurir> dataKurirRespon();

    @FormUrlEncoded
    @POST("ambilorder.php")
    Call<Dipilih> ubahKurirRespon(
            @Field("id_kurir") String id_kurir,
            @Field("kd_trans") String kd_trans
    );

    @FormUrlEncoded
    @POST("ambiltrans.php")
    Call<Select> dataTransRespon(
            @Field("kd_trans") String kd_trans
    );

    @FormUrlEncoded
    @POST("bayar.php")
    Call<Bayar> bayarRespon(
            @Field("status_bayar") String status_bayar,
            @Field("kd_trans") String kd_trans
    );

    @FormUrlEncoded
    @POST("dataantar.php")
    Call<Selectkur> dataAmbilRespon(
            @Field("id_kurir") String id_kurir
    );

    @FormUrlEncoded
    @POST("ubahkur.php")
    Call<Ubahkur> ubahKurRespon(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Field("username") String username,
            @Field("password") String password
    );

}
