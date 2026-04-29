package com.tankOfTitans.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tankOfTitans.model.dto.request.CreatePartidaRequest;
import com.tankOfTitans.model.dto.request.JoinPartidaRequest;
import com.tankOfTitans.model.dto.response.PartidaResponse;
import com.tankOfTitans.security.JWTUtil;
import com.tankOfTitans.service.LobbyService;

@RestController
@RequestMapping("/api/lobby")
public class LobbyController {
	private final LobbyService lobbyService;
    private final JWTUtil jwtUtil;
    
	public LobbyController(LobbyService lobbyService, JWTUtil jwtUtil) {
		super();
		this.lobbyService = lobbyService;
		this.jwtUtil = jwtUtil;
	}
    
	private Long getUserIdFromToken(String authHeader) {
        String token = authHeader.substring(7);
        return jwtUtil.extractUserId(token);
    }
	
	// Crear partida
    @PostMapping("/crear")
    public ResponseEntity<PartidaResponse> crearPartida(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody CreatePartidaRequest request) {
        Long userId = getUserIdFromToken(authHeader);
        return ResponseEntity.ok(lobbyService.crearPartida(userId, request));
    }

    // Listar partidas públicas en espera
    @GetMapping("/partidas")
    public ResponseEntity<List<PartidaResponse>> listarPartidas() {
        return ResponseEntity.ok(lobbyService.listarPartidasPublicas());
    }
    
 // Ver estado de una partida concreta
    @GetMapping("/partidas/{partidaId}")
    public ResponseEntity<PartidaResponse> getEstadoPartida(
            @PathVariable Long partidaId) {
        return ResponseEntity.ok(lobbyService.getEstadoPartida(partidaId));
    }
    
 // Unirse a una partida
    @PostMapping("/unirse/{partidaId}")
    public ResponseEntity<PartidaResponse> unirseAPartida(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long partidaId,
            @RequestBody(required = false) JoinPartidaRequest request) {
        Long userId = getUserIdFromToken(authHeader);
        return ResponseEntity.ok(lobbyService.unirseAPartida(userId, partidaId, request));
    }
    
    // Marcar listo / no listo (toggle)
    @PutMapping("/listo/{partidaId}")
    public ResponseEntity<String> marcarListo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long partidaId) {
        Long userId = getUserIdFromToken(authHeader);
        lobbyService.marcarListo(userId, partidaId);
        return ResponseEntity.ok("Estado de listo actualizado");
    }
    
 // Eliminar partida (solo el host)
    @DeleteMapping("/eliminar/{partidaId}")
    public ResponseEntity<String> eliminarPartida(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long partidaId) {
        Long userId = getUserIdFromToken(authHeader);
        lobbyService.eliminarPartida(userId, partidaId);
        return ResponseEntity.ok("Partida eliminada correctamente");
    }
    
 // Cambiar host (cuando el host se desconecta)
    @PutMapping("/cambiarHost/{partidaId}/{hostActualId}")
    public ResponseEntity<String> cambiarHost(
            @PathVariable Long partidaId,
            @PathVariable Long hostActualId) {
        lobbyService.cambiarHost(partidaId, hostActualId);
        return ResponseEntity.ok("Host cambiado correctamente");
    }
}
