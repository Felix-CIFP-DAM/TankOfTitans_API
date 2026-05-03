package com.tankOfTitans.repository.mongo;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tankOfTitans.document.HistorialPartida;

@Profile("mongo")
public interface HistorialPartidaRepository extends MongoRepository<HistorialPartida, String> {
	 List<HistorialPartida> findAllByOrderByFechaPartidaDesc();
	
}
