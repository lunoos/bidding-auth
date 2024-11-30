package com.bidding.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bidding.auth.dto.SignInRequest;
import com.bidding.auth.dto.SignUpRequest;
import com.bidding.auth.dto.UserResponse;
import com.bidding.auth.entity.User;
import com.bidding.auth.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        UserResponse userResponse = userService.signUp(signUpRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        UserResponse userResponse = userService.signIn(signInRequest);
        return ResponseEntity.ok(userResponse);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    	return userService.findUserById(userId)
                .map(vendor -> new ResponseEntity<>(vendor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}