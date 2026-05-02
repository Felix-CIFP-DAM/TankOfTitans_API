package com.tankOfTitans.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tankOfTitans.model.entity.Mapa;

public interface MapaRepository extends JpaRepository<Mapa, Long>  {
	 Optional<Mapa> findByNombre(String nombre);
}
