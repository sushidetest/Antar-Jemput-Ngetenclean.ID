package com.ngeten.aplikasi.model.Daftar;

import com.google.gson.annotations.SerializedName;

public class Daftar{

	@SerializedName("data")
	private DaftarData daftarData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DaftarData daftarData){
		this.daftarData = daftarData;
	}

	public DaftarData getData(){
		return daftarData;
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