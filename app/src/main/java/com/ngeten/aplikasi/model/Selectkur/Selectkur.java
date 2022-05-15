package com.ngeten.aplikasi.model.Selectkur;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Selectkur{

	@SerializedName("data")
	private List<DataSelectkur> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataSelectkur> data){
		this.data = data;
	}

	public List<DataSelectkur> getData(){
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