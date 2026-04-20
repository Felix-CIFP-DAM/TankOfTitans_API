package com.tankOfTitans.model.dto.request;

public class RegisterRequest {
	private String nombre;
	private String nickname;
    private String email;
    private String password;

    public RegisterRequest() {
    }
    
    
    
    public RegisterRequest(String nombre, String nickname, String email, String password) {
		super();
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
