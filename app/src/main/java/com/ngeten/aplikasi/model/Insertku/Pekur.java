package com.ngeten.aplikasi.model.Insertku;

import com.google.gson.annotations.SerializedName;

public class Pekur{

	@SerializedName("data")
	private DataPekur dataPekur;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(DataPekur dataPekur){
		this.dataPekur = dataPekur;
	}

	public DataPekur getData(){
		return dataPekur;
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