package com.ngeten.aplikasi.model.Dipilih;

import com.google.gson.annotations.SerializedName;

public class Dipilih{

	@SerializedName("data")
	private DataDipilih dataDipilih;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataDipilih dataDipilih){
		this.dataDipilih = dataDipilih;
	}

	public DataDipilih getData(){
		return dataDipilih;
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