package com.ngeten.aplikasi.model.Laporan;

import com.google.gson.annotations.SerializedName;

public class DataLaporan {

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("jml_sepatu")
	private String jmlSepatu;

	@SerializedName("total")
	private int total;

	@SerializedName("kd_trans")
	private String kdTrans;

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

	@SerializedName("nama_paket")
	private String namaPaket;

	@SerializedName("status")
	private String status;

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getJmlSepatu(){
		return jmlSepatu;
	}

	public int getTotal(){
		return total;
	}

	public String getKdTrans(){
		return kdTrans;
	}

	public String getNama(){
		return nama;
	}

	public String getHarga(){
		return harga;
	}

	public String getTglPesan(){
		return tglPesan;
	}

	public String getStatusBayar(){
		return statusBayar;
	}

	public String getNamaKurir(){
		return namaKurir;
	}

	public String getKdSepatu(){
		return kdSepatu;
	}

	public String getNamaPaket(){
		return namaPaket;
	}

	public String getStatus(){
		return status;
	}
}