package com.ngeten.aplikasi.model.Status;

import com.google.gson.annotations.SerializedName;

public class DataStatus {

	@SerializedName("kd_trans")
	private String kdTrans;

	@SerializedName("status")
	private String status;

	public void setKdTrans(String kdTrans){
		this.kdTrans = kdTrans;
	}

	public String getKdTrans(){
		return kdTrans;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}