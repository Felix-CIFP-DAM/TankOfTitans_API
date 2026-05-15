package com.tankOfTitans.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.JugadorEstadoDTO;
import com.tankOfTitans.model.dto.TanqueEstadoDTO;
import com.tankOfTitans.model.dto.request.EstadoPartidaRequest;
import com.tankOfTitans.model.dto.request.ResultadoPartidaRequest;
import com.tankOfTitans.model.dto.response.EstadoPartidaResponse;
import com.tankOfTitans.model.dto.response.ResultadoPartidaResponse;
import com.tankOfTitans.model.entity.Partida;
import com.tankOfTitans.model.entity.PartidaJugador;
import com.tankOfTitans.model.entity.PartidaTanque;
import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.model.entity.enums.EstadoPartida;
import com.tankOfTitans.repository.PartidaJugadorRepository;
import com.tankOfTitans.repository.PartidaRepository;
import com.tankOfTitans.repository.PartidaTanqueRepository;
import com.tankOfTitans.repository.UsuarioRepository;
import com.tankOfTitans.service.PartidaService;

import jakarta.transaction.Transactional;

@Service
public class PartidaServiceImpl implements PartidaService {
	
	private final PartidaRepository partidaRepository;
    private final PartidaJugadorRepository partidaJugadorRepository;
    private final PartidaTanqueRepository partidaTanqueRepository;
    private final UsuarioRepository usuarioRepository;
    

	public PartidaServiceImpl(PartidaRepository partidaRepository, PartidaJugadorRepository partidaJugadorRepository,
			PartidaTanqueRepository partidaTanqueRepository, UsuarioRepository usuarioRepository) {
		this.partidaRepository = partidaRepository;
		this.partidaJugadorRepository = partidaJugadorRepository;
		this.partidaTanqueRepository = partidaTanqueRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional
	public EstadoPartidaResponse guardarEstado(Long partidaId, EstadoPartidaRequest request) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));

        int tanquesMuertosJ1 = 0;
        int tanquesMuertosJ2 = 0;
        boolean esJ1 = true;

        for (JugadorEstadoDTO jugadorDTO : request.getJugadores()) {
            PartidaJugador pj = partidaJugadorRepository
                    .findByPartidaIdAndUsuarioId(partidaId, jugadorDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Jugador no encontrado en la partida"));

            List<PartidaTanque> tanquesActuales = partidaTanqueRepository.findByPartidaJugadorId(pj.getId());

            for (TanqueEstadoDTO tanqueDTO : jugadorDTO.getTanques()) {
                PartidaTanque pt = tanquesActuales.stream()
                        .filter(t -> t.getNumeroTanque() == tanqueDTO.getNumeroTanque())
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Tanque #" + tanqueDTO.getNumeroTanque() + " no encontrado para el jugador"));
                
                pt.setVivo(tanqueDTO.isVivo());
                pt.setPosX(tanqueDTO.getPosX());
                pt.setPosY(tanqueDTO.getPosY());
                partidaTanqueRepository.save(pt);
            }

            int muertos = (int) jugadorDTO.getTanques().stream()
                    .filter(t -> !t.isVivo()).count();

            if (esJ1) {
                tanquesMuertosJ1 = muertos;
                esJ1 = false;
            } else {
                tanquesMuertosJ2 = muertos;
            }
        }

        partida.setTanquesMuertosJ1(tanquesMuertosJ1);
        partida.setTanquesMuertosJ2(tanquesMuertosJ2);
        partidaRepository.save(partida);

        return buildEstadoResponse(partida, request.getJugadores());
    }

	@Override
	public EstadoPartidaResponse recuperarEstado(Long partidaId) {
		Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));

        List<PartidaJugador> jugadores = partidaJugadorRepository.findByPartidaId(partidaId);

        List<JugadorEstadoDTO> jugadoresDTO = new ArrayList<>();

        for (PartidaJugador pj : jugadores) {
            List<PartidaTanque> tanques = partidaTanqueRepository
                    .findByPartidaJugadorId(pj.getId());

            List<TanqueEstadoDTO> tanquesDTO = tanques.stream()
                    .map(t -> new TanqueEstadoDTO(
                            t.getNumeroTanque(),
                            t.getTanque().getTipo(),
                            t.getTanque().getHp(), // HP base, ya que se quitó hp de la tabla
                            t.isVivo(),
                            t.getPosX(),
                            t.getPosY()
                    ))
                    .collect(Collectors.toList());

            int muertos = (int) tanques.stream().filter(t -> !t.isVivo()).count();

            jugadoresDTO.add(new JugadorEstadoDTO(
                    pj.getUsuario().getId(),
                    pj.getUsuario().getNickname(),
                    muertos,
                    tanquesDTO
            ));
        }

        return new EstadoPartidaResponse(
                partidaId,
                partida.getEstado().name(),
                partida.getTanquesMuertosJ1(),
                partida.getTanquesMuertosJ2(),
                jugadoresDTO
        );
	}

	@Override
	@Transactional
	public ResultadoPartidaResponse guardarResultado(Long partidaId, ResultadoPartidaRequest request) {
		Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));

        partida.setEstado(EstadoPartida.FINALIZADA);
        partida.setDuracionSegundos(request.getDuracionSegundos());
        partida.setTanquesMuertosJ1(request.getTanquesMuertosJ1());
        partida.setTanquesMuertosJ2(request.getTanquesMuertosJ2());

        String ganadorNickname = "Empate";

        if (request.isEmpate()) {
            // Actualizar estadísticas de ambos jugadores como empate
            actualizarEstadisticas(request.getGanadorId(), false, true);
            actualizarEstadisticas(request.getPerdedorId(), false, true);
        } else {
            Usuario ganador = usuarioRepository.findById(request.getGanadorId())
                    .orElseThrow(() -> new RuntimeException("Ganador no encontrado"));
            partida.setGanador(ganador);
            ganadorNickname = ganador.getNickname();

            actualizarEstadisticas(request.getGanadorId(), true, false);
            actualizarEstadisticas(request.getPerdedorId(), false, false);
        }

        partidaRepository.save(partida);

        
        return new ResultadoPartidaResponse(
                partidaId,
                request.isEmpate() ? "EMPATE" : "VICTORIA",
                ganadorNickname,
                request.getDuracionSegundos(),
                "Resultado guardado correctamente"
        );
		
	}
	
	 private void actualizarEstadisticas(Long usuarioId, boolean gano, boolean empato) {
	        if (usuarioId == null) return;

	        Usuario usuario = usuarioRepository.findById(usuarioId)
	                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

	        usuario.setPartidasJugadas(usuario.getPartidasJugadas() + 1);

	        if (empato) {
	            usuario.setEmpates(usuario.getEmpates() + 1);
                usuario.setMonedas(usuario.getMonedas() + 75); // Recompensa por empate
	        } else if (gano) {
	            usuario.setVictorias(usuario.getVictorias() + 1);
                usuario.setMonedas(usuario.getMonedas() + 150); // Recompensa por ganar
	        } else {
	            usuario.setDerrotas(usuario.getDerrotas() + 1);
                usuario.setMonedas(usuario.getMonedas() + 50); // Recompensa por perder
	        }

	        usuarioRepository.save(usuario);
	    }
	 
	 private EstadoPartidaResponse buildEstadoResponse(Partida partida,
             List<JugadorEstadoDTO> jugadores) {
		 return new EstadoPartidaResponse(
				 partida.getId(),
				 partida.getEstado().name(),
				 partida.getTanquesMuertosJ1(),
				 partida.getTanquesMuertosJ2(),
				 jugadores);
}
	
	

	@Override
    @Transactional
    public void resetPartida(Long partidaId) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));

        partida.setEstado(EstadoPartida.PREPARACION);
        partida.setGanador(null);
        partida.setDuracionSegundos(0);
        partida.setTanquesMuertosJ1(0);
        partida.setTanquesMuertosJ2(0);

        // Limpiar tanques y bases de los jugadores
        List<PartidaJugador> jugadores = partidaJugadorRepository.findByPartidaId(partidaId);
        for (PartidaJugador pj : jugadores) {
            pj.getTanques().clear();
            pj.getBases().clear();
            partidaJugadorRepository.save(pj);
        }

        partidaRepository.save(partida);
    }
}
