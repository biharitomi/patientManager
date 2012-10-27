package com.acme.hospital;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="LoginManagedBean")
public class LoginManagedBean {
	private String userName;
	private String password;
	
	public String login(){
		System.out.println("OK Button clicked!");
		return "/views/index?faces-redirect=true";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
