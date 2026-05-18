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
    @Transactional
    public List<AvatarResponse> listarAvataresParaUsuario(Long usuarioId) {
        System.out.println("[JAVA][AvatarService] 📤 Listando avatares para usuarioId: " + usuarioId);
        
        List<Avatar> todos = avatarRepository.findAll();
        List<Long> idsComprados = usuarioAvatarRepository.findAvatarIdsByUsuarioId(usuarioId);
        
        System.out.println("[JAVA][AvatarService] 📥 Avatares totales: " + todos.size() + " | Avatares comprados (IDs): " + idsComprados);
        
        return todos.stream().map(a -> {
            boolean comprado = idsComprados.contains(a.getId());
            return new AvatarResponse(a.getId(), a.getNombre(), a.getImagen(), a.getPrecio(), comprado, a.isEsComprable());
        }).toList();
    }

    @Override
    public Avatar obtenerAvatar(Long id) {
        return avatarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avatar no encontrado"));
    }
}
