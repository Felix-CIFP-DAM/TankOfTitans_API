package com.tankOfTitans.service.impl;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tankOfTitans.document.EstadisticasJugador;
import com.tankOfTitans.document.HistorialPartida;
import com.tankOfTitans.model.entity.Partida;
import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.repository.PartidaRepository;
import com.tankOfTitans.repository.UsuarioRepository;
import com.tankOfTitans.repository.mongo.EstadisticasJugadorRepository;
import com.tankOfTitans.repository.mongo.HistorialPartidaRepository;
import com.tankOfTitans.service.EstadisticasService;

@Service
@Profile("mongo")
public class EstadisticasServiceImpl implements EstadisticasService {
	private final EstadisticasJugadorRepository estadisticasRepository;
    private final HistorialPartidaRepository historialRepository;
    private final UsuarioRepository usuarioRepository;
    private final PartidaRepository partidaRepository;
    
    

	public EstadisticasServiceImpl(EstadisticasJugadorRepository estadisticasRepository,
			HistorialPartidaRepository historialRepository, UsuarioRepository usuarioRepository,
			PartidaRepository partidaRepository) {
		this.estadisticasRepository = estadisticasRepository;
		this.historialRepository = historialRepository;
		this.usuarioRepository = usuarioRepository;
		this.partidaRepository = partidaRepository;
	}

	@Override
	public void sincronizarEstadisticas(Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EstadisticasJugador stats = estadisticasRepository
                .findByMysqlUserId(usuarioId)
                .orElse(new EstadisticasJugador());

        stats.setMysqlUserId(usuario.getId());
        stats.setNickname(usuario.getNickname());
        stats.setPartidasJugadas(usuario.getPartidasJugadas());
        stats.setVictorias(usuario.getVictorias());
        stats.setDerrotas(usuario.getDerrotas());
        stats.setEmpates(usuario.getEmpates());

        estadisticasRepository.save(stats);
		
	}

	@Override
	public void guardarHistorialPartida(Long partidaId) {
		 Partida partida = partidaRepository.findById(partidaId)
	                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));

	        String ganadorNickname = partida.getGanador() != null
	                ? partida.getGanador().getNickname()
	                : "Empate";

	        HistorialPartida historial = new HistorialPartida(
	                partida.getId(),
	                partida.getNombre(),
	                ganadorNickname,
	                partida.getEstado().name(),
	                partida.getDuracionSegundos() != null ? partida.getDuracionSegundos() : 0,
	                partida.getTanquesMuertosJ1(),
	                partida.getTanquesMuertosJ2()
	        );

	        historialRepository.save(historial);
		
	}

	@Override
	public List<EstadisticasJugador> getRankingJugadores() {
		 return estadisticasRepository.findAllByOrderByVictoriasDesc();
	}

	@Override
	public List<HistorialPartida> getHistorialPartidas() {
		return historialRepository.findAllByOrderByFechaPartidaDesc();
	}

}
