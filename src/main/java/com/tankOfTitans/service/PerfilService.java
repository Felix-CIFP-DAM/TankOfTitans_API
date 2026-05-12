package com.tankOfTitans.service;

import com.tankOfTitans.model.dto.request.ActualizarPerfilRequest;
import com.tankOfTitans.model.dto.response.PerfilResponse;

public interface PerfilService {
    PerfilResponse obtenerPerfil(Long usuarioId);
    PerfilResponse actualizarPerfil(ActualizarPerfilRequest request);
}