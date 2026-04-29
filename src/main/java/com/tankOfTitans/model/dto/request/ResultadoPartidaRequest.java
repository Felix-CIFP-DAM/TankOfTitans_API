package com.tankOfTitans.model.dto.request;

public class ResultadoPartidaRequest {
	 private Long ganadorId;   // null si empate
	    private Long perdedorId;  // null si empate
	    private boolean empate;
	    private int duracionSegundos;
	    private int tanquesMuertosJ1;
	    private int tanquesMuertosJ2;
	    
		public ResultadoPartidaRequest() {
		
		}

		public Long getGanadorId() {
			return ganadorId;
		}

		public void setGanadorId(Long ganadorId) {
			this.ganadorId = ganadorId;
		}

		public Long getPerdedorId() {
			return perdedorId;
		}

		public void setPerdedorId(Long perdedorId) {
			this.perdedorId = perdedorId;
		}

		public boolean isEmpate() {
			return empate;
		}

		public void setEmpate(boolean empate) {
			this.empate = empate;
		}

		public int getDuracionSegundos() {
			return duracionSegundos;
		}

		public void setDuracionSegundos(int duracionSegundos) {
			this.duracionSegundos = duracionSegundos;
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
		
		
		
	    
	    
}
