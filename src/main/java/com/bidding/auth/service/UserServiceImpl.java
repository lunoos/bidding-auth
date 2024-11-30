package com.bidding.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bidding.auth.entity.User;
import com.bidding.auth.dto.SignInRequest;
import com.bidding.auth.dto.SignUpRequest;
import com.bidding.auth.dto.UserResponse;
import com.bidding.auth.exception.AuthenticationException;
import com.bidding.auth.exception.UserAlreadyExistsException;
import com.bidding.auth.repository.UserRepository;
import com.bidding.auth.security.JwtService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	@Transactional
	@Override
	public UserResponse signUp(SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new UserAlreadyExistsException("Username is already taken");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserAlreadyExistsException("Email is already in use");
		}

		User user = new User();
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

		User savedUser = userRepository.save(user);
		String jwtToken = jwtService.generateToken(savedUser);

		return createUserResponse(savedUser, jwtToken);
	}

	@Transactional
	@Override
	public UserResponse signIn(SignInRequest signInRequest) {
		try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );

			User user = (User) authentication.getPrincipal();
			String jwtToken = jwtService.generateToken(user);

			return createUserResponse(user, jwtToken);
		} catch (Exception e) {
			throw new AuthenticationException("Invalid username/password combination");
		}
	}

	private UserResponse createUserResponse(User user, String token) {
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setUsername(user.getUsername());
		userResponse.setEmail(user.getEmail());
		userResponse.setToken(token);
		userResponse.setExpiresIn(jwtService.getExpirationTime());
		return userResponse;
	}

	@Override
	public Optional<User> findUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}
}
