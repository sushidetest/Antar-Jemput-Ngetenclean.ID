package com.ngeten.aplikasi.model.Dipilih;

import com.google.gson.annotations.SerializedName;

public class DataDipilih {

	@SerializedName("kd_trans")
	private String kdTrans;

	@SerializedName("id_kurir")
	private String idKurir;

	public void setKdTrans(String kdTrans){
		this.kdTrans = kdTrans;
	}

	public String getKdTrans(){
		return kdTrans;
	}

	public void setIdKurir(String idKurir){
		this.idKurir = idKurir;
	}

	public String getIdKurir(){
		return idKurir;
	}
}