package com.tankOfTitans.service;

import java.util.List;
import com.tankOfTitans.model.entity.Avatar;

import com.tankOfTitans.model.dto.response.AvatarResponse;

public interface AvatarService {
    Avatar crearAvatar(String nombre, String imagen, int precio, boolean esComprable);
    List<Avatar> listarAvatares();
    List<AvatarResponse> listarAvataresParaUsuario(Long usuarioId);
    Avatar obtenerAvatar(Long id);
}
