package com.ngeten.aplikasi.model.Insertka;

import com.google.gson.annotations.SerializedName;

public class Pekas{

	@SerializedName("data")
	private DataPekas dataPekas;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataPekas dataPekas){
		this.dataPekas = dataPekas;
	}

	public DataPekas getData(){
		return dataPekas;
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