package com.bidding.auth.exception;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException(String errorMssg) {
		super(errorMssg);
	}
}
