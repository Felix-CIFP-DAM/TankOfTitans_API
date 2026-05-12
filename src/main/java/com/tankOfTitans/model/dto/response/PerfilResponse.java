package com.tankOfTitans.model.dto.response;

public class PerfilResponse {
	private Long id;
	private String nombre;
	private String nickname;
	private String email;
	private int icono;
	private String iconoImagen;
	private int partidasJugadas;

	private int victorias;
	private int derrotas;
	private int empates;

	public PerfilResponse() {

	}

	public PerfilResponse(Long id, String nombre, String nickname, String email, int icono, String iconoImagen,
			int partidasJugadas, int victorias, int derrotas, int empates) {

		this.id = id;
		this.nombre = nombre;
		this.nickname = nickname;
		this.email = email;
		this.icono = icono;
		this.iconoImagen = iconoImagen;
		this.partidasJugadas = partidasJugadas;

		this.victorias = victorias;
		this.derrotas = derrotas;
		this.empates = empates;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIcono() {
		return icono;
	}

	public void setIcono(int icono) {
		this.icono = icono;
	}

	public String getIconoImagen() {
		return iconoImagen;
	}

	public void setIconoImagen(String iconoImagen) {
		this.iconoImagen = iconoImagen;
	}


	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

}
