package com.tankOfTitans.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tankOfTitans.model.entity.Avatar;
import com.tankOfTitans.repository.AvatarRepository;
import com.tankOfTitans.service.AvatarService;
import com.tankOfTitans.model.dto.response.AvatarResponse;
import com.tankOfTitans.model.entity.UsuarioAvatar;
import com.tankOfTitans.repository.UsuarioAvatarRepository;
import jakarta.transaction.Transactional;

@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final UsuarioAvatarRepository usuarioAvatarRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository, UsuarioAvatarRepository usuarioAvatarRepository) {
        this.avatarRepository = avatarRepository;
        this.usuarioAvatarRepository = usuarioAvatarRepository;
    }

    @Override
    @Transactional
    public Avatar crearAvatar(String nombre, String imagen, int precio, boolean esComprable) {
        Avatar avatar = new Avatar(nombre, imagen, precio, esComprable);
        return avatarRepository.save(avatar);
    }

    @Override
    public List<Avatar> listarAvatares() {
        return avatarRepository.findAll();
    }

    @Override
    public List<AvatarResponse> listarAvataresParaUsuario(Long usuarioId) {
        List<Avatar> todos = avatarRepository.findAll();
        List<UsuarioAvatar> comprados = usuarioAvatarRepository.findByUsuarioId(usuarioId);
        
        return todos.stream().map(a -> {
            boolean comprado = comprados.stream().anyMatch(ua -> ua.getAvatar().getId().equals(a.getId()));
            return new AvatarResponse(a.getId(), a.getNombre(), a.getImagen(), a.getPrecio(), comprado, a.isEsComprable());
        }).toList();
    }

    @Override
    public Avatar obtenerAvatar(Long id) {
        return avatarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avatar no encontrado"));
    }
}
