package com.tankOfTitans.model.dto.request;

public class JoinPartidaRequest {

    private String password;

    public JoinPartidaRequest() {
    	
    }
    
    public JoinPartidaRequest(String password) {
		this.password = password;
	}

	public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
