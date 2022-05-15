package com.ngeten.aplikasi.model.Bayar;

import com.google.gson.annotations.SerializedName;

public class Bayar{

	@SerializedName("data")
	private DataBayar dataBayar;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataBayar dataBayar){
		this.dataBayar = dataBayar;
	}

	public DataBayar getData(){
		return dataBayar;
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