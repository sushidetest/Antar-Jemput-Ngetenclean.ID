package com.ngeten.aplikasi.model.Pesan;

import com.google.gson.annotations.SerializedName;

public class Pesan{

	@SerializedName("data")
	private DataPesan dataPesan;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataPesan dataPesan){
		this.dataPesan = dataPesan;
	}

	public DataPesan getData(){
		return dataPesan;
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