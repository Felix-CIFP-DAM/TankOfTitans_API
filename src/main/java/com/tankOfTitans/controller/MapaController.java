package com.tankOfTitans.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tankOfTitans.model.dto.request.MapaRequest;
import com.tankOfTitans.model.dto.response.MapaResponse;
import com.tankOfTitans.service.MapaService;

@RestController
@RequestMapping("/api/mapas")
public class MapaController {
	private final MapaService mapaService;

    public MapaController(MapaService mapaService) {
        this.mapaService = mapaService;
    }
    
    @PostMapping("/crear")
    public ResponseEntity<MapaResponse> crearMapa(@RequestBody MapaRequest request) {
        return ResponseEntity.ok(mapaService.crearMapa(request));
    }

    @GetMapping("/{mapaId}")
    public ResponseEntity<MapaResponse> getMapa(@PathVariable Long mapaId) {
        return ResponseEntity.ok(mapaService.getMapa(mapaId));
    }

    @GetMapping
    public ResponseEntity<List<MapaResponse>> listarMapas() {
        return ResponseEntity.ok(mapaService.listarMapas());
    }

    @DeleteMapping("/{mapaId}")
    public ResponseEntity<String> eliminarMapa(@PathVariable Long mapaId) {
        mapaService.eliminarMapa(mapaId);
        return ResponseEntity.ok("Mapa eliminado correctamente");
    }
}
