package com.tankOfTitans.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Partida {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String estado;
	
	@OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<Jugador> jugadores;
	
	@ManyToOne
    private Jugador jugadorTurno;
}
