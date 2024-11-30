package com.bidding.auth.dto;

public class UserResponse {
	private Long id;
    private String username;
    private String email;
    private String token;
    private Long expiresIn;
	public UserResponse() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", username=" + username + ", email=" + email + ", token=" + token + "]";
	}
	

}
