package com.tankOfTitans.model.entity;

import java.time.LocalDateTime;

import com.tankOfTitans.model.entity.enums.EstadoPartida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="partida")
public class Partida {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private boolean publica;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPartida estado = EstadoPartida.ESPERANDO;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Usuario host;
    
    @ManyToOne
    @JoinColumn(name = "ganador_id")
    private Usuario ganador;

    @Column(name = "duracion_segundos")
    private Integer duracionSegundos;

    @Column(name = "tanques_muertos_j1")
    private Integer tanquesMuertosJ1 = 0;

    @Column(name = "tanques_muertos_j2")
    private Integer tanquesMuertosJ2 = 0;
    
    @ManyToOne
    @JoinColumn(name = "mapa_id")
    private Mapa mapa;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

	public Partida() {
		
	}

	public Partida(String nombre, boolean publica, String password, Usuario host) {
		this.nombre = nombre;
		this.publica = publica;
		this.password = password;
		this.host = host;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EstadoPartida getEstado() {
		return estado;
	}

	public void setEstado(EstadoPartida estado) {
		this.estado = estado;
	}

	public Usuario getHost() {
		return host;
	}

	public void setHost(Usuario host) {
		this.host = host;
	}
	
	
	public Usuario getGanador() {
		return ganador;
	}

	public void setGanador(Usuario ganador) {
		this.ganador = ganador;
	}

	public Integer getDuracionSegundos() {
		return duracionSegundos;
	}

	public void setDuracionSegundos(Integer duracionSegundos) {
		this.duracionSegundos = duracionSegundos;
	}

	public Integer getTanquesMuertosJ1() {
		return tanquesMuertosJ1;
	}

	public void setTanquesMuertosJ1(Integer tanquesMuertosJ1) {
		this.tanquesMuertosJ1 = tanquesMuertosJ1;
	}

	public Integer getTanquesMuertosJ2() {
		return tanquesMuertosJ2;
	}

	public void setTanquesMuertosJ2(Integer tanquesMuertosJ2) {
		this.tanquesMuertosJ2 = tanquesMuertosJ2;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
    
    

}
