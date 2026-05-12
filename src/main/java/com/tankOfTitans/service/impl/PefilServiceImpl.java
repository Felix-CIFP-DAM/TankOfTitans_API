package com.tankOfTitans.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.request.ActualizarPerfilRequest;
import com.tankOfTitans.model.dto.response.PerfilResponse;
import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.repository.AvatarRepository;
import com.tankOfTitans.repository.UsuarioRepository;

import com.tankOfTitans.service.PerfilService;

import jakarta.transaction.Transactional;

@Service
public class PefilServiceImpl implements PerfilService {

	private final UsuarioRepository usuarioRepository;
	private final AvatarRepository avatarRepository;
	private final PasswordEncoder passwordEncoder;

	public PefilServiceImpl(UsuarioRepository usuarioRepository, AvatarRepository avatarRepository,
			PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.avatarRepository = avatarRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public PerfilResponse obtenerPerfil(Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return toResponse(usuario);
	}

	@Override
	@Transactional
	public PerfilResponse actualizarPerfil(ActualizarPerfilRequest request) {
		System.out.println("[JAVA][PerfilService] actualizarPerfil llamado para usuarioId: " + request.getUsuarioId());
		Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


		// Solo actualiza los campos que vienen rellenos
		if (request.getNombre() != null && !request.getNombre().isBlank()) {
			usuario.setNombre(request.getNombre());
		}

		if (request.getNickname() != null && !request.getNickname().isBlank()) {
			// Comprueba que el nickname no esté en uso por otro usuario
			usuarioRepository.findByNickname(request.getNickname())
					.ifPresent(u -> {
						if (!u.getId().equals(request.getUsuarioId())) {
							throw new RuntimeException("El nickname ya está en uso");
						}
					});
			usuario.setNickname(request.getNickname());
		}

		if (request.getEmail() != null && !request.getEmail().isBlank()) {
			// Comprueba que el email no esté en uso por otro usuario
			usuarioRepository.findByEmail(request.getEmail())
					.ifPresent(u -> {
						if (!u.getId().equals(request.getUsuarioId())) {
							throw new RuntimeException("El email ya está en uso");
						}
					});
			usuario.setEmail(request.getEmail());
		}

		if (request.getContrasena() != null && !request.getContrasena().isBlank()) {
			usuario.setPassword(passwordEncoder.encode(request.getContrasena()));
		}

		usuario.setIcono(request.getIcono());

		usuarioRepository.save(usuario);
		return toResponse(usuario);
	}

	private PerfilResponse toResponse(Usuario usuario) {
		String iconoImagen = avatarRepository.findById((long) usuario.getIcono())
				.map(avatar -> avatar.getImagen())
				.orElse("recluta.png");

		return new PerfilResponse(
				usuario.getId(),
				usuario.getNombre(),
				usuario.getNickname(),
				usuario.getEmail(),
				usuario.getIcono(),
				iconoImagen,
				usuario.getPartidasJugadas(),
				usuario.getVictorias(),
				usuario.getDerrotas(),
				usuario.getEmpates());

	}

}
