package com.tankOfTitans.service;

import com.tankOfTitans.model.dto.response.TiendaResponse;

public interface TiendaService {
    TiendaResponse obtenerContenidoTienda(Long usuarioId);
    void comprarTanque(Long usuarioId, Long tanqueId);
    void comprarAvatar(Long usuarioId, Long avatarId);
}
