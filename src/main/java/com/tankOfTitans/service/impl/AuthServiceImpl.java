package com.tankOfTitans.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.request.LoginRequest;
import com.tankOfTitans.model.dto.request.RegisterRequest;
import com.tankOfTitans.model.dto.response.LoginResponse;
import com.tankOfTitans.model.entity.Tanque;
import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.model.entity.UsuarioAvatar;
import com.tankOfTitans.model.entity.UsuarioTanque;
import com.tankOfTitans.repository.AvatarRepository;
import com.tankOfTitans.repository.TanqueRepository;
import com.tankOfTitans.repository.UsuarioRepository;
import com.tankOfTitans.repository.UsuarioTanqueRepository;
import com.tankOfTitans.repository.UsuarioAvatarRepository;
import com.tankOfTitans.security.JWTUtil;
import com.tankOfTitans.service.AuthService;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final AvatarRepository avatarRepository;
    private final TanqueRepository tanqueRepository;
    private final UsuarioTanqueRepository usuarioTanqueRepository;
    private final UsuarioAvatarRepository usuarioAvatarRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, 
                           AvatarRepository avatarRepository, 
                           TanqueRepository tanqueRepository,
                           UsuarioTanqueRepository usuarioTanqueRepository,
                           UsuarioAvatarRepository usuarioAvatarRepository,
                           PasswordEncoder passwordEncoder, 
                           JWTUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.avatarRepository = avatarRepository;
        this.tanqueRepository = tanqueRepository;
        this.usuarioTanqueRepository = usuarioTanqueRepository;
        this.usuarioAvatarRepository = usuarioAvatarRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    

    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario user = usuarioRepository.findByNicknameOrEmail(request.getNickname(), request.getNickname())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos. Nickname recibido: " + request.getNickname()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getNickname());
        
        String iconoImagen = avatarRepository.findById((long) user.getIcono())
                .map(avatar -> avatar.getImagen())
                .orElse("recluta.png");

        return new LoginResponse(token, user.getId(), user.getNombre(), user.getNickname(), "Login correcto", user.getIcono(), iconoImagen, user.getRol().name());


    }



	@Override
	@Transactional
	public Usuario register(RegisterRequest request) {
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

        Usuario savedUser = usuarioRepository.save(user);

        // Asignar tanques iniciales (45, 49, 56)
        List<Long> initialTankIds = Arrays.asList(45L, 49L, 56L);
        for (Long tankId : initialTankIds) {
            tanqueRepository.findById(tankId).ifPresent(tanque -> {
                UsuarioTanque ut = new UsuarioTanque(savedUser, tanque);
                usuarioTanqueRepository.save(ut);
            });
        }

        // Asignar avatares iniciales (2, 6, 8, 10, 11, 34, 43)
        List<Long> initialAvatarIds = Arrays.asList(2L, 6L, 8L, 10L, 11L, 34L, 43L);
        for (Long avatarId : initialAvatarIds) {
            avatarRepository.findById(avatarId).ifPresent(avatar -> {
                UsuarioAvatar ua = new UsuarioAvatar(savedUser, avatar);
                usuarioAvatarRepository.save(ua);
            });
        }

        return savedUser;
	}
}
