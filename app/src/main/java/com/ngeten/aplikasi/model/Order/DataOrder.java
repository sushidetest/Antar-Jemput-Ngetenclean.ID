package com.ngeten.aplikasi.model.Order;

import com.google.gson.annotations.SerializedName;

public class DataOrder {

	@SerializedName("jml_sepatu")
	private String jmlSepatu;

	@SerializedName("kd_trans")
	private String kdTrans;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("nama_kurir")
	private Object namaKurir;

	@SerializedName("kd_sepatu")
	private String kdSepatu;

	@SerializedName("alamat")
	private String alamat;

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

	public void setKdTrans(String kdTrans){
		this.kdTrans = kdTrans;
	}

	public String getKdTrans(){
		return kdTrans;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setNamaKurir(Object namaKurir){
		this.namaKurir = namaKurir;
	}

	public Object getNamaKurir(){
		return namaKurir;
	}

	public void setKdSepatu(String kdSepatu){
		this.kdSepatu = kdSepatu;
	}

	public String getKdSepatu(){
		return kdSepatu;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
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