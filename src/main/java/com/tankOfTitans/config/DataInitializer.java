package com.tankOfTitans.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.model.entity.enums.Rol;
import com.tankOfTitans.repository.UsuarioRepository;

@Configuration
public class DataInitializer {
	@Bean
    public CommandLineRunner initMiddlewareAdmin(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            if (usuarioRepository.findByNickname("middleware_admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Middleware Admin");
                admin.setNickname("middleware_admin");
                admin.setEmail("middleware@tankoftitans.com");
                admin.setPassword(passwordEncoder.encode("middleware_secret_2024"));
                admin.setRol(Rol.MIDDLEWARE);
                usuarioRepository.save(admin);
                System.out.println("Superusuario middleware_admin creado correctamente");
            }
        };
    }
}
