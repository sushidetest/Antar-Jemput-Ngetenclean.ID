package com.ngeten.aplikasi.model.Ubahkur;

import com.google.gson.annotations.SerializedName;

public class Ubahkur{

	@SerializedName("data")
	private DataUbahkur dataUbahkur;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataUbahkur dataUbahkur){
		this.dataUbahkur = dataUbahkur;
	}

	public DataUbahkur getData(){
		return dataUbahkur;
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