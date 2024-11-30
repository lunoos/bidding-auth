package com.bidding.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class SignInRequest {
	@NotBlank(message = "Username is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	public SignInRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignInRequest [email=" + email + ", password=" + password + "]";
	}
	

}
