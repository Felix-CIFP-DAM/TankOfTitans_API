package com.tankOfTitans.model.dto.request;



public class LoginRequest {
	private String nickname;
    private String password;

    public LoginRequest() {
    	
    }

    public String getNickname() { 
    	return nickname;
    }
    public String getPassword() { 
    	return password; 
    }

    public void setNickname(String nickname) {
    	this.nickname = nickname;
    }
    public void setPassword(String password) {
    	this.password = password; 
    }
	
}
