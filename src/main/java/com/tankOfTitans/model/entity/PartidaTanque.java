package com.tankOfTitans.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "partida_tanque")
public class PartidaTanque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Identificador del tanque dentro de la partida (1, 2 o 3 por jugador)
	@Column(name = "numero_tanque", nullable = false)
	private int numeroTanque;

	@Column(nullable = false)
	private boolean vivo = true;

	@Column(nullable = false)
	private int posX;

	@Column(nullable = false)
	private int posY;

	@ManyToOne
	@JoinColumn(name = "partida_jugador_id", nullable = false)
	private PartidaJugador partidaJugador;

	@ManyToOne
	@JoinColumn(name = "tanque_id", nullable = false)
	private Tanque tanque;

	public PartidaTanque() {

	}

	public PartidaTanque(int numeroTanque, int posX, int posY, PartidaJugador partidaJugador, Tanque tanque) {
		this.numeroTanque = numeroTanque;
		this.posX = posX;
		this.posY = posY;
		this.partidaJugador = partidaJugador;
		this.tanque = tanque;
		this.vivo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroTanque() {
		return numeroTanque;
	}

	public void setNumeroTanque(int numeroTanque) {
		this.numeroTanque = numeroTanque;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public PartidaJugador getPartidaJugador() {
		return partidaJugador;
	}

	public void setPartidaJugador(PartidaJugador partidaJugador) {
		this.partidaJugador = partidaJugador;
	}

	public Tanque getTanque() {
		return tanque;
	}

	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}

}
