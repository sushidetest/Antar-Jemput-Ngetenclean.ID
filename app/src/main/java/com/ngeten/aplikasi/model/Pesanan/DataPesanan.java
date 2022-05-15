package com.ngeten.aplikasi.model.Pesanan;

import com.google.gson.annotations.SerializedName;

public class DataPesanan {

	@SerializedName("jml_sepatu")
	private String jmlSepatu;

	@SerializedName("nomor_kurir")
	private String nomorKurir;

	@SerializedName("total")
	private int total;

	@SerializedName("nama")
	private String nama;

	@SerializedName("harga")
	private String harga;

	@SerializedName("tgl_pesan")
	private String tglPesan;

	@SerializedName("status_bayar")
	private String statusBayar;

	@SerializedName("nama_kurir")
	private String namaKurir;

	@SerializedName("kd_sepatu")
	private String kdSepatu;

	@SerializedName("id")
	private String id;

	@SerializedName("nama_paket")
	private String namaPaket;

	@SerializedName("status")
	private String status;

	public void setJmlSepatu(String jmlSepatu){
		this.jmlSepatu = jmlSepatu;
	}

	public String getJmlSepatu(){
		return jmlSepatu;
	}

	public void setNomorKurir(String nomorKurir){
		this.nomorKurir = nomorKurir;
	}

	public String getNomorKurir(){
		return nomorKurir;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setTglPesan(String tglPesan){
		this.tglPesan = tglPesan;
	}

	public String getTglPesan(){
		return tglPesan;
	}

	public void setStatusBayar(String statusBayar){
		this.statusBayar = statusBayar;
	}

	public String getStatusBayar(){
		return statusBayar;
	}

	public void setNamaKurir(String namaKurir){
		this.namaKurir = namaKurir;
	}

	public String getNamaKurir(){
		return namaKurir;
	}

	public void setKdSepatu(String kdSepatu){
		this.kdSepatu = kdSepatu;
	}

	public String getKdSepatu(){
		return kdSepatu;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNamaPaket(String namaPaket){
		this.namaPaket = namaPaket;
	}

	public String getNamaPaket(){
		return namaPaket;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}