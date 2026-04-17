package com.tankOfTitans.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Jugador {
	 @Id @GeneratedValue
	    private Long id;

	    @ManyToOne
	    private Usuario usuario;

	    @ManyToOne
	    private Partida partida;

	    private int puntosVida;
	    private int puntosAccion;

	    private boolean esTurno;

		
	    
	    
}
