package com.ngeten.aplikasi.model.Order;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Order{

	@SerializedName("data")
	private List<DataOrder> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataOrder> data){
		this.data = data;
	}

	public List<DataOrder> getData(){
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