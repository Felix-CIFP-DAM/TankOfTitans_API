package com.tankOfTitans.service;

import java.util.List;

import com.tankOfTitans.model.dto.request.CreatePartidaRequest;
import com.tankOfTitans.model.dto.request.JoinPartidaRequest;
import com.tankOfTitans.model.response.PartidaResponse;

public interface LobbyService {
	PartidaResponse crearPartida(Long usuarioId, CreatePartidaRequest request);
    PartidaResponse unirseAPartida(Long usuarioId, Long partidaId, JoinPartidaRequest request);
    void eliminarPartida(Long usuarioId, Long partidaId);
    void cambiarHost(Long partidaId, Long hostActualId);
    void marcarListo(Long usuarioId, Long partidaId);
    PartidaResponse iniciarPartida(Long usuarioId, Long partidaId);
    List<PartidaResponse> listarPartidasPublicas();
    PartidaResponse getEstadoPartida(Long partidaId);
}
