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
@Table(name="partida_jugador")
public class PartidaJugador {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partida_id", nullable = false)
    private Partida partida;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private boolean listo = false;

    @Column(name = "puntos_accion", nullable = false)
    private int puntosAccion = 100;

    @Column(nullable = false)
    private int vida = 1000;

	public PartidaJugador() {
	}

	public PartidaJugador(Partida partida, Usuario usuario, boolean listo, int puntosAccion) {
		this.partida = partida;
		this.usuario = usuario;
		this.listo = listo;
		this.puntosAccion = puntosAccion;
        this.vida = 1000;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isListo() {
		return listo;
	}

	public void setListo(boolean listo) {
		this.listo = listo;
	}

    public int getPuntosAccion() {
        return puntosAccion;
    }

    public void setPuntosAccion(int puntosAccion) {
        this.puntosAccion = puntosAccion;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
