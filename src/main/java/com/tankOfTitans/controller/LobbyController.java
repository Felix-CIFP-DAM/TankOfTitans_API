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
    private final com.tankOfTitans.repository.UsuarioRepository usuarioRepository;
    
	public LobbyController(LobbyService lobbyService, JWTUtil jwtUtil, com.tankOfTitans.repository.UsuarioRepository usuarioRepository) {
		super();
		this.lobbyService = lobbyService;
		this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
	}
    
	private Long getUserIdFromToken(String authHeader) {
        String token = authHeader.substring(7);
        return jwtUtil.extractUserId(token);
    }
	
	// Crear partida
    @PostMapping("/crear")
    public ResponseEntity<PartidaResponse> crearPartida(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody java.util.Map<String, Object> body) {
        
        System.out.println("[JAVA][LobbyController] Raw Body Crear: " + body);
        String tokenNickname = jwtUtil.extractNickname(authHeader.substring(7));
        System.out.println("[JAVA][LobbyController] 🔐 Token extraído de: " + tokenNickname);
        
        Long userId = body.get("usuarioId") != null ? Long.valueOf(body.get("usuarioId").toString()) : null;
        if (userId == null) {
            System.out.println("[JAVA][LobbyController] ⚠️ WARNING: usuarioId no viene en el body de crearPartida. Fallback al token.");
            userId = getUserIdFromToken(authHeader);
        }
        
        // Mapear manualmente el resto del DTO
        CreatePartidaRequest request = new CreatePartidaRequest();
        request.setNombre((String) body.get("nombre"));
        request.setPublica(body.get("publica") != null && (boolean) body.get("publica"));
        request.setPassword((String) body.get("password"));
        request.setUsuarioId(userId);

        System.out.println("[JAVA][LobbyController] 🚀 crearPartida FINAL para userId: " + userId);
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
            @RequestBody(required = false) java.util.Map<String, Object> body) {
        
        System.out.println("[JAVA][LobbyController] Raw Body Unirse: " + body);
        String tokenNickname = jwtUtil.extractNickname(authHeader.substring(7));
        System.out.println("[JAVA][LobbyController] 🔐 Token extraído de: " + tokenNickname);

        Long userId = (body != null && body.get("usuarioId") != null) ? Long.valueOf(body.get("usuarioId").toString()) : null;
        if (userId == null) {
            System.out.println("[JAVA][LobbyController] ⚠️ WARNING: usuarioId no viene en el body de unirseAPartida. Fallback al token.");
            userId = getUserIdFromToken(authHeader);
        }
        
        JoinPartidaRequest request = new JoinPartidaRequest();
        if (body != null) {
            request.setUsuarioId(userId);
            request.setPassword((String) body.get("password"));
        }

        System.out.println("[JAVA][LobbyController] 🚀 unirseAPartida FINAL para userId: " + userId + " en partida: " + partidaId);
        return ResponseEntity.ok(lobbyService.unirseAPartida(userId, partidaId, request));
    }
    
    // Marcar listo / no listo (toggle)
    @PutMapping("/listo/{partidaId}")
    public ResponseEntity<String> marcarListo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long partidaId,
            @RequestBody(required = false) java.util.Map<String, Object> body) {
        
        Long userId = (body != null && body.containsKey("usuarioId")) ? Long.valueOf(body.get("usuarioId").toString()) : null;
        if (userId == null) {
            userId = getUserIdFromToken(authHeader);
        }
        System.out.println("[JAVA][LobbyController] 🚀 marcarListo para userId: " + userId);
        lobbyService.marcarListo(userId, partidaId);
        return ResponseEntity.ok("Estado de listo actualizado");
    }
    
 // Eliminar partida (solo el host)
    @DeleteMapping("/eliminar/{partidaId}")
    public ResponseEntity<String> eliminarPartida(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long partidaId,
            @RequestBody(required = false) java.util.Map<String, Object> body) {
        
        Long userId = (body != null && body.containsKey("usuarioId")) ? Long.valueOf(body.get("usuarioId").toString()) : null;
        if (userId == null) {
            userId = getUserIdFromToken(authHeader);
        }
        System.out.println("[JAVA][LobbyController] 🚀 eliminarPartida para userId: " + userId);
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

    // Debug: Listar todos los usuarios para ver sus IDs
    @GetMapping("/debug/usuarios")
    public ResponseEntity<List<String>> listarUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll().stream()
                .map(u -> u.getNickname() + " (ID: " + u.getId() + ")")
                .collect(java.util.stream.Collectors.toList()));
    }
}
