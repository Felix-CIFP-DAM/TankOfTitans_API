package com.tankOfTitans.service;

import java.util.List;
import com.tankOfTitans.model.dto.request.MapaRequest;
import com.tankOfTitans.model.dto.response.MapaResponse;

public interface MapaService {
	MapaResponse crearMapa(MapaRequest request);
    MapaResponse getMapa(Long mapaId);
    List<MapaResponse> listarMapas();
    void eliminarMapa(Long mapaId);
}
