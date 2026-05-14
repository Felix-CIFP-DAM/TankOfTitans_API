package com.tankOfTitans.model.dto.response;

import java.util.List;
import com.tankOfTitans.model.dto.JugadorLobbyDTO;

public class PartidaResponse {

    private Long id;
    private String nombre;
    private boolean publica;
    private String estado;
    private String hostNickname;
    private Long hostId;
    private int jugadores;
    private boolean invitadoListo;
    private List<JugadorLobbyDTO> jugadoresList;
    private Long mapaId;
    
	public PartidaResponse() {
		
	}

	public PartidaResponse(Long id, String nombre, boolean publica, String estado, String hostNickname, Long hostId, int jugadores,
			boolean invitadoListo, List<JugadorLobbyDTO> jugadoresList, Long mapaId) {
		this.id = id;
		this.nombre = nombre;
		this.publica = publica;
		this.estado = estado;
		this.hostNickname = hostNickname;
		this.hostId = hostId;
		this.jugadores = jugadores;
		this.invitadoListo = invitadoListo;
		this.jugadoresList = jugadoresList;
		this.mapaId = mapaId;
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
	public List<JugadorLobbyDTO> getJugadoresList() {
		return jugadoresList;
	}

	public void setJugadoresList(List<JugadorLobbyDTO> jugadoresList) {
		this.jugadoresList = jugadoresList;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public Long getMapaId() {
		return mapaId;
	}

	public void setMapaId(Long mapaId) {
		this.mapaId = mapaId;
	}
}
