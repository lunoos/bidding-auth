package com.bidding.auth.exception;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = -5340890584374399189L;
	
	public AuthenticationException(String message) {
		super(message);
	}
}
