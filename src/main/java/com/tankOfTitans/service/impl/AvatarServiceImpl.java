package com.tankOfTitans.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tankOfTitans.model.entity.Avatar;
import com.tankOfTitans.repository.AvatarRepository;
import com.tankOfTitans.service.AvatarService;
import jakarta.transaction.Transactional;

@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @Override
    @Transactional
    public Avatar crearAvatar(String nombre, String imagen) {
        Avatar avatar = new Avatar(nombre, imagen);
        return avatarRepository.save(avatar);
    }

    @Override
    public List<Avatar> listarAvatares() {
        return avatarRepository.findAll();
    }

    @Override
    public Avatar obtenerAvatar(Long id) {
        return avatarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avatar no encontrado"));
    }
}
