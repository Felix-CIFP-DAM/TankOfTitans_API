package com.tankOfTitans.model.dto.response;

public class LoginResponse {
    private String token;
    private Long userId;
    private String nombre;
    private String nickname;

    private String message;
    private int icono;
    private String iconoImagen;

    
	public LoginResponse() {	
	}

	public LoginResponse(String token, Long userId, String nombre, String nickname, String message, int icono, String iconoImagen) {
		super();
		this.token = token;
		this.userId = userId;
		this.nombre = nombre;
		this.nickname = nickname;

		this.message = message;
		this.icono = icono;
		this.iconoImagen = iconoImagen;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	
	
    
    
}    
