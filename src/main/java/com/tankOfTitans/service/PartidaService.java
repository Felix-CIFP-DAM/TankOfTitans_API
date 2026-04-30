package com.tankOfTitans.service;

import com.tankOfTitans.model.dto.request.EstadoPartidaRequest;
import com.tankOfTitans.model.dto.request.ResultadoPartidaRequest;
import com.tankOfTitans.model.dto.response.EstadoPartidaResponse;
import com.tankOfTitans.model.dto.response.ResultadoPartidaResponse;

public interface PartidaService {
	EstadoPartidaResponse guardarEstado(Long partidaId, EstadoPartidaRequest request);
    EstadoPartidaResponse recuperarEstado(Long partidaId);
    ResultadoPartidaResponse guardarResultado(Long partidaId, ResultadoPartidaRequest request);
}
