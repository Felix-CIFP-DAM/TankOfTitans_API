package com.tankOfTitans.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JoinPartidaRequest {
	@JsonProperty("usuarioId")
	private Long usuarioId;
    private String password;

    public JoinPartidaRequest() {
    	
    }
    
    public JoinPartidaRequest(Long usuarioId, String password) {
		this.usuarioId = usuarioId;
		this.password = password;
	}

	@JsonProperty("usuarioId")
	public Long getUsuarioId() { return usuarioId; }
	@JsonProperty("usuarioId")
	public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

	public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
