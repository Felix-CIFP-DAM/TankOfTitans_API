package com.tankOfTitans.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.repository.UsuarioRepository;
import com.tankOfTitans.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
	}

	@Override
	public void deleteById(Long id) {
		if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
		
	}
	
}
