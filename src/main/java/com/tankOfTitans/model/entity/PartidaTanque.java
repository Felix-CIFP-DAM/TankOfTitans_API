package com.tankOfTitans.model.entity;

import com.tankOfTitans.model.entity.enums.TipoTanque;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "partida_tanque")
public class PartidaTanque {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    // Identificador del tanque dentro de la partida (1, 2 o 3 por jugador)
	  @Column(name = "numero_tanque", nullable = false)
	  private int numeroTanque;

	  @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private TipoTanque tipo;

	    @Column(nullable = false)
	    private int hp;

	    @Column(nullable = false)
	    private boolean vivo = true;

	    @Column(nullable = false)
	    private int posX;

	    @Column(nullable = false)
	    private int posY;

	    @ManyToOne
	    @JoinColumn(name = "partida_jugador_id", nullable = false)
	    private PartidaJugador partidaJugador;

	    public PartidaTanque() {
	    	
	    }

	    public PartidaTanque(int numeroTanque, TipoTanque tipo, int hp,
	                         int posX, int posY, PartidaJugador partidaJugador) {
	        this.numeroTanque = numeroTanque;
	        this.tipo = tipo;
	        this.hp = hp;
	        this.posX = posX;
	        this.posY = posY;
	        this.partidaJugador = partidaJugador;
	        this.vivo = true;
	        // HP inicial según tipo
	        switch (tipo) {
	            case SUPERPESADO -> this.hp = 120;
	            case LIGERO -> this.hp = 50;
	            case MEDIO -> this.hp = 70;
	        }
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

		public TipoTanque getTipo() {
			return tipo;
		}

		public void setTipo(TipoTanque tipo) {
			this.tipo = tipo;
		}

		public int getHp() {
			return hp;
		}

		public void setHp(int hp) {
			this.hp = hp;
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

	   
	
}
