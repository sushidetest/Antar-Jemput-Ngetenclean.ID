package com.ngeten.aplikasi.model.Ubah;

import com.google.gson.annotations.SerializedName;

public class Ubah{

	@SerializedName("data")
	private DataUbah dataUbah;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataUbah dataUbah){
		this.dataUbah = dataUbah;
	}

	public DataUbah getData(){
		return dataUbah;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}