package com.example.jwtdemo2.payload.response;
// credit from
//https://bezkoder.com/spring-boot-jwt-authentication/#google_vignette

public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
