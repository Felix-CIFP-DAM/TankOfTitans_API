package com.tankOfTitans.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tanque {
	 @Id @GeneratedValue
	    private Long id;

	    @Enumerated(EnumType.STRING)
	    private TipoTanque tipo;

	    private int hp;
	    private int ataque;
	    private int defensa;
	    private int rangoAtaque;
	    private int rangoMovimiento;

	    @ManyToOne
	    private Jugador jugador;

	    @ManyToOne
	    private Partida partida;
}
