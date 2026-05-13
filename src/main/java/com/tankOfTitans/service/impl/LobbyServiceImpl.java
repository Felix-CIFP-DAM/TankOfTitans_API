package com.tankOfTitans.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tankOfTitans.model.dto.JugadorLobbyDTO;
import com.tankOfTitans.model.dto.request.CreatePartidaRequest;
import com.tankOfTitans.model.dto.request.JoinPartidaRequest;
import com.tankOfTitans.model.dto.response.PartidaResponse;
import com.tankOfTitans.model.entity.Partida;
import com.tankOfTitans.model.entity.PartidaJugador;
import com.tankOfTitans.model.entity.PartidaTanque;
import com.tankOfTitans.model.entity.Tanque;
import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.model.entity.enums.EstadoPartida;
import com.tankOfTitans.repository.AvatarRepository;
import com.tankOfTitans.repository.PartidaJugadorRepository;
import com.tankOfTitans.repository.PartidaRepository;
import com.tankOfTitans.repository.PartidaTanqueRepository;
import com.tankOfTitans.repository.TanqueRepository;
import com.tankOfTitans.repository.UsuarioRepository;
import com.tankOfTitans.service.LobbyService;

import jakarta.transaction.Transactional;

@Service
public class LobbyServiceImpl implements LobbyService {

	private final PartidaRepository partidaRepository;
	private final PartidaJugadorRepository partidaJugadorRepository;
	private final UsuarioRepository usuarioRepository;
	private final AvatarRepository avatarRepository;
	private final PartidaTanqueRepository partidaTanqueRepository;
	private final TanqueRepository tanqueRepository;

	public LobbyServiceImpl(PartidaRepository partidaRepository, PartidaJugadorRepository partidaJugadorRepository,
			UsuarioRepository usuarioRepository, AvatarRepository avatarRepository,
			PartidaTanqueRepository partidaTanqueRepository, TanqueRepository tanqueRepository) {
		this.partidaRepository = partidaRepository;
		this.partidaJugadorRepository = partidaJugadorRepository;
		this.usuarioRepository = usuarioRepository;
		this.avatarRepository = avatarRepository;
		this.partidaTanqueRepository = partidaTanqueRepository;
		this.tanqueRepository = tanqueRepository;
	}

	@Override
	@Transactional
	public PartidaResponse crearPartida(Long usuarioId, CreatePartidaRequest request) {
		if (partidaRepository.existsByHostId(usuarioId)) {
			throw new RuntimeException("Ya tienes una partida creada");
		}

		Usuario host = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Partida partida = new Partida(
				request.getNombre(),
				request.isPublica(),
				request.getPassword(),
				host);
		partidaRepository.save(partida);

		// El host entra automáticamente como jugador
		PartidaJugador jugador = new PartidaJugador(partida, host, false);
		jugador.setListo(false);
		partidaJugadorRepository.save(jugador);

		return toResponse(partida, List.of(jugador));

	}

	@Override
	@Transactional
	public PartidaResponse unirseAPartida(Long usuarioId, Long partidaId, JoinPartidaRequest request) {

		Partida partida = partidaRepository.findById(partidaId)
				.orElseThrow(() -> new RuntimeException("Partida no encontrada"));

		if (!partida.getEstado().equals(EstadoPartida.ESPERANDO)) {
			throw new RuntimeException("La partida ya ha comenzado");
		}

		List<PartidaJugador> jugadores = new java.util.ArrayList<>(partidaJugadorRepository.findByPartidaId(partidaId));

		if (jugadores.size() >= 2) {
			throw new RuntimeException("La partida ya está llena");
		}

		if (!partida.isPublica()) {
			if (request == null || request.getPassword() == null
					|| !request.getPassword().equals(partida.getPassword())) {
				throw new RuntimeException("Contraseña incorrecta");
			}
		}

		boolean yaEsta = jugadores.stream()
				.anyMatch(j -> j.getUsuario().getId().equals(usuarioId));
		if (yaEsta) {
			throw new RuntimeException("Ya estás en esta partida");
		}

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		PartidaJugador jugador = new PartidaJugador(partida, usuario, false);
		partidaJugadorRepository.save(jugador);

		jugadores.add(jugador);
		return toResponse(partida, jugadores);
	}

	@Override
	@Transactional
	public void eliminarPartida(Long usuarioId, Long partidaId) {
		Partida partida = partidaRepository.findById(partidaId)
				.orElseThrow(() -> new RuntimeException("Partida no encontrada"));

		if (!partida.getHost().getId().equals(usuarioId)) {
			throw new RuntimeException("Solo el host puede eliminar la partida");
		}

		partidaJugadorRepository.deleteByPartidaId(partidaId);
		partidaRepository.delete(partida);

	}

	@Override
	@Transactional
	public void cambiarHost(Long partidaId, Long hostActualId) {
		Partida partida = partidaRepository.findById(partidaId)
				.orElseThrow(() -> new RuntimeException("Partida no encontrada"));

		List<PartidaJugador> jugadores = partidaJugadorRepository.findByPartidaId(partidaId);

		if (jugadores.isEmpty()) {
			partidaRepository.delete(partida);
			return;
		}

		jugadores.stream()
				.filter(j -> !j.getUsuario().getId().equals(hostActualId))
				.findFirst()
				.ifPresent(j -> {
					partida.setHost(j.getUsuario());
					partidaRepository.save(partida);
				});

	}

	@Override
	@Transactional
	public void marcarListo(Long usuarioId, Long partidaId) {
		PartidaJugador jugador = partidaJugadorRepository
				.findByPartidaIdAndUsuarioId(partidaId, usuarioId)
				.orElseThrow(() -> new RuntimeException("No estás en esta partida"));

		jugador.setListo(!jugador.isListo());
		partidaJugadorRepository.save(jugador);
	}

	@Override
	@Transactional
	public void seleccionarTanque(Long usuarioId, Long partidaId, Long tanqueId) {
		PartidaJugador jugador = partidaJugadorRepository
				.findByPartidaIdAndUsuarioId(partidaId, usuarioId)
				.orElseThrow(() -> new RuntimeException("No estás en esta partida"));

		Tanque tanque = tanqueRepository.findById(tanqueId)
				.orElseThrow(() -> new RuntimeException("Tanque no encontrado"));

		// Verificar si ya tiene 3 tanques
		List<PartidaTanque> tanquesActuales = partidaTanqueRepository.findByPartidaJugadorId(jugador.getId());
		if (tanquesActuales.size() >= 3) {
			throw new RuntimeException("Ya has seleccionado el máximo de 3 tanques");
		}

		// Verificar si ya ha seleccionado este tanque
		boolean yaSeleccionado = tanquesActuales.stream()
				.anyMatch(pt -> pt.getTanque().getId().equals(tanqueId));
		if (yaSeleccionado) {
			throw new RuntimeException("Este tanque ya está en tu pelotón");
		}

		PartidaTanque pt = new PartidaTanque(tanquesActuales.size() + 1, 0, 0, jugador, tanque);
		partidaTanqueRepository.save(pt);
	}

	@Override
	@Transactional
	public void deseleccionarTanque(Long usuarioId, Long partidaId, Long tanqueId) {
		PartidaJugador jugador = partidaJugadorRepository
				.findByPartidaIdAndUsuarioId(partidaId, usuarioId)
				.orElseThrow(() -> new RuntimeException("No estás en esta partida"));

		List<PartidaTanque> tanquesActuales = partidaTanqueRepository.findByPartidaJugadorId(jugador.getId());
		PartidaTanque ptToRemove = tanquesActuales.stream()
				.filter(pt -> pt.getTanque().getId().equals(tanqueId))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("El tanque no está en tu pelotón"));

		partidaTanqueRepository.delete(ptToRemove);
	}

	@Transactional
	@Override
	public PartidaResponse iniciarPartida(Long usuarioId, Long partidaId) {

		Partida partida = partidaRepository.findById(partidaId)
				.orElseThrow(() -> new RuntimeException("Partida no encontrada"));

		if (!partida.getHost().getId().equals(usuarioId)) {
			throw new RuntimeException("Solo el host puede iniciar la partida");
		}

		List<PartidaJugador> jugadores = partidaJugadorRepository.findByPartidaId(partidaId);

		if (jugadores.size() < 2) {
			throw new RuntimeException("Se necesitan 2 jugadores para iniciar");
		}

		boolean todosListos = jugadores.stream().allMatch(PartidaJugador::isListo);
		if (!todosListos) {
			throw new RuntimeException("No todos los jugadores están listos");
		}

		partida.setEstado(EstadoPartida.EN_CURSO);
		partidaRepository.save(partida);

		return toResponse(partida, jugadores);
	}

	@Override
	public List<PartidaResponse> listarPartidasDisponibles() {
		return partidaRepository.findByEstado(EstadoPartida.ESPERANDO).stream()
				.map(p -> {
					List<PartidaJugador> jugadores = partidaJugadorRepository.findByPartidaId(p.getId());
					return toResponse(p, jugadores);
				})
				.collect(Collectors.toList());
	}

	@Override
	public PartidaResponse getEstadoPartida(Long partidaId) {
		Partida partida = partidaRepository.findById(partidaId)
				.orElseThrow(() -> new RuntimeException("Partida no encontrada"));

		List<PartidaJugador> jugadores = partidaJugadorRepository.findByPartidaId(partidaId);

		return toResponse(partida, jugadores);
	}

	private PartidaResponse toResponse(Partida partida, List<PartidaJugador> jugadores) {
		List<JugadorLobbyDTO> jugadoresDTO = jugadores.stream()
				.map(j -> {
					String iconoImagen = avatarRepository.findById((long) j.getUsuario().getIcono())
							.map(avatar -> avatar.getImagen())
							.orElse("recluta.png");

					List<Long> tanquesIds = partidaTanqueRepository.findByPartidaJugadorId(j.getId()).stream()
							.map(pt -> pt.getTanque().getId())
							.collect(Collectors.toList());

					return new JugadorLobbyDTO(
							j.getUsuario().getId(),
							j.getUsuario().getNickname(),
							j.getUsuario().getIcono(),
							iconoImagen,
							j.isListo(),
							tanquesIds);
				})
				.collect(Collectors.toList());

		boolean invitadoListo = jugadores.stream()
				.filter(j -> !j.getUsuario().getId().equals(partida.getHost().getId()))
				.anyMatch(PartidaJugador::isListo);

		return new PartidaResponse(
				partida.getId(),
				partida.getNombre(),
				partida.isPublica(),
				partida.getEstado().name(),
				partida.getHost().getNickname(),
				jugadores.size(),
				invitadoListo,
				jugadoresDTO);
	}
}
