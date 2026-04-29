package com.tankOfTitans.service;

import com.tankOfTitans.model.dto.request.LoginRequest;
import com.tankOfTitans.model.dto.request.RegisterRequest;
import com.tankOfTitans.model.dto.response.LoginResponse;

public interface AuthService {
	String register(RegisterRequest request);
	LoginResponse login(LoginRequest request);
}
