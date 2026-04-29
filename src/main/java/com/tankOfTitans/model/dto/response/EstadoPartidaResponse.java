package com.tankOfTitans.model.dto.response;

import java.util.List;

import com.tankOfTitans.model.dto.JugadorEstadoDTO;

public class EstadoPartidaResponse {
	 private Long partidaId;
	    private String estado;
	    private int tanquesMuertosJ1;
	    private int tanquesMuertosJ2;
	    private List<JugadorEstadoDTO> jugadores;

	    public EstadoPartidaResponse() {}

	    public EstadoPartidaResponse(Long partidaId, String estado, int tanquesMuertosJ1, int tanquesMuertosJ2,
	                                  List<JugadorEstadoDTO> jugadores) {
	        this.partidaId = partidaId;
	        this.estado = estado;
	        this.tanquesMuertosJ1 = tanquesMuertosJ1;
	        this.tanquesMuertosJ2 = tanquesMuertosJ2;
	        this.jugadores = jugadores;
	    }

		public Long getPartidaId() {
			return partidaId;
		}

		public void setPartidaId(Long partidaId) {
			this.partidaId = partidaId;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public int getTanquesMuertosJ1() {
			return tanquesMuertosJ1;
		}

		public void setTanquesMuertosJ1(int tanquesMuertosJ1) {
			this.tanquesMuertosJ1 = tanquesMuertosJ1;
		}

		public int getTanquesMuertosJ2() {
			return tanquesMuertosJ2;
		}

		public void setTanquesMuertosJ2(int tanquesMuertosJ2) {
			this.tanquesMuertosJ2 = tanquesMuertosJ2;
		}

		public List<JugadorEstadoDTO> getJugadores() {
			return jugadores;
		}

		public void setJugadores(List<JugadorEstadoDTO> jugadores) {
			this.jugadores = jugadores;
		}
	    
	    
}
