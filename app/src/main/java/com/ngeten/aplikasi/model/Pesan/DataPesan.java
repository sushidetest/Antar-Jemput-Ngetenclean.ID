package com.ngeten.aplikasi.model.Pesan;

import com.google.gson.annotations.SerializedName;

public class DataPesan {

	@SerializedName(":status_bayar")
	private String statusBayar;

	@SerializedName(":kd_sepatu")
	private String kdSepatu;

	@SerializedName(":id")
	private String id;

	@SerializedName(":id_kurir")
	private Object idKurir;

	@SerializedName(":longitude")
	private String longitude;

	@SerializedName(":jml_sepatu")
	private String jmlSepatu;

	@SerializedName(":latitude")
	private String latitude;

	@SerializedName(":status")
	private String status;

	@SerializedName(":tgl_pesan")
	private String tglPesan;

	@SerializedName(":kd_paket")
	private String kdPaket;

	public void setStatusBayar(String statusBayar){
		this.statusBayar = statusBayar;
	}

	public String getStatusBayar(){
		return statusBayar;
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

	public void setIdKurir(Object idKurir){
		this.idKurir = idKurir;
	}

	public Object getIdKurir(){
		return idKurir;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	public void setJmlSepatu(String jmlSepatu){
		this.jmlSepatu = jmlSepatu;
	}

	public String getJmlSepatu(){
		return jmlSepatu;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTglPesan(String tglPesan){
		this.tglPesan = tglPesan;
	}

	public String getTglPesan(){
		return tglPesan;
	}

	public void setKdPaket(String kdPaket){
		this.kdPaket = kdPaket;
	}

	public String getKdPaket(){
		return kdPaket;
	}
}