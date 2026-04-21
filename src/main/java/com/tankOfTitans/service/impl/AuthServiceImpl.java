package com.tankOfTitans.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.request.LoginRequest;
import com.tankOfTitans.model.dto.request.RegisterRequest;
import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.model.response.LoginResponse;
import com.tankOfTitans.repository.UsuarioRepository;
import com.tankOfTitans.security.JWTUtil;
import com.tankOfTitans.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    

    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario user = usuarioRepository.findByNickname(request.getNickname())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getNickname());
        return new LoginResponse(token, user.getId(), user.getNickname(), "Login correcto");
    }



	@Override
	public String register(RegisterRequest request) {
		if (usuarioRepository.existsByNickname(request.getNickname())) {
            throw new RuntimeException("El nickname ya está en uso");
        }
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario user = new Usuario(
                request.getNombre(),
                request.getNickname(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        usuarioRepository.save(user);
        return "Usuario registrado correctamente";
	}
}
