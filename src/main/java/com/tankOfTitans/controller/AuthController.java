package com.tankOfTitans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tankOfTitans.model.dto.request.LoginRequest;
import com.tankOfTitans.model.dto.request.RegisterRequest;
import com.tankOfTitans.model.response.LoginResponse;
import com.tankOfTitans.service.impl.AuthServiceImpl;
import com.tankOfTitans.service.impl.RegisterServiceImpl;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	private final AuthServiceImpl authService;
	private final RegisterServiceImpl registerService;

	public AuthController(AuthServiceImpl authService, RegisterServiceImpl registerService) {
		this.authService = authService;
		this.registerService = registerService;
	}
	
	 @PostMapping("/register")
	 public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
	        String message = registerService.register(request);
	        return ResponseEntity.ok(message);
	    }
	 
	 @PostMapping("/login")
	    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
	        LoginResponse response = authService.login(request);
	        return ResponseEntity.ok(response);
	    }
	
	
}
