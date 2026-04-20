package com.tankOfTitans.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.request.RegisterRequest;
import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.repository.UsuarioRepository;
import com.tankOfTitans.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UsuarioRepository usuarioRepository,
                               PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
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
                passwordEncoder.encode(request.getPassword()), 
                request.getEmail() 
        );

        usuarioRepository.save(user);
        return "Usuario registrado correctamente";
	}

}
