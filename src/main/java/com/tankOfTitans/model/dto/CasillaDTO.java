package com.tankOfTitans.model.dto;

public class CasillaDTO {
	private int posX;
    private int posY;
    private boolean transitable;
    private String tipo;

    public CasillaDTO() {
    	
    }

    public CasillaDTO(int posX, int posY, boolean transitable, String tipo) {
        this.posX = posX;
        this.posY = posY;
        this.transitable = transitable;
        this.tipo = tipo;
    }

    public int getPosX() {
    	return posX; 
    }
    
    public int getPosY() {
    	return posY;
    }
    
    public boolean isTransitable() {
    	return transitable; 
    }

    public void setPosX(int posX) {
    	this.posX = posX; 
    }
    public void setPosY(int posY) { 
    	this.posY = posY; 
    }
    public void setTransitable(boolean transitable) {
    	this.transitable = transitable;
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
