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
import com.tankOfTitans.model.dto.request.UsuarioIdRequest;
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
            @RequestBody CreatePartidaRequest request) {
        
        System.out.println("[JAVA][LobbyController] 🚀 crearPartida para usuarioId: " + request.getUsuarioId());
        
        if (request.getUsuarioId() == null) {
            throw new RuntimeException("El usuarioId es obligatorio para crear una partida");
        }
        
        return ResponseEntity.ok(lobbyService.crearPartida(request.getUsuarioId(), request));
    }

    // Listar partidas públicas en espera
    @GetMapping("/partidas")
    public ResponseEntity<List<PartidaResponse>> listarPartidas() {
        return ResponseEntity.ok(lobbyService.listarPartidasDisponibles());
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
            @PathVariable Long partidaId,
            @RequestBody JoinPartidaRequest request) {
        
        System.out.println("[JAVA][LobbyController] 🚀 unirseAPartida para usuarioId: " + request.getUsuarioId() + " en partida: " + partidaId);

        if (request.getUsuarioId() == null) {
            throw new RuntimeException("El usuarioId es obligatorio para unirse a una partida");
        }

        return ResponseEntity.ok(lobbyService.unirseAPartida(request.getUsuarioId(), partidaId, request));
    }
    
    // Marcar listo / no listo (toggle)
    @PutMapping("/listo/{partidaId}")
    public ResponseEntity<String> marcarListo(
            @PathVariable Long partidaId,
            @RequestBody UsuarioIdRequest request) {
        
        System.out.println("[JAVA][LobbyController] 🚀 marcarListo para usuarioId: " + request.getUsuarioId());
        
        if (request.getUsuarioId() == null) {
            throw new RuntimeException("El usuarioId es obligatorio para marcar como listo");
        }

        lobbyService.marcarListo(request.getUsuarioId(), partidaId);
        return ResponseEntity.ok("Estado de listo actualizado");
    }
    
    // Iniciar partida
    @PutMapping("/iniciar/{partidaId}")
    public ResponseEntity<PartidaResponse> iniciarPartida(
            @PathVariable Long partidaId,
            @RequestBody UsuarioIdRequest request) {
        
        System.out.println("[JAVA][LobbyController] 🚀 iniciarPartida para usuarioId: " + request.getUsuarioId());
        
        if (request.getUsuarioId() == null) {
            throw new RuntimeException("El usuarioId es obligatorio para iniciar la partida");
        }

        return ResponseEntity.ok(lobbyService.iniciarPartida(request.getUsuarioId(), partidaId));
    }

    // Eliminar partida (solo el host)
    @DeleteMapping("/eliminar/{partidaId}")
    public ResponseEntity<String> eliminarPartida(
            @PathVariable Long partidaId,
            @RequestBody UsuarioIdRequest request) {
        
        System.out.println("[JAVA][LobbyController] 🚀 eliminarPartida para usuarioId: " + request.getUsuarioId());
        
        if (request.getUsuarioId() == null) {
            throw new RuntimeException("El usuarioId es obligatorio para eliminar la partida");
        }

        lobbyService.eliminarPartida(request.getUsuarioId(), partidaId);
        return ResponseEntity.ok("Partida eliminada correctamente");
    }
    
    // Abandonar partida
    @PostMapping("/abandonar/{partidaId}")
    public ResponseEntity<String> abandonarPartida(
            @PathVariable Long partidaId,
            @RequestBody UsuarioIdRequest request) {
        
        System.out.println("[JAVA][LobbyController] 🚀 abandonarPartida para usuarioId: " + request.getUsuarioId());
        
        if (request.getUsuarioId() == null) {
            throw new RuntimeException("El usuarioId es obligatorio para abandonar la partida");
        }

        lobbyService.abandonarPartida(request.getUsuarioId(), partidaId);
        return ResponseEntity.ok("Has abandonado la partida correctamente");
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

    // Seleccionar tanque
    @PostMapping("/seleccionarTanque/{partidaId}")
    public ResponseEntity<String> seleccionarTanque(
            @PathVariable Long partidaId,
            @RequestBody com.tankOfTitans.model.dto.request.SeleccionarTanqueRequest request) {
        lobbyService.seleccionarTanque(request.getUsuarioId(), partidaId, request.getTanqueId());
        return ResponseEntity.ok("Tanque seleccionado");
    }

    // Deseleccionar tanque
    @PostMapping("/deseleccionarTanque/{partidaId}")
    public ResponseEntity<String> deseleccionarTanque(
            @PathVariable Long partidaId,
            @RequestBody com.tankOfTitans.model.dto.request.SeleccionarTanqueRequest request) {
        lobbyService.deseleccionarTanque(request.getUsuarioId(), partidaId, request.getTanqueId());
        return ResponseEntity.ok("Tanque deseleccionado");
    }

    // Seleccionar mapa
    @PostMapping("/seleccionarMapa/{partidaId}")
    public ResponseEntity<String> seleccionarMapa(
            @PathVariable Long partidaId,
            @RequestBody com.tankOfTitans.model.dto.request.SeleccionarMapaRequest request) {
        lobbyService.seleccionarMapa(request.getUsuarioId(), partidaId, request.getMapaId());
        return ResponseEntity.ok("Mapa seleccionado");
    }
}
