package com.bidding.auth.service;

import java.util.Optional;

import com.bidding.auth.dto.SignInRequest;
import com.bidding.auth.dto.SignUpRequest;
import com.bidding.auth.dto.UserResponse;
import com.bidding.auth.entity.User;

public interface UserService {
	 public UserResponse signUp(SignUpRequest signUpRequest);
	 public UserResponse signIn(SignInRequest signInRequest);
	 public Optional<User> findUserById(Long userId);
}
