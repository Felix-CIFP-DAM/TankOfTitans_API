package com.tankOfTitans.model.response;

public class PartidaResponse {

    private Long id;
    private String nombre;
    private boolean publica;
    private String estado;
    private String hostNickname;
    private int jugadores;
    private boolean invitadoListo;
    
	public PartidaResponse() {
		
	}

	public PartidaResponse(Long id, String nombre, boolean publica, String estado, String hostNickname, int jugadores,
			boolean invitadoListo) {
		this.id = id;
		this.nombre = nombre;
		this.publica = publica;
		this.estado = estado;
		this.hostNickname = hostNickname;
		this.jugadores = jugadores;
		this.invitadoListo = invitadoListo;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getHostNickname() {
		return hostNickname;
	}

	public void setHostNickname(String hostNickname) {
		this.hostNickname = hostNickname;
	}

	public int getJugadores() {
		return jugadores;
	}

	public void setJugadores(int jugadores) {
		this.jugadores = jugadores;
	}

	public boolean isInvitadoListo() {
		return invitadoListo;
	}

	public void setInvitadoListo(boolean invitadoListo) {
		this.invitadoListo = invitadoListo;
	}
	
	
	
	
    
    
}
