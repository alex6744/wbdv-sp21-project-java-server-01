package com.example.jwtdemo2.payload.request;

// credit from
//https://bezkoder.com/spring-boot-jwt-authentication/#google_vignette


public class LoginRequest {

	private String username;


	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
