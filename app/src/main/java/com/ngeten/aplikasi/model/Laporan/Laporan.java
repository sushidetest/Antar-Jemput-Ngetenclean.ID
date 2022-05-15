package com.ngeten.aplikasi.model.Laporan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Laporan{

	@SerializedName("data")
	private List<DataLaporan> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<DataLaporan> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}