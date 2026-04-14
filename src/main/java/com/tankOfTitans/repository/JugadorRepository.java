package com.tankOfTitans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tankOfTitans.model.entity.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}