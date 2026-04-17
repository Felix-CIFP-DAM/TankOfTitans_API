package com.tankOfTitans.model.dto.request;

public class RegisterRequest {
	private String nickname;
    private String email;
    private String password;

    public RegisterRequest() {
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

    public void setNombreUsuario(String nickname) {
    	this.nickname = nickname;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public void setPassword(String password) {
    	this.password = password; 
    }

}
