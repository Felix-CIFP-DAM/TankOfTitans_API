package com.tankOfTitans.service;

import com.tankOfTitans.model.dto.request.LoginRequest;
import com.tankOfTitans.model.dto.request.RegisterRequest;
import com.tankOfTitans.model.dto.response.LoginResponse;
import com.tankOfTitans.model.entity.Usuario;

public interface AuthService {
	Usuario register(RegisterRequest request);
	LoginResponse login(LoginRequest request);
}
