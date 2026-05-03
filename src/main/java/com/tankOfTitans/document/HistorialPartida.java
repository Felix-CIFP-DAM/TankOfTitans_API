package com.tankOfTitans.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "historial_partidas")
public class HistorialPartida {
	@Id
    private String id;
    private Long mysqlPartidaId;
    private String nombrePartida;
    private String ganadorNickname;
    private String resultado;
    private Integer duracionSegundos;
    private Integer tanquesMuertosJ1;
    private Integer tanquesMuertosJ2;
    private LocalDateTime fechaPartida;
    
	public HistorialPartida() {

	}

	public HistorialPartida(Long mysqlPartidaId, String nombrePartida, String ganadorNickname, String resultado,
			Integer duracionSegundos, Integer tanquesMuertosJ1, Integer tanquesMuertosJ2) {
		this.mysqlPartidaId = mysqlPartidaId;
		this.nombrePartida = nombrePartida;
		this.ganadorNickname = ganadorNickname;
		this.resultado = resultado;
		this.duracionSegundos = duracionSegundos;
		this.tanquesMuertosJ1 = tanquesMuertosJ1;
		this.tanquesMuertosJ2 = tanquesMuertosJ2;
		this.fechaPartida = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getMysqlPartidaId() {
		return mysqlPartidaId;
	}

	public void setMysqlPartidaId(Long mysqlPartidaId) {
		this.mysqlPartidaId = mysqlPartidaId;
	}

	public String getNombrePartida() {
		return nombrePartida;
	}

	public void setNombrePartida(String nombrePartida) {
		this.nombrePartida = nombrePartida;
	}

	public String getGanadorNickname() {
		return ganadorNickname;
	}

	public void setGanadorNickname(String ganadorNickname) {
		this.ganadorNickname = ganadorNickname;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
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

	public LocalDateTime getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(LocalDateTime fechaPartida) {
		this.fechaPartida = fechaPartida;
	}
	
	
	
	
	
	
    
    

}

