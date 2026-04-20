package com.tankOfTitans.service;

import com.tankOfTitans.model.dto.request.LoginRequest;
import com.tankOfTitans.model.response.LoginResponse;

public interface AuthService {
	LoginResponse login(LoginRequest request);
}
