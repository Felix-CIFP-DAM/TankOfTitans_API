package com.tankOfTitans.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.CasillaDTO;
import com.tankOfTitans.model.dto.request.MapaRequest;
import com.tankOfTitans.model.dto.response.MapaResponse;
import com.tankOfTitans.model.entity.Casilla;
import com.tankOfTitans.model.entity.Mapa;
import com.tankOfTitans.model.entity.Tile;
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
		
		if (request.getData() != null) {
			mapa.setData(request.getData());
			if (request.getData().getSuelo() != null && request.getData().getSuelo().length > 0) {
				mapa.setAlto(request.getData().getSuelo().length);
				mapa.setAncho(request.getData().getSuelo()[0].length);
			}
		}

        mapaRepository.save(mapa);

		List<CasillaDTO> casillasDTO = new ArrayList<>();

		// Si el request ya trae casillas (legacy), las usamos
		if (request.getCasillas() != null && !request.getCasillas().isEmpty()) {
			for (CasillaDTO dto : request.getCasillas()) {
				Casilla casilla = new Casilla(dto.getPosX(), dto.getPosY(),
						dto.isTransitable(), mapa);
				casillaRepository.save(casilla);
				casillasDTO.add(dto);
			}
		} else if (request.getData() != null) {
			// Generamos las casillas a partir de MapData para la lógica del juego
			Tile[][] suelo = request.getData().getSuelo();
			Tile[][] objetos = request.getData().getObjetos();

			for (int y = 0; y < suelo.length; y++) {
				for (int x = 0; x < suelo[y].length; x++) {
					boolean transitable = true;
					
					// Regla: si el suelo es No_Transitable, la casilla no es transitable
					if (suelo[y][x] != null && "No_Transitable".equals(suelo[y][x].getTipo())) {
						transitable = false;
					}
					
					// Regla: si hay un objeto y es No_Transitable, la casilla no es transitable
					if (objetos != null && y < objetos.length && x < objetos[y].length) {
						if (objetos[y][x] != null && "No_Transitable".equals(objetos[y][x].getTipo())) {
							transitable = false;
						}
					}

					Casilla casilla = new Casilla(x, y, transitable, mapa);
					casillaRepository.save(casilla);
					casillasDTO.add(new CasillaDTO(x, y, transitable));
				}
			}
		}

        return toResponse(mapa, casillasDTO);
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
				casillas,
				mapa.getData()
		);
	}

}
