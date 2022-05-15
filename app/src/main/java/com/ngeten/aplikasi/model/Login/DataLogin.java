package com.ngeten.aplikasi.model.Login;

import com.google.gson.annotations.SerializedName;

public class DataLogin {

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("level")
	private String level;

	@SerializedName("id")
	private String id;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}