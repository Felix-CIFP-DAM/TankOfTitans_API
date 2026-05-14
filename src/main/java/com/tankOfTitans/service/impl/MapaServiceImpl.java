package com.tankOfTitans.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.request.MapaRequest;
import com.tankOfTitans.model.dto.response.MapaResponse;
import com.tankOfTitans.model.entity.Mapa;
import com.tankOfTitans.model.entity.Tile;
import com.tankOfTitans.repository.MapaRepository;
import com.tankOfTitans.service.MapaService;

import jakarta.transaction.Transactional;

@Service
public class MapaServiceImpl implements MapaService {
	
	private final MapaRepository mapaRepository;
 
	public MapaServiceImpl(MapaRepository mapaRepository) {
		this.mapaRepository = mapaRepository;
	}

	@Override
	@Transactional
	public MapaResponse crearMapa(MapaRequest request) {
		if (mapaRepository.findByNombre(request.getNombre()).isPresent()) {
            throw new RuntimeException("Ya existe un mapa con ese nombre");
        }
 
        Mapa mapa = new Mapa(request.getNombre());
		
		if (request.getData() != null) {
			mapa.setData(request.getData());
			if (request.getData().getSuelo() != null && request.getData().getSuelo().length > 0) {
				mapa.setAlto(request.getData().getSuelo().length);
				mapa.setAncho(request.getData().getSuelo()[0].length);
			}
		}
 
        mapaRepository.save(mapa);
        return toResponse(mapa);
	}

	@Override
	public MapaResponse getMapa(Long mapaId) {
		 Mapa mapa = mapaRepository.findById(mapaId)
	                .orElseThrow(() -> new RuntimeException("Mapa no encontrado"));
 
	        return toResponse(mapa);
	}
 
	@Override
	public List<MapaResponse> listarMapas() {
		return mapaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void eliminarMapa(Long mapaId) {
		Mapa mapa = mapaRepository.findById(mapaId)
                .orElseThrow(() -> new RuntimeException("Mapa no encontrado"));
        mapaRepository.delete(mapa);
		
	}
	
	private MapaResponse toResponse(Mapa mapa) {
		return new MapaResponse(
				mapa.getId(),
				mapa.getNombre(),
				mapa.getAncho(),
				mapa.getAlto(),
				mapa.getData()
		);
	}

}
