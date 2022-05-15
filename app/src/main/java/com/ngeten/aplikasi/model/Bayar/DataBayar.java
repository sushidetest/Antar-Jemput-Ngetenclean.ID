package com.ngeten.aplikasi.model.Bayar;

import com.google.gson.annotations.SerializedName;

public class DataBayar {

	@SerializedName("kd_trans")
	private String kdTrans;

	@SerializedName("status_bayar")
	private String statusBayar;

	public void setKdTrans(String kdTrans){
		this.kdTrans = kdTrans;
	}

	public String getKdTrans(){
		return kdTrans;
	}

	public void setStatusBayar(String statusBayar){
		this.statusBayar = statusBayar;
	}

	public String getStatusBayar(){
		return statusBayar;
	}
}