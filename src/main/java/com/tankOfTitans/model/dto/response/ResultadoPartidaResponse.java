package com.tankOfTitans.model.dto.response;

public class ResultadoPartidaResponse {
	private Long partidaId;
    private String resultado;
    private String ganadorNickname;
    private int duracionSegundos;
    private String mensaje;

    public ResultadoPartidaResponse() {}

    public ResultadoPartidaResponse(Long partidaId, String resultado, String ganadorNickname, int duracionSegundos, String mensaje) {
        this.partidaId = partidaId;
        this.resultado = resultado;
        this.ganadorNickname = ganadorNickname;
        this.duracionSegundos = duracionSegundos;
        this.mensaje = mensaje;
    }

	public Long getPartidaId() {
		return partidaId;
	}

	public void setPartidaId(Long partidaId) {
		this.partidaId = partidaId;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getGanadorNickname() {
		return ganadorNickname;
	}

	public void setGanadorNickname(String ganadorNickname) {
		this.ganadorNickname = ganadorNickname;
	}

	public int getDuracionSegundos() {
		return duracionSegundos;
	}

	public void setDuracionSegundos(int duracionSegundos) {
		this.duracionSegundos = duracionSegundos;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
    
    
}
