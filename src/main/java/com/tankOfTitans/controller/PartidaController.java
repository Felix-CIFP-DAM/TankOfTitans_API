package com.tankOfTitans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tankOfTitans.model.dto.request.EstadoPartidaRequest;
import com.tankOfTitans.model.dto.request.ResultadoPartidaRequest;
import com.tankOfTitans.model.dto.response.EstadoPartidaResponse;
import com.tankOfTitans.model.dto.response.ResultadoPartidaResponse;
import com.tankOfTitans.service.PartidaService;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {
	 private final PartidaService partidaService;

	 public PartidaController(PartidaService partidaService) {
		this.partidaService = partidaService;
	 }
	 
	// Guardar estado periódico (min 0, min 5, min 10)
	    @PostMapping("/{partidaId}/estado")
	    public ResponseEntity<EstadoPartidaResponse> guardarEstado(
	            @PathVariable Long partidaId,
	            @RequestBody EstadoPartidaRequest request) {
	        return ResponseEntity.ok(partidaService.guardarEstado(partidaId, request));
	    }

	    // Recuperar estado en caso de reconexión
	    @GetMapping("/{partidaId}/estado")
	    public ResponseEntity<EstadoPartidaResponse> recuperarEstado(
	            @PathVariable Long partidaId) {
	        return ResponseEntity.ok(partidaService.recuperarEstado(partidaId));
	    }

	    // Guardar resultado final
	    @PostMapping("/{partidaId}/resultado")
	    public ResponseEntity<ResultadoPartidaResponse> guardarResultado(
	            @PathVariable Long partidaId,
	            @RequestBody ResultadoPartidaRequest request) {
	        return ResponseEntity.ok(partidaService.guardarResultado(partidaId, request));
	    }
	 
}
