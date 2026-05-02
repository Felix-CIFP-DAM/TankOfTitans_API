package com.tankOfTitans.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.CasillaDTO;
import com.tankOfTitans.model.dto.request.MapaRequest;
import com.tankOfTitans.model.dto.response.MapaResponse;
import com.tankOfTitans.model.entity.Casilla;
import com.tankOfTitans.model.entity.Mapa;
import com.tankOfTitans.repository.CasillaRepository;
import com.tankOfTitans.repository.MapaRepository;
import com.tankOfTitans.service.MapaService;

import jakarta.transaction.Transactional;

@Service
public class MapaServiceImpl implements MapaService {
	
	private final MapaRepository mapaRepository;
    private final CasillaRepository casillaRepository;
    
    


	public MapaServiceImpl(MapaRepository mapaRepository, CasillaRepository casillaRepository) {
		this.mapaRepository = mapaRepository;
		this.casillaRepository = casillaRepository;
	}

	@Override
	@Transactional
	public MapaResponse crearMapa(MapaRequest request) {
		if (mapaRepository.findByNombre(request.getNombre()).isPresent()) {
            throw new RuntimeException("Ya existe un mapa con ese nombre");
        }

        Mapa mapa = new Mapa(request.getNombre());
        mapaRepository.save(mapa);

        for (CasillaDTO dto : request.getCasillas()) {
            Casilla casilla = new Casilla(dto.getPosX(), dto.getPosY(),
                    dto.isTransitable(), mapa);
            casillaRepository.save(casilla);
        }

        return toResponse(mapa, request.getCasillas());
	}

	@Override
	public MapaResponse getMapa(Long mapaId) {
		 Mapa mapa = mapaRepository.findById(mapaId)
	                .orElseThrow(() -> new RuntimeException("Mapa no encontrado"));

	        List<CasillaDTO> casillas = casillaRepository.findByMapaId(mapaId)
	                .stream()
	                .map(c -> new CasillaDTO(c.getPosX(), c.getPosY(), c.isTransitable()))
	                .collect(Collectors.toList());

	        return toResponse(mapa, casillas);
	}

	@Override
	public List<MapaResponse> listarMapas() {
		return mapaRepository.findAll().stream()
                .map(m -> {
                    List<CasillaDTO> casillas = casillaRepository.findByMapaId(m.getId())
                            .stream()
                            .map(c -> new CasillaDTO(c.getPosX(), c.getPosY(), c.isTransitable()))
                            .collect(Collectors.toList());
                    return toResponse(m, casillas);
                })
                .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void eliminarMapa(Long mapaId) {
		Mapa mapa = mapaRepository.findById(mapaId)
                .orElseThrow(() -> new RuntimeException("Mapa no encontrado"));
        mapaRepository.delete(mapa);
		
	}
	
	 private MapaResponse toResponse(Mapa mapa, List<CasillaDTO> casillas) {
	        return new MapaResponse(
	                mapa.getId(),
	                mapa.getNombre(),
	                mapa.getAncho(),
	                mapa.getAlto(),
	                casillas
	        );
	    }

}
