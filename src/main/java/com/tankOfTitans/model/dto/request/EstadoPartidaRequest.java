package com.tankOfTitans.model.dto.request;

import java.util.List;

import com.tankOfTitans.model.dto.JugadorEstadoDTO;

public class EstadoPartidaRequest {
	private List<JugadorEstadoDTO> jugadores;
    public EstadoPartidaRequest() {
    	
    }
	public List<JugadorEstadoDTO> getJugadores() {
		return jugadores;
	}
	public void setJugadores(List<JugadorEstadoDTO> jugadores) {
		this.jugadores = jugadores;
	}
    
    
}
