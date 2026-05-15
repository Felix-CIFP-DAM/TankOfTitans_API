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
@Table(name = "partida_base")
public class PartidaBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partida_jugador_id", nullable = false)
    private PartidaJugador partidaJugador;

    @Column(nullable = false)
    private int hp = 100;

    @Column(name = "pos_x", nullable = false)
    private int posX;

    @Column(name = "pos_y", nullable = false)
    private int posY;

    @Column(name = "es_host", nullable = false)
    private boolean esHost;

    public PartidaBase() {}

    public PartidaBase(PartidaJugador partidaJugador, int posX, int posY, boolean esHost) {
        this.partidaJugador = partidaJugador;
        this.posX = posX;
        this.posY = posY;
        this.esHost = esHost;
        this.hp = 100;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PartidaJugador getPartidaJugador() { return partidaJugador; }
    public void setPartidaJugador(PartidaJugador partidaJugador) { this.partidaJugador = partidaJugador; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getPosX() { return posX; }
    public void setPosX(int posX) { this.posX = posX; }

    public int getPosY() { return posY; }
    public void setPosY(int posY) { this.posY = posY; }

    public boolean isEsHost() { return esHost; }
    public void setEsHost(boolean esHost) { this.esHost = esHost; }
}
