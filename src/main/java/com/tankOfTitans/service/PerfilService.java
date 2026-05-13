package com.tankOfTitans.service;

import com.tankOfTitans.model.dto.request.ActualizarPerfilRequest;
import com.tankOfTitans.model.dto.response.PerfilResponse;
import java.util.List;
import com.tankOfTitans.model.dto.response.TanqueResponse;

public interface PerfilService {
    PerfilResponse obtenerPerfil(Long usuarioId);
    PerfilResponse actualizarPerfil(ActualizarPerfilRequest request);
    List<TanqueResponse> obtenerTanquesUsuario(Long usuarioId);
}