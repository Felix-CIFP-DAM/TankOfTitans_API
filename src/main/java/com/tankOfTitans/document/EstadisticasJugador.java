package com.tankOfTitans.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "estadisticas_jugador")
public class EstadisticasJugador {
	@Id
    private String id;
    private Long mysqlUserId;
    private String nickname;
    private Integer partidasJugadas;
    private Integer victorias;
    private Integer derrotas;
    private Integer empates;
    private LocalDateTime ultimaActualizacion;
    
	public EstadisticasJugador() {

	}

	public EstadisticasJugador(String id, Long mysqlUserId, String nickname, Integer partidasJugadas, Integer victorias,
			Integer derrotas, Integer empates, LocalDateTime ultimaActualizacion) {
		this.mysqlUserId = mysqlUserId;
		this.nickname = nickname;
		this.partidasJugadas = partidasJugadas;
		this.victorias = victorias;
		this.derrotas = derrotas;
		this.empates = empates;
		this.ultimaActualizacion = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getMysqlUserId() {
		return mysqlUserId;
	}

	public void setMysqlUserId(Long mysqlUserId) {
		this.mysqlUserId = mysqlUserId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(Integer partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public Integer getVictorias() {
		return victorias;
	}

	public void setVictorias(Integer victorias) {
		this.victorias = victorias;
	}

	public Integer getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(Integer derrotas) {
		this.derrotas = derrotas;
	}

	public Integer getEmpates() {
		return empates;
	}

	public void setEmpates(Integer empates) {
		this.empates = empates;
	}

	public LocalDateTime getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
	
	

	
	
    
    
}
