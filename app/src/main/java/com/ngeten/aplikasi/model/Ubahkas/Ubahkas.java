package com.ngeten.aplikasi.model.Ubahkas;

import com.google.gson.annotations.SerializedName;

public class Ubahkas{

	@SerializedName("data")
	private DataUbahkas dataUbahkas;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataUbahkas dataUbahkas){
		this.dataUbahkas = dataUbahkas;
	}

	public DataUbahkas getData(){
		return dataUbahkas;
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