package com.tankOfTitans.model.dto;

import java.util.List;

public class JugadorEstadoDTO {
	private Long usuarioId;
    private String nickname;
    private int tanquesMuertos;
    private List<TanqueEstadoDTO> tanques;

    public JugadorEstadoDTO() {}

    public JugadorEstadoDTO(Long usuarioId, String nickname,
                            int tanquesMuertos, List<TanqueEstadoDTO> tanques) {
        this.usuarioId = usuarioId;
        this.nickname = nickname;
        this.tanquesMuertos = tanquesMuertos;
        this.tanques = tanques;
    }

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTanquesMuertos() {
		return tanquesMuertos;
	}

	public void setTanquesMuertos(int tanquesMuertos) {
		this.tanquesMuertos = tanquesMuertos;
	}

	public List<TanqueEstadoDTO> getTanques() {
		return tanques;
	}

	public void setTanques(List<TanqueEstadoDTO> tanques) {
		this.tanques = tanques;
	}
    
    
}
