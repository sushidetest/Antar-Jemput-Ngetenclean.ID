package com.ngeten.aplikasi.model.Select;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Select{

	@SerializedName("data")
	private List<DataSelect> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataSelect> data){
		this.data = data;
	}

	public List<DataSelect> getData(){
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