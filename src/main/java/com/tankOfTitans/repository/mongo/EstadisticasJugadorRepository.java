package com.tankOfTitans.repository.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tankOfTitans.document.EstadisticasJugador;

@Profile("mongo")
public interface EstadisticasJugadorRepository extends MongoRepository<EstadisticasJugador, String> {
	Optional<EstadisticasJugador> findByMysqlUserId(Long mysqlUserId);
    List<EstadisticasJugador> findAllByOrderByVictoriasDesc();
}
