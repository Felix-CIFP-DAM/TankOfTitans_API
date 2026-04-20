package com.tankOfTitans.security;

import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + nickname));

        return new org.springframework.security.core.userdetails.User(
                usuario.getNickname(),
                usuario.getPassword(),
                new ArrayList<>()
        );
	}

}
