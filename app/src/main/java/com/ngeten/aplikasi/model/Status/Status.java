package com.ngeten.aplikasi.model.Status;

import com.google.gson.annotations.SerializedName;

public class Status{

	@SerializedName("data")
	private DataStatus dataStatus;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataStatus dataStatus){
		this.dataStatus = dataStatus;
	}

	public DataStatus getData(){
		return dataStatus;
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