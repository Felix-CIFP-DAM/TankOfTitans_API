package com.tankOfTitans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tankOfTitans.model.entity.Partida;

public interface PartidaRepository extends JpaRepository <Partida, Long> {
	}
