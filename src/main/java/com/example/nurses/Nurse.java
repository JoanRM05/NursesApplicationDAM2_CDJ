package com.example.nurses;

public class Nurse {
	
	//private int id;
	private String name;
	private String password;

	public Nurse (int id,String name, String password) {
		this.name=name;
		this.password=password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Nurse [name=" + name + ", password=" + password + "]";
	}
}
