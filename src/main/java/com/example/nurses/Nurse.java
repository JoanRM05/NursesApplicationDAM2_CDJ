package com.example.nurses;

public class Nurse {
	
	private String name;
	private String surname;
	private String user;
	private String pass;
	
	
	public Nurse(String name, String surname, String user, String pass) {
		this.name = name;
		this.surname = surname;
		this.user = user;
		this.pass = pass;
	}
	
	public Nurse() {
	}


	public String getName() {
		return name;
	}
  
	public void setName(String name) {
		this.name = name;
	}
  
	public String getSurname() {
		return surname;
	}

	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	public String getUser() {
		return user;
	}

	
	public void setUser(String user) {
		this.user = user;
	}
	
	
	public String getPass() {
		return pass;
	}
	

	public void setPass(String pass) {
		this.pass = pass;
	}
	

}
