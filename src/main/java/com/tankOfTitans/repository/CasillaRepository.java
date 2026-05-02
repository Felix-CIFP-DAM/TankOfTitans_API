package com.tankOfTitans.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tankOfTitans.model.entity.Casilla;

public interface CasillaRepository extends JpaRepository <Casilla, Long> {
	 List<Casilla> findByMapaId(Long mapaId);
	 List<Casilla> findByMapaIdAndTransitableTrue(Long mapaId);
}
