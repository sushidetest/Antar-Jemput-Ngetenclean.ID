package com.ngeten.aplikasi.model.Kurir;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Kurir{

	@SerializedName("data")
	private List<DataKurir> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataKurir> data){
		this.data = data;
	}

	public List<DataKurir> getData(){
		return data;
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