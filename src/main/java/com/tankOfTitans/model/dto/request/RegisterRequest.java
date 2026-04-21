package com.tankOfTitans.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
	@NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El nickname es obligatorio")
    private String nickname;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
    private String password;

    public RegisterRequest() {
    }
    
    
    
    public RegisterRequest(String nombre, String nickname, String email, String password) {
		this.nombre = nombre;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}



	public String getNombre() {
    	return nombre;
    }

    public String getNickname() {
    	return nickname;
    }
    public String getEmail() {
    	return email;
    }
    public String getPassword() {
    	return password;
    }
    
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public void setNickname(String nickname) {
    	this.nickname = nickname;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public void setPassword(String password) {
    	this.password = password; 
    }

}
