package com.tankOfTitans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tankOfTitans.model.entity.PartidaTanque;

public interface PartidaTanqueRepository extends JpaRepository<PartidaTanque,Long> {
	List<PartidaTanque> findByPartidaJugadorId(Long partidaJugadorId);
    void deleteByPartidaJugadorPartidaId(Long partidaId);

}
