package com.tankOfTitans.controller;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tankOfTitans.document.EstadisticasJugador;
import com.tankOfTitans.document.HistorialPartida;
import com.tankOfTitans.service.EstadisticasService;

@RestController
@RequestMapping("/api/estadisticas")
@Profile("mongo")
public class EstadisticasController {
	private final EstadisticasService estadisticasService;

	public EstadisticasController(EstadisticasService estadisticasService) {
		this.estadisticasService = estadisticasService;
	}
	
	// Sincronizar estadísticas de un jugador de MySQL a MongoDB
    @PostMapping("/sincronizar/{usuarioId}")
    public ResponseEntity<String> sincronizar(@PathVariable Long usuarioId) {
        estadisticasService.sincronizarEstadisticas(usuarioId);
        return ResponseEntity.ok("Estadísticas sincronizadas correctamente");
    }
	
    // Guardar historial de una partida finalizada
    @PostMapping("/historial/{partidaId}")
    public ResponseEntity<String> guardarHistorial(@PathVariable Long partidaId) {
        estadisticasService.guardarHistorialPartida(partidaId);
        return ResponseEntity.ok("Historial guardado correctamente");
    }
    
    // Ranking de jugadores por victorias
    @GetMapping("/ranking")
    public ResponseEntity<List<EstadisticasJugador>> getRanking() {
        return ResponseEntity.ok(estadisticasService.getRankingJugadores());
    }
    
 // Historial de partidas
    @GetMapping("/historial")
    public ResponseEntity<List<HistorialPartida>> getHistorial() {
        return ResponseEntity.ok(estadisticasService.getHistorialPartidas());
    }

}
