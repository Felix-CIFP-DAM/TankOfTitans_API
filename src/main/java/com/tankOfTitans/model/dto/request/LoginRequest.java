package com.tankOfTitans.model.dto.request;



public class LoginRequest {
	private String nickname;
    private String password;

    public LoginRequest() {
    	
    }

    public String getNombreUsuario() { 
    	return nickname;
    }
    public String getPassword() { 
    	return password; 
    }

    public void setNombreUsuario(String nickname) {
    	this.nickname = nickname;
    }
    public void setPassword(String password) {
    	this.password = password; 
    }
	
}
