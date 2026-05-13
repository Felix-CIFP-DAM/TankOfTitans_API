package com.tankOfTitans.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePartidaRequest {
	@JsonProperty("usuarioId")
	private Long usuarioId;
	private String nombre;
    private boolean publica;
    private String password;

    public CreatePartidaRequest() {
    	
    }
    
    public CreatePartidaRequest(Long usuarioId, String nombre, boolean publica, String password) {
		super();
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.publica = publica;
		this.password = password;
	}

	@JsonProperty("usuarioId")
	public Long getUsuarioId() { return usuarioId; }
	@JsonProperty("usuarioId")
	public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

	public String getNombre() { return nombre; }
    public boolean isPublica() { return publica; }
    public String getPassword() { return password; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPublica(boolean publica) { this.publica = publica; }
    public void setPassword(String password) { this.password = password; }
}

