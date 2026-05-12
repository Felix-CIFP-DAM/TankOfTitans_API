package com.tankOfTitans.service;

import java.util.List;
import com.tankOfTitans.model.entity.Avatar;

public interface AvatarService {
    Avatar crearAvatar(String nombre, String imagen);
    List<Avatar> listarAvatares();
    Avatar obtenerAvatar(Long id);
}
