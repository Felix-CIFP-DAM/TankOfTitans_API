package com.tankOfTitans.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tankOfTitans.model.entity.PartidaJugador;

public interface PartidaJugadorRepository extends JpaRepository<PartidaJugador, Long> {
	List<PartidaJugador> findByPartidaId(Long partidaId);
    Optional<PartidaJugador> findByPartidaIdAndUsuarioId(Long partidaId, Long usuarioId);
    boolean existsByUsuarioId(Long usuarioId);
    void deleteByPartidaId(Long partidaId);

}
