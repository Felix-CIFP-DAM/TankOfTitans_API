package com.tankOfTitans.model.dto.request;


public class CreatePartidaRequest {
	private String nombre;
    private boolean publica;
    private String password;

    public CreatePartidaRequest() {
    	
    }
    
    public CreatePartidaRequest(String nombre, boolean publica, String password) {
		super();
		this.nombre = nombre;
		this.publica = publica;
		this.password = password;
	}

	public String getNombre() { return nombre; }
    public boolean isPublica() { return publica; }
    public String getPassword() { return password; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPublica(boolean publica) { this.publica = publica; }
    public void setPassword(String password) { this.password = password; }
}

