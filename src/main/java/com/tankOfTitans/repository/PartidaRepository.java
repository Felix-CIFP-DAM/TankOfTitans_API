package com.tankOfTitans.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tankOfTitans.model.entity.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
	List<Partida> finfByPublicaTrue();
	Optional<Partida> finfByHostId(Long hostId);
	boolean existsByHostId(Long hostId);
}
